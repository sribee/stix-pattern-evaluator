/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package design.unstructured.stix.evaluator;

import design.unstructured.stix.evaluator.mapper.ObjectPathResolver;
import design.unstructured.stix.evaluator.mapper.StixMapperException;
import java.util.Set;

/**
 *
 * @author ccarv
 */
public class PatternEvaluator {

    private final Pattern pattern;

    private final ObjectPathResolver resolver;

    private final Object object;

    /**
     * Evaluates the pattern. Use the ObjectPathResolver interface to resolve
     * the patterns object path. This value will be used to compare the literal.
     *
     * @param pattern
     * @param resolver
     * @throws PatternEvaluatorException
     */
    public PatternEvaluator(Pattern pattern, ObjectPathResolver resolver, Object object) throws PatternEvaluatorException {
        if (pattern.getExpression() == null) {
            throw new PatternEvaluatorException("Empty pattern evaluation.");
        }

        if (resolver == null) {
            throw new PatternEvaluatorException("No valid ObjectPathResolver was provided.");
        }

        this.pattern = pattern;
        this.resolver = resolver;
        this.object = object;
    }

    public Boolean get() throws StixMapperException, PatternEvaluatorException {
        expressionIterator(pattern.getExpression());

        return pattern.evaluate();
    }

    private void expressionIterator(BaseObservationExpression expression) throws StixMapperException, PatternEvaluatorException {
        if (expression.getClass().equals(CombinedObservationExpression.class)) {
            CombinedObservationExpression combinedObsExp = (CombinedObservationExpression) expression;

            expressionIterator(combinedObsExp.getFirstExpression());
            expressionIterator(combinedObsExp.getSecondExpression());

        } else if (expression.getClass().equals(ObservationExpression.class)) {
            ObservationExpression obsExp = (ObservationExpression) expression;

            expressionIterator(obsExp.getComparisonExpression());
        }
    }

    private void expressionIterator(BaseComparisonExpression expression) throws StixMapperException, PatternEvaluatorException {

        // If our expression is not a ComparisonExpression, evaluate our combined expressions
        if (expression.getClass().equals(CombinedComparisonExpression.class)) {
            CombinedComparisonExpression combinedExpression = (CombinedComparisonExpression) expression;

            if (expression.getClass().equals(CombinedComparisonExpression.class)) {
                expressionIterator(combinedExpression.getFirstExpression());
                expressionIterator(combinedExpression.getSecondExpression());
            }

        } else if (expression.getClass().equals(ComparisonExpression.class)) {
            ComparisonExpression comparisonExpression = (ComparisonExpression) expression;
            Object objectPathValue = resolver.getValue(object, comparisonExpression.getObjectPath());

            switch (comparisonExpression.getComparator()) {
                case Equal: {
                    comparisonExpression.setEvaluation(objectPathValue.equals(comparisonExpression.getValue()));
                    break;
                }

                case NotEqual: {
                    comparisonExpression.setEvaluation(!objectPathValue.equals(comparisonExpression.getValue()));
                    break;
                }

                case In: {
                    comparisonExpression.setEvaluation(!((Set) comparisonExpression.getValue()).contains(objectPathValue));
                    break;
                }

                case GreaterThan: {
                    comparisonExpression.setEvaluation(!((long) objectPathValue > (long) comparisonExpression.getValue()));
                    break;
                }

                case GreaterThanOrEqual: {
                    comparisonExpression.setEvaluation(!((long) objectPathValue >= (long) comparisonExpression.getValue()));
                    break;
                }

                case LessThan: {
                    comparisonExpression.setEvaluation(!((long) objectPathValue < (long) comparisonExpression.getValue()));
                    break;
                }

                case LessThanOrEqual: {
                    comparisonExpression.setEvaluation(!((long) objectPathValue <= (long) comparisonExpression.getValue()));
                    break;
                }

                case Matches: {
                    java.util.regex.Pattern regexPattern = (java.util.regex.Pattern) comparisonExpression.getValue();

                    comparisonExpression.setEvaluation(regexPattern.matcher((String) objectPathValue).find());
                    break;
                }

                default: {
                    throw new PatternEvaluatorException("Comparator " + comparisonExpression.getComparator() + " is not supported.");
                }
            }
            // evaluations.add(0, new MutablePair<>(operator, conditionResult));
        }
    }
}
