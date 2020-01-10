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
package design.unstructured.stix.evaluator;

/**
 *
 * @author ccarv
 */
public class CombinedComparisonExpression extends BaseComparisonExpression implements ExpressionEvaluator {

    private final BaseComparisonExpression firstExpression;

    private final BaseComparisonExpression secondExpression;

    private final ComparisonExpressionOperators operator;

    CombinedComparisonExpression(BaseComparisonExpression firstExpression, BaseComparisonExpression secondExpression,
            ComparisonExpressionOperators operator) {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.operator = operator;
    }

    /**
     * @return the firstExpression
     */
    public BaseComparisonExpression getFirstExpression() {
        return firstExpression;
    }

    /**
     * @return the secondExpression
     */
    public BaseComparisonExpression getSecondExpression() {
        return secondExpression;
    }

    /**
     * @return the operator
     */
    public ComparisonExpressionOperators getOperator() {
        return operator;
    }

    @Override
    public boolean evaluate() {
        ExpressionEvaluator firstExp = (ExpressionEvaluator) getFirstExpression();
        ExpressionEvaluator secondExp = (ExpressionEvaluator) getSecondExpression();

        if (operator.equals(ComparisonExpressionOperators.And)) {
            return firstExp.evaluate() && secondExp.evaluate();
        } else {
            return firstExp.evaluate() || secondExp.evaluate();
        }
    }

    @Override
    public String toString() {
        return "CombinedComparisonExpression(" + this.getFirstExpression() + ", " + this.getSecondExpression() + ", "
                + this.getOperator() + ")";
    }

}
