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
package design.unstructured.stix.evaluator;

/**
 *
 * @author ccarv
 */
public class Pattern implements ExpressionEvaluator {

    private final BaseObservationExpression expression;

    Pattern(BaseObservationExpression expression) {
        this.expression = expression;
    }

    /**
     * @return the expression
     */
    public BaseObservationExpression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return "Pattern[" + this.getExpression() + "]";
    }

    @Override
    public boolean evaluate() {
        return ((ExpressionEvaluator) expression).evaluate();
    }
}
