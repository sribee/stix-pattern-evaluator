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
public class CombinedComparisonExpression extends BaseComparisonExpression implements ExpressionEvaluator {

    private final BaseComparisonExpression firstExpression;

    private final BaseComparisonExpression secondExpression;

    private final ComparisonExpressionOperators operator;

    CombinedComparisonExpression(BaseComparisonExpression firstExpression, BaseComparisonExpression secondExpression, ComparisonExpressionOperators operator) {
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
        return "CombinedComparisonExpression(" + this.getFirstExpression() + ", " + this.getSecondExpression() + ", " + this.getOperator() + ")";
    }

}
