/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package design.unstructured.stix.evaluator;

import org.antlr.v4.runtime.tree.TerminalNode;

/**
 *
 * @author ccarv
 */
public class ComparisonExpression extends BaseComparisonExpression implements ExpressionEvaluator {

    private final String objectPath;

    private final Object value;

    private final ComparisonComparators comparator;

    private final TerminalNode negated;

    private Boolean evaluation = null;

    ComparisonExpression(String objectPath, Object value, ComparisonComparators comparator, TerminalNode negated) {
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
    public TerminalNode isNegated() {
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
        return "ComparisonExpression(" + this.getObjectPath() + ", " + this.getComparator() + ", " + this.getValue() + ")";
    }

}
