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
package design.unstructured.stix.evaluator;

/**
 *
 * @author ccarv
 */
public class ComparisonExpression extends BaseComparisonExpression implements ExpressionEvaluator {

    private final String objectPath;

    private final Object value;

    private final ComparisonComparators comparator;

    private final Boolean negated;

    private Boolean evaluation = null;

    ComparisonExpression(String objectPath, Object value, ComparisonComparators comparator, Boolean negated) {
        this.objectPath = objectPath;
        this.value = value;
        this.comparator = comparator;
        this.negated = negated;
    }

    /**
     * @return the objectPath
     */
    public String getObjectPath() {
        return objectPath;
    }

    /**
     * @return the value
     */
    public Object getValue() {
        return value;
    }

    /**
     * @return the comparator
     */
    public ComparisonComparators getComparator() {
        return comparator;
    }

    /**
     * @return the negated
     */
    public Boolean isNegated() {
        return negated;
    }

    public void setEvaluation(Boolean value) {
        evaluation = value;
    }

    @Override
    public boolean evaluate() {
        return evaluation;
    }

    @Override
    public String toString() {
        return "ComparisonExpression(" + this.getObjectPath() + ", " + this.getComparator() + ", " + this.getValue()
                + ")";
    }

}
