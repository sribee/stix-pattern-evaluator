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

import design.unstructured.stix.evaluator.mapper.ObjectPathResolver;
import design.unstructured.stix.evaluator.mapper.StixMapperException;
import java.util.Set;

/**
 * Evaluates a pattern object. An object resolver (use the
 * {@link ObjectPathResolver}) is required to supply an object path value. If a
 * resolver is not available, this class will do nothing.
 * 
 */
@SuppressWarnings({ "rawtypes" })
public class PatternEvaluator {

    private final Pattern pattern;

    private final ObjectPathResolver resolver;

    private final Object object;

    /**
     * Use the ObjectPathResolver interface to resolve the patterns object path.
     * This value will be used to compare the literal.
     *
     * @param pattern
     * @param resolver
     * @throws PatternEvaluatorException
     */
    public PatternEvaluator(Pattern pattern, ObjectPathResolver resolver, Object object)
            throws PatternEvaluatorException {
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

    /**
     * Runs the evaluation on the pattern and returns the result of the expression.
     * 
     * @return
     * @throws StixMapperException
     * @throws PatternEvaluatorException
     */
    public Boolean get() throws StixMapperException, PatternEvaluatorException {
        expressionIterator(pattern.getExpression());

        return pattern.evaluate();
    }

    private void expressionIterator(BaseObservationExpression expression)
            throws StixMapperException, PatternEvaluatorException {
        if (expression.getClass().equals(CombinedObservationExpression.class)) {
            CombinedObservationExpression combinedObsExp = (CombinedObservationExpression) expression;

            expressionIterator(combinedObsExp.getFirstExpression());
            expressionIterator(combinedObsExp.getSecondExpression());

        } else if (expression.getClass().equals(ObservationExpression.class)) {
            ObservationExpression obsExp = (ObservationExpression) expression;

            expressionIterator(obsExp.getComparisonExpression());
        }
    }

    private void expressionIterator(BaseComparisonExpression expression)
            throws StixMapperException, PatternEvaluatorException {

        // If our expression is not a ComparisonExpression, evaluate our combined
        // expressions
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
                throw new PatternEvaluatorException(
                        "Comparator " + comparisonExpression.getComparator() + " is not supported.");
            }
            }
        }
    }
}
