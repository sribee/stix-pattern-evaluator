package design.unstructured.stix.evaluator.grammar;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link StixPatternParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface StixPatternVisitor<T> extends ParseTreeVisitor<T> {

    /**
     * Visit a parse tree produced by {@link StixPatternParser#pattern}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitPattern(StixPatternParser.PatternContext ctx);

    /**
     * Visit a parse tree produced by
     * {@link StixPatternParser#observationExpressions}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitObservationExpressions(StixPatternParser.ObservationExpressionsContext ctx);

    /**
     * Visit a parse tree produced by
     * {@link StixPatternParser#observationExpressionOr}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitObservationExpressionOr(StixPatternParser.ObservationExpressionOrContext ctx);

    /**
     * Visit a parse tree produced by
     * {@link StixPatternParser#observationExpressionAnd}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitObservationExpressionAnd(StixPatternParser.ObservationExpressionAndContext ctx);

    /**
     * Visit a parse tree produced by the {@code observationExpressionRepeated}
     * labeled alternative in {@link StixPatternParser#observationExpression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitObservationExpressionRepeated(StixPatternParser.ObservationExpressionRepeatedContext ctx);

    /**
     * Visit a parse tree produced by the {@code observationExpressionSimple}
     * labeled alternative in {@link StixPatternParser#observationExpression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitObservationExpressionSimple(StixPatternParser.ObservationExpressionSimpleContext ctx);

    /**
     * Visit a parse tree produced by the {@code observationExpressionCompound}
     * labeled alternative in {@link StixPatternParser#observationExpression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitObservationExpressionCompound(StixPatternParser.ObservationExpressionCompoundContext ctx);

    /**
     * Visit a parse tree produced by the {@code observationExpressionWithin}
     * labeled alternative in {@link StixPatternParser#observationExpression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitObservationExpressionWithin(StixPatternParser.ObservationExpressionWithinContext ctx);

    /**
     * Visit a parse tree produced by the {@code observationExpressionStartStop}
     * labeled alternative in {@link StixPatternParser#observationExpression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitObservationExpressionStartStop(StixPatternParser.ObservationExpressionStartStopContext ctx);

    /**
     * Visit a parse tree produced by the {@code comparisonExpressionAnd_}
     * labeled alternative in {@link StixPatternParser#comparisonExpression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitComparisonExpressionAnd_(StixPatternParser.ComparisonExpressionAnd_Context ctx);

    /**
     * Visit a parse tree produced by the {@code comparisonExpressionOred}
     * labeled alternative in {@link StixPatternParser#comparisonExpression}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitComparisonExpressionOred(StixPatternParser.ComparisonExpressionOredContext ctx);

    /**
     * Visit a parse tree produced by the
     * {@code comparisonExpressionAndPropTest} labeled alternative in
     * {@link StixPatternParser#comparisonExpressionAnd}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitComparisonExpressionAndPropTest(StixPatternParser.ComparisonExpressionAndPropTestContext ctx);

    /**
     * Visit a parse tree produced by the {@code comparisonExpressionAnded}
     * labeled alternative in {@link StixPatternParser#comparisonExpressionAnd}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitComparisonExpressionAnded(StixPatternParser.ComparisonExpressionAndedContext ctx);

    /**
     * Visit a parse tree produced by the {@code propTestEqual} labeled
     * alternative in {@link StixPatternParser#propTest}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitPropTestEqual(StixPatternParser.PropTestEqualContext ctx);

    /**
     * Visit a parse tree produced by the {@code propTestOrder} labeled
     * alternative in {@link StixPatternParser#propTest}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitPropTestOrder(StixPatternParser.PropTestOrderContext ctx);

    /**
     * Visit a parse tree produced by the {@code propTestSet} labeled
     * alternative in {@link StixPatternParser#propTest}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitPropTestSet(StixPatternParser.PropTestSetContext ctx);

    /**
     * Visit a parse tree produced by the {@code propTestLike} labeled
     * alternative in {@link StixPatternParser#propTest}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitPropTestLike(StixPatternParser.PropTestLikeContext ctx);

    /**
     * Visit a parse tree produced by the {@code propTestRegex} labeled
     * alternative in {@link StixPatternParser#propTest}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitPropTestRegex(StixPatternParser.PropTestRegexContext ctx);

    /**
     * Visit a parse tree produced by the {@code propTestIsSubset} labeled
     * alternative in {@link StixPatternParser#propTest}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitPropTestIsSubset(StixPatternParser.PropTestIsSubsetContext ctx);

    /**
     * Visit a parse tree produced by the {@code propTestIsSuperset} labeled
     * alternative in {@link StixPatternParser#propTest}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitPropTestIsSuperset(StixPatternParser.PropTestIsSupersetContext ctx);

    /**
     * Visit a parse tree produced by the {@code propTestParen} labeled
     * alternative in {@link StixPatternParser#propTest}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitPropTestParen(StixPatternParser.PropTestParenContext ctx);

    /**
     * Visit a parse tree produced by the {@code propTestExists} labeled
     * alternative in {@link StixPatternParser#propTest}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitPropTestExists(StixPatternParser.PropTestExistsContext ctx);

    /**
     * Visit a parse tree produced by
     * {@link StixPatternParser#orderingComparator}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitOrderingComparator(StixPatternParser.OrderingComparatorContext ctx);

    /**
     * Visit a parse tree produced by {@link StixPatternParser#stringLiteral}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitStringLiteral(StixPatternParser.StringLiteralContext ctx);

    /**
     * Visit a parse tree produced by
     * {@link StixPatternParser#startStopQualifier}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitStartStopQualifier(StixPatternParser.StartStopQualifierContext ctx);

    /**
     * Visit a parse tree produced by {@link StixPatternParser#withinQualifier}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitWithinQualifier(StixPatternParser.WithinQualifierContext ctx);

    /**
     * Visit a parse tree produced by
     * {@link StixPatternParser#repeatedQualifier}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitRepeatedQualifier(StixPatternParser.RepeatedQualifierContext ctx);

    /**
     * Visit a parse tree produced by {@link StixPatternParser#objectPath}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitObjectPath(StixPatternParser.ObjectPathContext ctx);

    /**
     * Visit a parse tree produced by {@link StixPatternParser#objectType}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitObjectType(StixPatternParser.ObjectTypeContext ctx);

    /**
     * Visit a parse tree produced by
     * {@link StixPatternParser#firstPathComponent}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitFirstPathComponent(StixPatternParser.FirstPathComponentContext ctx);

    /**
     * Visit a parse tree produced by the {@code indexPathStep} labeled
     * alternative in {@link StixPatternParser#objectPathComponent}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitIndexPathStep(StixPatternParser.IndexPathStepContext ctx);

    /**
     * Visit a parse tree produced by the {@code pathStep} labeled alternative
     * in {@link StixPatternParser#objectPathComponent}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitPathStep(StixPatternParser.PathStepContext ctx);

    /**
     * Visit a parse tree produced by the {@code keyPathStep} labeled
     * alternative in {@link StixPatternParser#objectPathComponent}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitKeyPathStep(StixPatternParser.KeyPathStepContext ctx);

    /**
     * Visit a parse tree produced by {@link StixPatternParser#setLiteral}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitSetLiteral(StixPatternParser.SetLiteralContext ctx);

    /**
     * Visit a parse tree produced by
     * {@link StixPatternParser#primitiveLiteral}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitPrimitiveLiteral(StixPatternParser.PrimitiveLiteralContext ctx);

    /**
     * Visit a parse tree produced by
     * {@link StixPatternParser#orderableLiteral}.
     *
     * @param ctx the parse tree
     * @return the visitor result
     */
    T visitOrderableLiteral(StixPatternParser.OrderableLiteralContext ctx);
}
