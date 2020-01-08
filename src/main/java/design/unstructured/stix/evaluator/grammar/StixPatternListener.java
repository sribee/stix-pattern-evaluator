package design.unstructured.stix.evaluator.grammar;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link StixPatternParser}.
 */
public interface StixPatternListener extends ParseTreeListener {

    /**
     * Enter a parse tree produced by {@link StixPatternParser#pattern}.
     *
     * @param ctx the parse tree
     */
    void enterPattern(StixPatternParser.PatternContext ctx);

    /**
     * Exit a parse tree produced by {@link StixPatternParser#pattern}.
     *
     * @param ctx the parse tree
     */
    void exitPattern(StixPatternParser.PatternContext ctx);

    /**
     * Enter a parse tree produced by
     * {@link StixPatternParser#observationExpressions}.
     *
     * @param ctx the parse tree
     */
    void enterObservationExpressions(StixPatternParser.ObservationExpressionsContext ctx);

    /**
     * Exit a parse tree produced by
     * {@link StixPatternParser#observationExpressions}.
     *
     * @param ctx the parse tree
     */
    void exitObservationExpressions(StixPatternParser.ObservationExpressionsContext ctx);

    /**
     * Enter a parse tree produced by
     * {@link StixPatternParser#observationExpressionOr}.
     *
     * @param ctx the parse tree
     */
    void enterObservationExpressionOr(StixPatternParser.ObservationExpressionOrContext ctx);

    /**
     * Exit a parse tree produced by
     * {@link StixPatternParser#observationExpressionOr}.
     *
     * @param ctx the parse tree
     */
    void exitObservationExpressionOr(StixPatternParser.ObservationExpressionOrContext ctx);

    /**
     * Enter a parse tree produced by
     * {@link StixPatternParser#observationExpressionAnd}.
     *
     * @param ctx the parse tree
     */
    void enterObservationExpressionAnd(StixPatternParser.ObservationExpressionAndContext ctx);

    /**
     * Exit a parse tree produced by
     * {@link StixPatternParser#observationExpressionAnd}.
     *
     * @param ctx the parse tree
     */
    void exitObservationExpressionAnd(StixPatternParser.ObservationExpressionAndContext ctx);

    /**
     * Enter a parse tree produced by the {@code observationExpressionRepeated}
     * labeled alternative in {@link StixPatternParser#observationExpression}.
     *
     * @param ctx the parse tree
     */
    void enterObservationExpressionRepeated(StixPatternParser.ObservationExpressionRepeatedContext ctx);

    /**
     * Exit a parse tree produced by the {@code observationExpressionRepeated}
     * labeled alternative in {@link StixPatternParser#observationExpression}.
     *
     * @param ctx the parse tree
     */
    void exitObservationExpressionRepeated(StixPatternParser.ObservationExpressionRepeatedContext ctx);

    /**
     * Enter a parse tree produced by the {@code observationExpressionSimple}
     * labeled alternative in {@link StixPatternParser#observationExpression}.
     *
     * @param ctx the parse tree
     */
    void enterObservationExpressionSimple(StixPatternParser.ObservationExpressionSimpleContext ctx);

    /**
     * Exit a parse tree produced by the {@code observationExpressionSimple}
     * labeled alternative in {@link StixPatternParser#observationExpression}.
     *
     * @param ctx the parse tree
     */
    void exitObservationExpressionSimple(StixPatternParser.ObservationExpressionSimpleContext ctx);

    /**
     * Enter a parse tree produced by the {@code observationExpressionCompound}
     * labeled alternative in {@link StixPatternParser#observationExpression}.
     *
     * @param ctx the parse tree
     */
    void enterObservationExpressionCompound(StixPatternParser.ObservationExpressionCompoundContext ctx);

    /**
     * Exit a parse tree produced by the {@code observationExpressionCompound}
     * labeled alternative in {@link StixPatternParser#observationExpression}.
     *
     * @param ctx the parse tree
     */
    void exitObservationExpressionCompound(StixPatternParser.ObservationExpressionCompoundContext ctx);

    /**
     * Enter a parse tree produced by the {@code observationExpressionWithin}
     * labeled alternative in {@link StixPatternParser#observationExpression}.
     *
     * @param ctx the parse tree
     */
    void enterObservationExpressionWithin(StixPatternParser.ObservationExpressionWithinContext ctx);

    /**
     * Exit a parse tree produced by the {@code observationExpressionWithin}
     * labeled alternative in {@link StixPatternParser#observationExpression}.
     *
     * @param ctx the parse tree
     */
    void exitObservationExpressionWithin(StixPatternParser.ObservationExpressionWithinContext ctx);

    /**
     * Enter a parse tree produced by the {@code observationExpressionStartStop}
     * labeled alternative in {@link StixPatternParser#observationExpression}.
     *
     * @param ctx the parse tree
     */
    void enterObservationExpressionStartStop(StixPatternParser.ObservationExpressionStartStopContext ctx);

    /**
     * Exit a parse tree produced by the {@code observationExpressionStartStop}
     * labeled alternative in {@link StixPatternParser#observationExpression}.
     *
     * @param ctx the parse tree
     */
    void exitObservationExpressionStartStop(StixPatternParser.ObservationExpressionStartStopContext ctx);

    /**
     * Enter a parse tree produced by the {@code comparisonExpressionAnd_}
     * labeled alternative in {@link StixPatternParser#comparisonExpression}.
     *
     * @param ctx the parse tree
     */
    void enterComparisonExpressionAnd_(StixPatternParser.ComparisonExpressionAnd_Context ctx);

    /**
     * Exit a parse tree produced by the {@code comparisonExpressionAnd_}
     * labeled alternative in {@link StixPatternParser#comparisonExpression}.
     *
     * @param ctx the parse tree
     */
    void exitComparisonExpressionAnd_(StixPatternParser.ComparisonExpressionAnd_Context ctx);

    /**
     * Enter a parse tree produced by the {@code comparisonExpressionOred}
     * labeled alternative in {@link StixPatternParser#comparisonExpression}.
     *
     * @param ctx the parse tree
     */
    void enterComparisonExpressionOred(StixPatternParser.ComparisonExpressionOredContext ctx);

    /**
     * Exit a parse tree produced by the {@code comparisonExpressionOred}
     * labeled alternative in {@link StixPatternParser#comparisonExpression}.
     *
     * @param ctx the parse tree
     */
    void exitComparisonExpressionOred(StixPatternParser.ComparisonExpressionOredContext ctx);

    /**
     * Enter a parse tree produced by the
     * {@code comparisonExpressionAndPropTest} labeled alternative in
     * {@link StixPatternParser#comparisonExpressionAnd}.
     *
     * @param ctx the parse tree
     */
    void enterComparisonExpressionAndPropTest(StixPatternParser.ComparisonExpressionAndPropTestContext ctx);

    /**
     * Exit a parse tree produced by the {@code comparisonExpressionAndPropTest}
     * labeled alternative in {@link StixPatternParser#comparisonExpressionAnd}.
     *
     * @param ctx the parse tree
     */
    void exitComparisonExpressionAndPropTest(StixPatternParser.ComparisonExpressionAndPropTestContext ctx);

    /**
     * Enter a parse tree produced by the {@code comparisonExpressionAnded}
     * labeled alternative in {@link StixPatternParser#comparisonExpressionAnd}.
     *
     * @param ctx the parse tree
     */
    void enterComparisonExpressionAnded(StixPatternParser.ComparisonExpressionAndedContext ctx);

    /**
     * Exit a parse tree produced by the {@code comparisonExpressionAnded}
     * labeled alternative in {@link StixPatternParser#comparisonExpressionAnd}.
     *
     * @param ctx the parse tree
     */
    void exitComparisonExpressionAnded(StixPatternParser.ComparisonExpressionAndedContext ctx);

    /**
     * Enter a parse tree produced by the {@code propTestEqual} labeled
     * alternative in {@link StixPatternParser#propTest}.
     *
     * @param ctx the parse tree
     */
    void enterPropTestEqual(StixPatternParser.PropTestEqualContext ctx);

    /**
     * Exit a parse tree produced by the {@code propTestEqual} labeled
     * alternative in {@link StixPatternParser#propTest}.
     *
     * @param ctx the parse tree
     */
    void exitPropTestEqual(StixPatternParser.PropTestEqualContext ctx);

    /**
     * Enter a parse tree produced by the {@code propTestOrder} labeled
     * alternative in {@link StixPatternParser#propTest}.
     *
     * @param ctx the parse tree
     */
    void enterPropTestOrder(StixPatternParser.PropTestOrderContext ctx);

    /**
     * Exit a parse tree produced by the {@code propTestOrder} labeled
     * alternative in {@link StixPatternParser#propTest}.
     *
     * @param ctx the parse tree
     */
    void exitPropTestOrder(StixPatternParser.PropTestOrderContext ctx);

    /**
     * Enter a parse tree produced by the {@code propTestSet} labeled
     * alternative in {@link StixPatternParser#propTest}.
     *
     * @param ctx the parse tree
     */
    void enterPropTestSet(StixPatternParser.PropTestSetContext ctx);

    /**
     * Exit a parse tree produced by the {@code propTestSet} labeled alternative
     * in {@link StixPatternParser#propTest}.
     *
     * @param ctx the parse tree
     */
    void exitPropTestSet(StixPatternParser.PropTestSetContext ctx);

    /**
     * Enter a parse tree produced by the {@code propTestLike} labeled
     * alternative in {@link StixPatternParser#propTest}.
     *
     * @param ctx the parse tree
     */
    void enterPropTestLike(StixPatternParser.PropTestLikeContext ctx);

    /**
     * Exit a parse tree produced by the {@code propTestLike} labeled
     * alternative in {@link StixPatternParser#propTest}.
     *
     * @param ctx the parse tree
     */
    void exitPropTestLike(StixPatternParser.PropTestLikeContext ctx);

    /**
     * Enter a parse tree produced by the {@code propTestRegex} labeled
     * alternative in {@link StixPatternParser#propTest}.
     *
     * @param ctx the parse tree
     */
    void enterPropTestRegex(StixPatternParser.PropTestRegexContext ctx);

    /**
     * Exit a parse tree produced by the {@code propTestRegex} labeled
     * alternative in {@link StixPatternParser#propTest}.
     *
     * @param ctx the parse tree
     */
    void exitPropTestRegex(StixPatternParser.PropTestRegexContext ctx);

    /**
     * Enter a parse tree produced by the {@code propTestIsSubset} labeled
     * alternative in {@link StixPatternParser#propTest}.
     *
     * @param ctx the parse tree
     */
    void enterPropTestIsSubset(StixPatternParser.PropTestIsSubsetContext ctx);

    /**
     * Exit a parse tree produced by the {@code propTestIsSubset} labeled
     * alternative in {@link StixPatternParser#propTest}.
     *
     * @param ctx the parse tree
     */
    void exitPropTestIsSubset(StixPatternParser.PropTestIsSubsetContext ctx);

    /**
     * Enter a parse tree produced by the {@code propTestIsSuperset} labeled
     * alternative in {@link StixPatternParser#propTest}.
     *
     * @param ctx the parse tree
     */
    void enterPropTestIsSuperset(StixPatternParser.PropTestIsSupersetContext ctx);

    /**
     * Exit a parse tree produced by the {@code propTestIsSuperset} labeled
     * alternative in {@link StixPatternParser#propTest}.
     *
     * @param ctx the parse tree
     */
    void exitPropTestIsSuperset(StixPatternParser.PropTestIsSupersetContext ctx);

    /**
     * Enter a parse tree produced by the {@code propTestParen} labeled
     * alternative in {@link StixPatternParser#propTest}.
     *
     * @param ctx the parse tree
     */
    void enterPropTestParen(StixPatternParser.PropTestParenContext ctx);

    /**
     * Exit a parse tree produced by the {@code propTestParen} labeled
     * alternative in {@link StixPatternParser#propTest}.
     *
     * @param ctx the parse tree
     */
    void exitPropTestParen(StixPatternParser.PropTestParenContext ctx);

    /**
     * Enter a parse tree produced by the {@code propTestExists} labeled
     * alternative in {@link StixPatternParser#propTest}.
     *
     * @param ctx the parse tree
     */
    void enterPropTestExists(StixPatternParser.PropTestExistsContext ctx);

    /**
     * Exit a parse tree produced by the {@code propTestExists} labeled
     * alternative in {@link StixPatternParser#propTest}.
     *
     * @param ctx the parse tree
     */
    void exitPropTestExists(StixPatternParser.PropTestExistsContext ctx);

    /**
     * Enter a parse tree produced by
     * {@link StixPatternParser#orderingComparator}.
     *
     * @param ctx the parse tree
     */
    void enterOrderingComparator(StixPatternParser.OrderingComparatorContext ctx);

    /**
     * Exit a parse tree produced by
     * {@link StixPatternParser#orderingComparator}.
     *
     * @param ctx the parse tree
     */
    void exitOrderingComparator(StixPatternParser.OrderingComparatorContext ctx);

    /**
     * Enter a parse tree produced by {@link StixPatternParser#stringLiteral}.
     *
     * @param ctx the parse tree
     */
    void enterStringLiteral(StixPatternParser.StringLiteralContext ctx);

    /**
     * Exit a parse tree produced by {@link StixPatternParser#stringLiteral}.
     *
     * @param ctx the parse tree
     */
    void exitStringLiteral(StixPatternParser.StringLiteralContext ctx);

    /**
     * Enter a parse tree produced by
     * {@link StixPatternParser#startStopQualifier}.
     *
     * @param ctx the parse tree
     */
    void enterStartStopQualifier(StixPatternParser.StartStopQualifierContext ctx);

    /**
     * Exit a parse tree produced by
     * {@link StixPatternParser#startStopQualifier}.
     *
     * @param ctx the parse tree
     */
    void exitStartStopQualifier(StixPatternParser.StartStopQualifierContext ctx);

    /**
     * Enter a parse tree produced by {@link StixPatternParser#withinQualifier}.
     *
     * @param ctx the parse tree
     */
    void enterWithinQualifier(StixPatternParser.WithinQualifierContext ctx);

    /**
     * Exit a parse tree produced by {@link StixPatternParser#withinQualifier}.
     *
     * @param ctx the parse tree
     */
    void exitWithinQualifier(StixPatternParser.WithinQualifierContext ctx);

    /**
     * Enter a parse tree produced by
     * {@link StixPatternParser#repeatedQualifier}.
     *
     * @param ctx the parse tree
     */
    void enterRepeatedQualifier(StixPatternParser.RepeatedQualifierContext ctx);

    /**
     * Exit a parse tree produced by
     * {@link StixPatternParser#repeatedQualifier}.
     *
     * @param ctx the parse tree
     */
    void exitRepeatedQualifier(StixPatternParser.RepeatedQualifierContext ctx);

    /**
     * Enter a parse tree produced by {@link StixPatternParser#objectPath}.
     *
     * @param ctx the parse tree
     */
    void enterObjectPath(StixPatternParser.ObjectPathContext ctx);

    /**
     * Exit a parse tree produced by {@link StixPatternParser#objectPath}.
     *
     * @param ctx the parse tree
     */
    void exitObjectPath(StixPatternParser.ObjectPathContext ctx);

    /**
     * Enter a parse tree produced by {@link StixPatternParser#objectType}.
     *
     * @param ctx the parse tree
     */
    void enterObjectType(StixPatternParser.ObjectTypeContext ctx);

    /**
     * Exit a parse tree produced by {@link StixPatternParser#objectType}.
     *
     * @param ctx the parse tree
     */
    void exitObjectType(StixPatternParser.ObjectTypeContext ctx);

    /**
     * Enter a parse tree produced by
     * {@link StixPatternParser#firstPathComponent}.
     *
     * @param ctx the parse tree
     */
    void enterFirstPathComponent(StixPatternParser.FirstPathComponentContext ctx);

    /**
     * Exit a parse tree produced by
     * {@link StixPatternParser#firstPathComponent}.
     *
     * @param ctx the parse tree
     */
    void exitFirstPathComponent(StixPatternParser.FirstPathComponentContext ctx);

    /**
     * Enter a parse tree produced by the {@code indexPathStep} labeled
     * alternative in {@link StixPatternParser#objectPathComponent}.
     *
     * @param ctx the parse tree
     */
    void enterIndexPathStep(StixPatternParser.IndexPathStepContext ctx);

    /**
     * Exit a parse tree produced by the {@code indexPathStep} labeled
     * alternative in {@link StixPatternParser#objectPathComponent}.
     *
     * @param ctx the parse tree
     */
    void exitIndexPathStep(StixPatternParser.IndexPathStepContext ctx);

    /**
     * Enter a parse tree produced by the {@code pathStep} labeled alternative
     * in {@link StixPatternParser#objectPathComponent}.
     *
     * @param ctx the parse tree
     */
    void enterPathStep(StixPatternParser.PathStepContext ctx);

    /**
     * Exit a parse tree produced by the {@code pathStep} labeled alternative in
     * {@link StixPatternParser#objectPathComponent}.
     *
     * @param ctx the parse tree
     */
    void exitPathStep(StixPatternParser.PathStepContext ctx);

    /**
     * Enter a parse tree produced by the {@code keyPathStep} labeled
     * alternative in {@link StixPatternParser#objectPathComponent}.
     *
     * @param ctx the parse tree
     */
    void enterKeyPathStep(StixPatternParser.KeyPathStepContext ctx);

    /**
     * Exit a parse tree produced by the {@code keyPathStep} labeled alternative
     * in {@link StixPatternParser#objectPathComponent}.
     *
     * @param ctx the parse tree
     */
    void exitKeyPathStep(StixPatternParser.KeyPathStepContext ctx);

    /**
     * Enter a parse tree produced by {@link StixPatternParser#setLiteral}.
     *
     * @param ctx the parse tree
     */
    void enterSetLiteral(StixPatternParser.SetLiteralContext ctx);

    /**
     * Exit a parse tree produced by {@link StixPatternParser#setLiteral}.
     *
     * @param ctx the parse tree
     */
    void exitSetLiteral(StixPatternParser.SetLiteralContext ctx);

    /**
     * Enter a parse tree produced by
     * {@link StixPatternParser#primitiveLiteral}.
     *
     * @param ctx the parse tree
     */
    void enterPrimitiveLiteral(StixPatternParser.PrimitiveLiteralContext ctx);

    /**
     * Exit a parse tree produced by {@link StixPatternParser#primitiveLiteral}.
     *
     * @param ctx the parse tree
     */
    void exitPrimitiveLiteral(StixPatternParser.PrimitiveLiteralContext ctx);

    /**
     * Enter a parse tree produced by
     * {@link StixPatternParser#orderableLiteral}.
     *
     * @param ctx the parse tree
     */
    void enterOrderableLiteral(StixPatternParser.OrderableLiteralContext ctx);

    /**
     * Exit a parse tree produced by {@link StixPatternParser#orderableLiteral}.
     *
     * @param ctx the parse tree
     */
    void exitOrderableLiteral(StixPatternParser.OrderableLiteralContext ctx);
}
