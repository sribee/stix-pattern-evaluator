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
public class CombinedObservationExpression extends BaseObservationExpression implements ExpressionEvaluator {

    private final BaseObservationExpression firstExpression;

    private final BaseObservationExpression secondExpression;

    private final ObservationOperators operator;

    CombinedObservationExpression(BaseObservationExpression firstExpression, BaseObservationExpression secondExpression,
            ObservationOperators operator) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.operator = operator;
    }

    /**
     * @return the firstExpression
     */
    public BaseObservationExpression getFirstExpression() {
        return firstExpression;
    }

    /**
     * @return the secondExpression
     */
    public BaseObservationExpression getSecondExpression() {
        return secondExpression;
    }

    /**
     * @return the operator
     */
    public ObservationOperators getOperator() {
        return operator;
    }

    @Override
    public boolean evaluate() {
        ExpressionEvaluator firstExp = (ExpressionEvaluator) getFirstExpression();
        ExpressionEvaluator secondExp = (ExpressionEvaluator) getSecondExpression();

        if (operator.equals(ObservationOperators.And)) {
            return firstExp.evaluate() && secondExp.evaluate();
        } else {
            return firstExp.evaluate() || secondExp.evaluate();
        }
    }

    @Override
    public String toString() {
        return "CombinedObservationExpression(" + this.getFirstExpression() + ", " + this.getSecondExpression() + ", "
                + this.getOperator() + ")";
    }
}
