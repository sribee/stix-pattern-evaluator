/*
* stix-pattern-evaluator
* Copyright (C) 2020 - Christopher Carver
* 
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package design.unstructured.stix.evaluator.mapper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.CaseFormat;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import design.unstructured.stix.evaluator.mapper.annotations.StixAnnotationType;
import design.unstructured.stix.evaluator.mapper.annotations.StixEntity;
import design.unstructured.stix.evaluator.mapper.annotations.StixProperty;

/**
 * The purpose of this class is to bring the Cyber Observable data model to your
 * java objects. This implementation is still a WIP and should be used with
 * caution.
 *
 * @author ccarv
 */
public class StixObservableMapper implements ObjectPathResolver {

    private static final Logger logger = LoggerFactory.getLogger(StixObservableMapper.class);

    private final Map<Class<?>, StixAnnotationType> observables = new HashMap<>();

    private final Map<String, StixObservablePropertyNode> observableTree = new HashMap<>();

    private final List<String> pathFilter = new ArrayList<>();

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
            logger.debug("-> analyzing entity {}", clazz);

            StixObservablePropertyNode node = null;
            String entityName = entity.name();

            if (entityName.isEmpty()) {
                entityName = clazz.getSimpleName().toLowerCase();
                logger.trace("--> @StixEntity did not have a name, using class name as observable", entityName);
            }

            logger.debug("--> @StixEntity '{}'", entityName);
            node = new StixObservablePropertyNode(null, entityName, null, clazz, false);
            tree.put(entityName, node);

            build(clazz, node);

            return tree;
        }

        private void build(Class<?> clazz, StixObservablePropertyNode nodeParent) {
            // Need to keep track of classes that have already been mapped to avoid infinite
            // recursion
            visitedClasses.add(clazz);

            getFields(clazz).entrySet().forEach((entry) -> {
                boolean isReferenceNode = entry.getKey().isEmpty();
                Field field = entry.getValue();
                String propertyName = (!isReferenceNode ? entry.getKey() : "&" + field.getName());
                String[] properties = propertyName.split("(\\.)|(:)"); // propertySplitter.apply(propertyName);
                Boolean isGeneric = isGenericJavaType(field.getType());

                if (!observables.containsKey(field.getType()) && !isGeneric) {
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
    public StixObservableMapper(final Set<Class<?>> stixClasses) {
        logger.debug("scanning for @StixEntity and @StixObject in {} classes", stixClasses.size());

        // Scan for STIX Entity annotations
        stixClasses.forEach((clazz) -> {
            logger.debug("class [{}] matched stix annotation type, scanning for properties...", clazz.getName());
            observables.put(clazz, (clazz.isAnnotationPresent(StixEntity.class) ? StixAnnotationType.ENTITY
                    : StixAnnotationType.OBJECT));
        });

        logger.debug("finished scanning metagrid system artifacts, found {} stix observables", observables.size());
        logger.debug("building stix observable tree on all @StixEntity...");
        observables.entrySet().stream().filter((entry) -> entry.getValue().equals(StixAnnotationType.ENTITY))
                .forEach(entry -> {
                    Class<?> clazz = entry.getKey();
                    observableTree.putAll(new StixObservableTreeIntrospector(clazz).map());
                });
        logger.debug("finished building stix observable tree");

        if (logger.isTraceEnabled()) {
            observableTree.entrySet().forEach(
                    (entry) -> logger.trace("Observable: " + entry.getKey() + " = " + entry.getValue().toString()));
        }

    }

    /**
     * Adds a path filter that will be used during getFilter(...).
     */
    public StixObservableMapper addPathFilter(final String filter) {
        pathFilter.add(filter);

        return this;
    }

    /**
     * Gets the value from an object using the specified object path. The path must
     * point to a valid property, otherwise an empty string value will be returned.
     *
     * @param object
     * @param path
     * @return
     * @throws StixMappingException
     */
    @Override
    public Object getValue(final Object object, final String path) throws StixMappingException {
        Object value = null;

        if (observables.containsKey(object.getClass())) {
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
                            throw new StixMappingException(ex.getMessage());
                        }

                    } else if (instance != null) {
                        logger.trace("property node '{}' is an entity and has no nested type, skipping",
                                node.getName());
                    }
                }

                value = instance;
            }
        }

        return value;
    }

    /**
     *
     * @return
     */
    private List<StixObservablePropertyNode> buildNodePath(final String path) throws StixMappingException {
        List<StixObservablePropertyNode> nodePath;
        StixObservablePropertyNode node;

        logger.trace("analyzing path '{}' for observables", path);

        if ((node = observableTree.get(path)) != null) {
            // Our path was available in the stixTree
            nodePath = node.getPath();
            logger.trace("found observable node directly from path string '{}', navigating path", path);

        } else {
            // Our path wasn't available, need to walk the tree manually
            String[] objectPath = ObjectPathUtils.toArray(path, pathFilter);

            if (objectPath == null || objectPath.length == 1) {
                throw new StixMappingException("An invalid STIX observable object path (" + path + ") was specified.");
            }

            nodePath = new ArrayList<>();
            node = observableTree.get(objectPath[0]);

            if (node == null) {
                throw new StixMappingException("Unable to find root observable node for path '" + path
                        + "', the property '" + objectPath[0] + "' was not found");
            }

            nodePath.add(node);

            for (int i = 1; i < objectPath.length; i++) {
                node = node.getChildren().get(objectPath[i]);

                if (node != null) {
                    logger.trace("found child node '{}'", node.getName());
                    nodePath.add(node);

                    if (observables.get(node.getClazz()).equals(StixAnnotationType.ENTITY)) {
                        String newPath = ObjectPathUtils.toPath(ArrayUtils.remove(objectPath, i));

                        logger.trace(
                                "child node '{}' class type is type @StixEntity, using existing cache for lookup of path '{}'",
                                node.getName(), newPath);
                        nodePath.addAll(buildNodePath(newPath));
                        break;
                    }
                } else {
                    throw new StixMappingException("Unable to find observable node for path '" + path + "', property '"
                            + objectPath[i] + "' was not found");
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

    /**
     * Returns a map of STIX observable classes. These are the same classes passed
     * to the constructor that contained either a {@code StixEntity} or
     * {@code StixObject} annotation.
     * 
     * @return
     */
    public Map<Class<?>, StixAnnotationType> getObservables() {
        return observables;
    }

    /**
     * Returns the STIX observable tree. Your key will represent the STIX Observable
     * as a string and the value will be a {@code StixObservablePropertyNode}.
     * 
     * @return
     */
    public Map<String, StixObservablePropertyNode> getObservableTree() {
        return observableTree;
    }
}
