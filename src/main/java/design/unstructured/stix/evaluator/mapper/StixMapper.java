/*
 * stix-pattern-evaluator
 * Copyright (C) 2020 - Christopher Carver
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package design.unstructured.stix.evaluator.mapper;

import com.google.common.base.CaseFormat;
import design.unstructured.stix.evaluator.mapper.annotations.StixAnnotationType;
import design.unstructured.stix.evaluator.mapper.annotations.StixEntity;
import design.unstructured.stix.evaluator.mapper.annotations.StixProperty;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The purpose of the StixMapper is to provide a one-stop-shop for building and
 * resolving STIX observables (process:name -> com.package.pojos.Process::name)
 * to your applications Java objects.
 *
 * @author ccarv
 */
public class StixMapper implements ObjectPathResolver {

    private static final Logger logger = LoggerFactory.getLogger(StixMapper.class);

    private static final String METAGRID_ARTIFACTS_PACKAGE = "com.redlambda.metagrid.correlator.artifacts";

    private final Map<Class<?>, StixAnnotationType> stixObservables = new HashMap<>();

    private final Map<String, StixObservablePropertyNode> stixTree = new HashMap<>();

    private final List<String> pathFilter = new ArrayList<>();

    /**
     * Use to split STIX observable object path (process:parent_ref:name) into a
     * string array.
     */
    private final Function<String, String[]> propertySplitter = property -> {
        String propertyReplaced = property;

        for (String filter : pathFilter) {
            propertyReplaced = propertyReplaced.replace(filter + ".", "");
        }

        return propertyReplaced.split("(\\.)|(:)");
    };

    /**
     * Joins a string array back to a STIX observable object path.
     */
    private final Function<String[], String> propertyJoiner = properties -> {
        StringBuilder joined = new StringBuilder();

        for (int i = 0; i < properties.length; i++) {
            char delimeter = (i == 0 ? ':' : '.');

            joined.append(properties[i]).append(delimeter);
        }

        return joined.substring(0, joined.length() - 1);
    };

    /**
     * The StixObservableTreeIntrospector uses reflection (type introspection) to
     * build a STIX observable tree. This tree is essentially a cache of resolvable
     * STIX observables to their Java object counterparts. Object path collisions
     * will throw an exception.
     */
    class StixObservableTreeIntrospector {

        private final Set<Class<?>> visitedClasses = new HashSet<>();

        private final Map<String, StixObservablePropertyNode> tree = new HashMap<>();

        private final StixEntity entity;

        private final Class<?> clazz;

        StixObservableTreeIntrospector(final Class<?> clazz) {
            this.entity = clazz.getAnnotation(StixEntity.class);
            this.clazz = clazz;
        }

        public Map<String, StixObservablePropertyNode> map() {
            logger.debug("-> analyzing entity {}", entity.getClass());

            StixObservablePropertyNode node = null;

            if (entity.name().isEmpty()) {
                logger.debug(
                        "--> @StixEntity did not provide a class level stix observable 'name', only fields that have @StixProperty annnotations will be parsed...");
            } else {
                logger.debug("--> @StixEntity '{}'", entity.name());
                node = new StixObservablePropertyNode(null, entity.name(), null, clazz, false);
                tree.put(entity.name(), node);
            }

            build(clazz, node);

            return tree;
        }

        private void build(Class<?> clazz, StixObservablePropertyNode nodeParent) {
            // Need to keep track of classes that have already been mapped to avoid infinite
            // loop
            visitedClasses.add(clazz);

            getFields(clazz).entrySet().forEach((entry) -> {
                boolean isReferenceNode = entry.getKey().isEmpty();
                Field field = entry.getValue();
                String propertyName = (!isReferenceNode ? entry.getKey() : "&" + field.getName());
                String[] properties = propertySplitter.apply(propertyName);

                Boolean isGeneric = isGenericJavaType(field.getType());

                if (!stixObservables.containsKey(field.getType()) && !isGeneric) {
                    logger.warn(
                            "field '{}' type '{}' has no interpreter and is not available as a @StixObject, add @StixObject annotation to custom type or add a custom interpreter.",
                            field.getName(), field.getType().getName());
                } else {
                    if (properties.length > 1) {
                        logger.trace("@StixProperty {} is overriding the path but thats OK. :)", propertyName);
                    }

                    StixObservablePropertyNode node = new StixObservablePropertyNode(nodeParent, propertyName, field,
                            clazz, isReferenceNode);

                    field.setAccessible(true);
                    tree.put(isReferenceNode ? node.toPath() + ":" + propertyName : node.toPath(), node);

                    if (nodeParent != null) {
                        nodeParent.addChild(propertyName, node);
                    }

                    logger.debug("mapped field '{}' => '{}' {fullPath={}}", field.getName(), propertyName,
                            node.toPath());

                    if (!isGeneric && !visitedClasses.contains(field.getType())) {
                        logger.trace("entering nested field '{}=>{}'...", field.getName(), propertyName);
                        build(field.getType(), node);

                    } else if (visitedClasses.contains(field.getType())) {
                        logger.trace("nested field '{}=>{}' type '{}' has already been mapped", field.getName(),
                                propertyName, field.getType());
                    }
                }
            });
        }

    }

    /**
     * Provide a set of classes that use one of the STIX annotations from the mapper
     * annotations package.
     *
     * @see design.unstructured.stix.evaluator.mapper.annotations}
     * @param stixClasses
     */
    public StixMapper(final Set<Class<?>> stixClasses) {
        logger.debug("scanning for @StixEntity and @StixObject in package '{}'", METAGRID_ARTIFACTS_PACKAGE);

        // Scan for STIX Entity annotations
        stixClasses.forEach((clazz) -> {
            logger.debug("class [{}] matched stix annotation type, scanning for properties...", clazz.getName());
            stixObservables.put(clazz, (clazz.isAnnotationPresent(StixEntity.class) ? StixAnnotationType.ENTITY
                    : StixAnnotationType.OBJECT));
        });

        logger.debug("finished scanning metagrid system artifacts, found {} stix observables", stixObservables.size());
        logger.debug("building stix observable tree on all @StixEntity...");
        stixObservables.entrySet().stream().filter((entry) -> entry.getValue().equals(StixAnnotationType.ENTITY))
                .forEach(entry -> {
                    Class<?> clazz = entry.getKey();
                    stixTree.putAll(new StixObservableTreeIntrospector(clazz).map());
                });
        logger.debug("finished building stix observable tree");

        if (logger.isTraceEnabled()) {
            stixTree.entrySet().forEach(
                    (entry) -> logger.trace("Observable: " + entry.getKey() + " = " + entry.getValue().toString()));
        }

    }

    /**
     * Adds a path filter that will be used during getFilter(...).
     */
    public void addPathFilter(final String filter) {
        pathFilter.add(filter);
    }

    /**
     * Gets the value from an object using the specified object path. The path must
     * point to a valid property, otherwise an empty string value will be returned.
     *
     * @param object
     * @param path
     * @return
     * @throws StixMapperException
     */
    @Override
    public String getValue(final Object object, final String path) throws StixMapperException {
        String value = "";

        if (stixObservables.containsKey(object.getClass())) {
            List<StixObservablePropertyNode> nodePath = buildNodePath(path);

            if (nodePath != null) {
                Object instance = object;

                for (StixObservablePropertyNode node : nodePath) {
                    logger.trace("observing {} node '{}'...", node.isReference() ? "reference" : "property",
                            node.getName());
                    if (node.getField() != null && instance != null) {
                        try {
                            instance = node.getField().get(instance);
                        } catch (IllegalArgumentException | IllegalAccessException ex) {
                            throw new StixMapperException(ex.getMessage());
                        }
                    } else if (instance != null) {
                        logger.trace("property node '{}' is an entity and has no nested type, skipping",
                                node.getName());
                    }
                }

                value = (instance != null ? instance.toString() : "");
            }
        }

        return value;
    }

    /**
     *
     * @return
     */
    private List<StixObservablePropertyNode> buildNodePath(final String path) throws StixMapperException {
        List<StixObservablePropertyNode> nodePath;
        StixObservablePropertyNode node;

        logger.trace("analyzing path '{}' for observables", path);

        if ((node = stixTree.get(path)) != null) {
            // Our path was available in the stixTree
            nodePath = node.getPath();
            logger.trace("found observable node directly from path string '{}', navigating path", path);

        } else {
            // Our path wasn't available, need to walk the tree manually
            String[] properties = propertySplitter.apply(path);

            nodePath = new ArrayList<>();
            node = stixTree.get(properties[0]);

            if (node == null) {
                throw new StixMapperException("Unable to find root observable node for path '" + path
                        + "', the property '" + properties[0] + "' has no observable");
            }

            nodePath.add(node);

            for (int i = 1; i < properties.length; i++) {
                node = node.getChildren().get(properties[i]);

                if (node != null) {
                    logger.trace("found child node '{}'", node.getName());
                    nodePath.add(node);

                    if (stixObservables.get(node.getClazz()).equals(StixAnnotationType.ENTITY)) {
                        String newPath = propertyJoiner.apply(ArrayUtils.remove(properties, i));

                        logger.trace(
                                "child node '{}' class type is type @StixEntity, using existing cache for lookup of path '{}'",
                                node.getName(), newPath);
                        nodePath.addAll(buildNodePath(newPath));
                        break;
                    }
                } else {
                    throw new StixMapperException("Unable to find observable node for path '" + path + "', property '"
                            + properties[i] + "' has no observable");
                }
            }
        }

        return nodePath;
    }

    private static Map<String, Field> getFields(Class<?> clazz) {
        return getFields(clazz, false);
    }

    private static Map<String, Field> getFields(Class<?> clazz, boolean filterForStixProperties) {
        Map<String, Field> fields = new HashMap<>();

        for (Field field : clazz.getDeclaredFields()) {
            // Check to see if StixProperty overrides the field name
            if (field.isAnnotationPresent(StixProperty.class)) {
                for (String property : field.getAnnotation(StixProperty.class).name()) {
                    fields.put(property, field);
                    logger.trace("@StixProperty annotation found [{} => {}::{}]", property, clazz.getSimpleName(),
                            field.getName());
                }
            } else if (!filterForStixProperties) {
                fields.put(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName()), field);
            }
        }

        return fields;
    }

    public static boolean isGenericJavaType(Class<?> type) {
        return (type.isPrimitive() && type != void.class) || type == Double.class || type == Float.class
                || type == Long.class || type == Integer.class || type == Short.class || type == Character.class
                || type == Byte.class || type == Boolean.class || type == String.class;
    }
}
