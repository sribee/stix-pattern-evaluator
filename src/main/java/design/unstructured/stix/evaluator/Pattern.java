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
