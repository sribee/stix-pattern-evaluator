/*
 * stix-pattern-evaluator Copyright (C) 2020 - Christopher Carver
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package design.unstructured.stix.evaluator;

import design.unstructured.stix.evaluator.mapper.ObjectPathResolver;
import design.unstructured.stix.evaluator.mapper.StixMappingException;
import java.util.Set;

/**
 * Evaluates the pattern expression tree. An object resolver (use the {@link ObjectPathResolver}) is
 * required to supply an object path value. If a resolver is not available, this class will do
 * nothing.
 * 
 */
@SuppressWarnings({"unchecked"})
public class PatternEvaluator implements ComparisonEvaluator {

    private final Pattern pattern;

    private final ObjectPathResolver resolver;

    private final Object object;

    private final ComparisonEvaluator comparisonEvaluator;

    /**
     * {@code comparisonEvaluator} defaults to {@link PatternEvaluator}.
     *
     * @see #PatternEvaluator(Pattern, ObjectPathResolver, ComparisonEvaluator, Object)
     */
    public PatternEvaluator(Pattern pattern, ObjectPathResolver resolver, Object object) throws PatternEvaluatorException {
        this(pattern, resolver, null, object);
    }

    /**
     * Use the {@code ObjectPathResolver} interface to resolve the patterns object path. An expression
     * will be evaluated regardless if the resolver is able to provide a value.
     * 
     * Passing an empty expression tree or passing a null resolver will result in an exception.
     *
     * @param pattern
     * @param resolver
     * @throws PatternEvaluatorException
     */
    public PatternEvaluator(Pattern pattern, ObjectPathResolver resolver, ComparisonEvaluator comparisonEvaluator, Object object)
            throws PatternEvaluatorException {
        if (pattern == null || pattern.getExpression() == null) {
            throw new PatternEvaluatorException("Empty pattern evaluation.");
        }

        if (resolver == null) {
            throw new PatternEvaluatorException("No valid ObjectPathResolver was provided.");
        }

        this.pattern = pattern;
        this.resolver = resolver;
        this.comparisonEvaluator = (comparisonEvaluator == null ? this : comparisonEvaluator);
        this.object = object;
    }

    /**
     * Runs the evaluation on the pattern and returns the result of the expression. The execution
     * depends on the complexity and depth of the expression tree.
     * 
     * @return the result of the expression in this pattern
     * @throws StixMappingException
     * @throws PatternEvaluatorException
     */
    public Boolean get() throws StixMappingException, PatternEvaluatorException {
        evaluator(pattern.getExpression());

        return pattern.evaluate();
    }

    private void evaluator(BaseObservationExpression expression) throws StixMappingException, PatternEvaluatorException {
        if (expression.getClass().equals(CombinedObservationExpression.class)) {
            CombinedObservationExpression combinedObsExp = (CombinedObservationExpression) expression;

            evaluator(combinedObsExp.getFirstExpression());
            evaluator(combinedObsExp.getSecondExpression());

        } else if (expression.getClass().equals(ObservationExpression.class)) {
            ObservationExpression obsExp = (ObservationExpression) expression;

            evaluator(obsExp.getComparisonExpression());
        }
    }

    private void evaluator(BaseComparisonExpression expression) throws StixMappingException, PatternEvaluatorException {

        // If our expression is not a ComparisonExpression, evaluate our combined
        // expressions
        if (expression.getClass().equals(CombinedComparisonExpression.class)) {
            CombinedComparisonExpression combinedExpression = (CombinedComparisonExpression) expression;

            if (expression.getClass().equals(CombinedComparisonExpression.class)) {
                evaluator(combinedExpression.getFirstExpression());
                evaluator(combinedExpression.getSecondExpression());
            }

        } else if (expression.getClass().equals(ComparisonExpression.class)) {
            ComparisonExpression comparisonExpression = (ComparisonExpression) expression;
            Object contextObject = resolver.getValue(object, comparisonExpression.getObjectPath());
            Object patternObject = comparisonExpression.getValue();

            // The getValue(...) may return null, in which case the lookup failed for whatever reason. STIX v2.1
            // has no specification on patterns with null comparison. It is safe to assume we can set our
            // evaluation to false and exit to avoid NPE's.
            if (contextObject == null) {
                comparisonExpression.setEvaluation(false);
                return;
            }

            switch (comparisonExpression.getComparator()) {
                case Equal: {
                    comparisonExpression.setEvaluation(comparisonEvaluator.isEqual(contextObject, patternObject));
                    break;
                }

                case NotEqual: {
                    comparisonExpression.setEvaluation(comparisonEvaluator.isNotEqual(contextObject, patternObject));
                    break;
                }

                case In: {
                    comparisonExpression.setEvaluation(comparisonEvaluator.isIn(contextObject, (Set<Object>) patternObject));
                    break;
                }

                case GreaterThan: {
                    comparisonExpression.setEvaluation(comparisonEvaluator.isGreaterThan((Number) contextObject, (Number) patternObject));
                    break;
                }

                case GreaterThanOrEqual: {
                    comparisonExpression.setEvaluation(comparisonEvaluator.isGreaterThanOrEqual((Number) contextObject, (Number) patternObject));
                    break;
                }

                case LessThan: {
                    comparisonExpression.setEvaluation(comparisonEvaluator.isLessThan((Number) contextObject, (Number) patternObject));
                    break;
                }

                case LessThanOrEqual: {
                    comparisonExpression.setEvaluation(comparisonEvaluator.isLessThanOrEqual((Number) contextObject, (Number) patternObject));
                    break;
                }

                case Matches: {
                    comparisonExpression.setEvaluation(comparisonEvaluator.matches(contextObject, patternObject));
                    break;
                }

                default: {
                    throw new PatternEvaluatorException("Comparator " + comparisonExpression.getComparator() + " is not supported.");
                }
            }
        }
    }

    @Override
    public boolean isEqual(Object contextObject, Object patternObject) {
        return contextObject.equals(patternObject);
    }

    @Override
    public boolean isNotEqual(Object contextObject, Object patternObject) {
        return !contextObject.equals(patternObject);
    }

    @Override
    public boolean isIn(Object contextObject, Set<Object> patternObject) {
        return patternObject.contains(contextObject);
    }

    @Override
    public boolean isGreaterThan(Number contextObject, Number patternObject) {
        return contextObject.doubleValue() > patternObject.doubleValue();
    }

    @Override
    public boolean isGreaterThanOrEqual(Number contextObject, Number patternObject) {
        return contextObject.doubleValue() >= patternObject.doubleValue();
    }

    @Override
    public boolean isLessThan(Number contextObject, Number patternObject) {
        return contextObject.doubleValue() < patternObject.doubleValue();
    }

    @Override
    public boolean isLessThanOrEqual(Number contextObject, Number patternObject) {
        return contextObject.doubleValue() <= patternObject.doubleValue();
    }

    @Override
    public boolean matches(Object contextObject, Object patternObject) {
        java.util.regex.Pattern regexPattern = (java.util.regex.Pattern) patternObject;

        return regexPattern.matcher((String) contextObject).find();
    }

}
