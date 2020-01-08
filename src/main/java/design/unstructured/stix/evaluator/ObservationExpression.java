/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
