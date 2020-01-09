/*
 * stix-pattern-evaluator
 * Copyright (C) 2020 - Unstructured Design
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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Used to maintain and represent relationships between STIX objects, also known
 * as STIX observables, within your application.
 *
 * @author ccarv
 */
public class StixObservablePropertyNode {

    private final String name;

    private final Field field;

    private final StixObservablePropertyNode parent;

    private final Class<?> clazz;

    private final Map<String, StixObservablePropertyNode> children = new HashMap<>();

    private final boolean reference;

    /**
     * This could use a builder pattern.
     *
     * @param parent
     * @param name
     * @param field
     */
    StixObservablePropertyNode(StixObservablePropertyNode parent, String name, Field field, Class<?> clazz, boolean isReference) {
        this.name = name;
        this.parent = parent;
        this.field = field;
        this.clazz = clazz;
        this.reference = isReference;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the field
     */
    public Field getField() {
        return field;
    }

    /**
     * @return the parent
     */
    public StixObservablePropertyNode getParent() {
        return parent;
    }

    /**
     * @return the clazz
     */
    public Class<?> getClazz() {
        return clazz;
    }

    /**
     * @return the children
     */
    public Map<String, StixObservablePropertyNode> getChildren() {
        return children;
    }

    public void addChild(String pathName, StixObservablePropertyNode stixPath) {
        children.put(pathName, stixPath);
    }

    /**
     * @return the reference
     */
    public boolean isReference() {
        return reference;
    }

    /**
     * Gets every parent from this node represented as an ordered (oldest to
     * newest) list.
     *
     * @return
     */
    public List<StixObservablePropertyNode> getPath() {
        List<StixObservablePropertyNode> path = new ArrayList<>();
        StixObservablePropertyNode node = this;

        while (node != null) {
            path.add(0, node);
            node = node.getParent();
        }

        return path;
    }

    /**
     * Gets every parent from this node represented as a STIX object path.
     *
     * Ex: process:a_property.name
     *
     * @return
     */
    public String toPath() {
        StringBuilder path = new StringBuilder();
        StixObservablePropertyNode node = this;

        while (node != null) {
            if (!node.isReference()) {
                path.insert(0, node.getName() + (node.getParent() == null ? ":" : "."));
            }
            node = node.getParent();
        }

        return path.toString().substring(0, path.length() - 1);
    }

    @Override
    public String toString() {
        StringBuilder path = new StringBuilder();
        StixObservablePropertyNode node = this;

        while (node != null) {
            path.insert(0, node.getName() + "->");
            node = node.getParent();
        }

        return path.toString().substring(0, path.length() - 2);
    }

}
