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
public class ObservationExpression extends BaseObservationExpression implements ExpressionEvaluator {

    private final BaseComparisonExpression comparisonExpression;

    ObservationExpression(BaseComparisonExpression comparisonExpression) {
        this.comparisonExpression = comparisonExpression;
    }

    /**
     * @return the comparisonExpression
     */
    public BaseComparisonExpression getComparisonExpression() {
        return comparisonExpression;
    }

    @Override
    public boolean evaluate() {
        return ((ExpressionEvaluator) comparisonExpression).evaluate();
    }

    @Override
    public String toString() {
        return "ObservationExpression(" + this.getComparisonExpression() + ")";
    }
}
