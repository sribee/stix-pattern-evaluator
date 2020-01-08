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
public class EvaluatedComparisonExpression extends BaseComparisonExpression implements ExpressionEvaluator {

    private final boolean value;

    public EvaluatedComparisonExpression(final boolean value) {
        this.value = value;
    }

    @Override
    public boolean evaluate() {
        return value;
    }
}
