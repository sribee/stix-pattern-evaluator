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
public class CombinedObservationExpression extends BaseObservationExpression implements ExpressionEvaluator {

    private final BaseObservationExpression firstExpression;

    private final BaseObservationExpression secondExpression;

    private final ObservationOperators operator;

    CombinedObservationExpression(BaseObservationExpression firstExpression, BaseObservationExpression secondExpression, ObservationOperators operator) {
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
        return "CombinedObservationExpression(" + this.getFirstExpression() + ", " + this.getSecondExpression() + ", " + this.getOperator() + ")";
    }
}
