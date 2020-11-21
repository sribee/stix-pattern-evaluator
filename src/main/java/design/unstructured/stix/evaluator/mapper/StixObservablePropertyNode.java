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
    StixObservablePropertyNode(StixObservablePropertyNode parent, String name, Field field, Class<?> clazz,
            boolean isReference) {
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
     * Gets every parent from this node represented as an ordered (oldest to newest)
     * list.
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
