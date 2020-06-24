/*
* stix-pattern-evaluator
* Copyright (C) 2020 - Christopher Carver
* 
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package design.unstructured.stix.evaluator.grammar;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 * This class provides an empty implementation of {@link StixPatternListener},
 * which can be extended to create a listener which only needs to handle a
 * subset of the available methods.
 */
public class StixPatternBaseListener implements StixPatternListener {

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterPattern(StixPatternParser.PatternContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitPattern(StixPatternParser.PatternContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterObservationExpressions(StixPatternParser.ObservationExpressionsContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitObservationExpressions(StixPatternParser.ObservationExpressionsContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterObservationExpressionOr(StixPatternParser.ObservationExpressionOrContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitObservationExpressionOr(StixPatternParser.ObservationExpressionOrContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterObservationExpressionAnd(StixPatternParser.ObservationExpressionAndContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitObservationExpressionAnd(StixPatternParser.ObservationExpressionAndContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterObservationExpressionRepeated(StixPatternParser.ObservationExpressionRepeatedContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitObservationExpressionRepeated(StixPatternParser.ObservationExpressionRepeatedContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterObservationExpressionSimple(StixPatternParser.ObservationExpressionSimpleContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitObservationExpressionSimple(StixPatternParser.ObservationExpressionSimpleContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterObservationExpressionCompound(StixPatternParser.ObservationExpressionCompoundContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitObservationExpressionCompound(StixPatternParser.ObservationExpressionCompoundContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterObservationExpressionWithin(StixPatternParser.ObservationExpressionWithinContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitObservationExpressionWithin(StixPatternParser.ObservationExpressionWithinContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterObservationExpressionStartStop(StixPatternParser.ObservationExpressionStartStopContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitObservationExpressionStartStop(StixPatternParser.ObservationExpressionStartStopContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterComparisonExpressionAnd_(StixPatternParser.ComparisonExpressionAnd_Context ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitComparisonExpressionAnd_(StixPatternParser.ComparisonExpressionAnd_Context ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterComparisonExpressionOred(StixPatternParser.ComparisonExpressionOredContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitComparisonExpressionOred(StixPatternParser.ComparisonExpressionOredContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterComparisonExpressionAndPropTest(StixPatternParser.ComparisonExpressionAndPropTestContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitComparisonExpressionAndPropTest(StixPatternParser.ComparisonExpressionAndPropTestContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterComparisonExpressionAnded(StixPatternParser.ComparisonExpressionAndedContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitComparisonExpressionAnded(StixPatternParser.ComparisonExpressionAndedContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterPropTestEqual(StixPatternParser.PropTestEqualContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitPropTestEqual(StixPatternParser.PropTestEqualContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterPropTestOrder(StixPatternParser.PropTestOrderContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitPropTestOrder(StixPatternParser.PropTestOrderContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterPropTestSet(StixPatternParser.PropTestSetContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitPropTestSet(StixPatternParser.PropTestSetContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterPropTestLike(StixPatternParser.PropTestLikeContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitPropTestLike(StixPatternParser.PropTestLikeContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterPropTestRegex(StixPatternParser.PropTestRegexContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitPropTestRegex(StixPatternParser.PropTestRegexContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterPropTestIsSubset(StixPatternParser.PropTestIsSubsetContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitPropTestIsSubset(StixPatternParser.PropTestIsSubsetContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterPropTestIsSuperset(StixPatternParser.PropTestIsSupersetContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitPropTestIsSuperset(StixPatternParser.PropTestIsSupersetContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterPropTestParen(StixPatternParser.PropTestParenContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitPropTestParen(StixPatternParser.PropTestParenContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterPropTestExists(StixPatternParser.PropTestExistsContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitPropTestExists(StixPatternParser.PropTestExistsContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterOrderingComparator(StixPatternParser.OrderingComparatorContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitOrderingComparator(StixPatternParser.OrderingComparatorContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterStringLiteral(StixPatternParser.StringLiteralContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitStringLiteral(StixPatternParser.StringLiteralContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterStartStopQualifier(StixPatternParser.StartStopQualifierContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitStartStopQualifier(StixPatternParser.StartStopQualifierContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterWithinQualifier(StixPatternParser.WithinQualifierContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitWithinQualifier(StixPatternParser.WithinQualifierContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterRepeatedQualifier(StixPatternParser.RepeatedQualifierContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitRepeatedQualifier(StixPatternParser.RepeatedQualifierContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterObjectPath(StixPatternParser.ObjectPathContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitObjectPath(StixPatternParser.ObjectPathContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterObjectType(StixPatternParser.ObjectTypeContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitObjectType(StixPatternParser.ObjectTypeContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterFirstPathComponent(StixPatternParser.FirstPathComponentContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitFirstPathComponent(StixPatternParser.FirstPathComponentContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterIndexPathStep(StixPatternParser.IndexPathStepContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitIndexPathStep(StixPatternParser.IndexPathStepContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterPathStep(StixPatternParser.PathStepContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitPathStep(StixPatternParser.PathStepContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterKeyPathStep(StixPatternParser.KeyPathStepContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitKeyPathStep(StixPatternParser.KeyPathStepContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterSetLiteral(StixPatternParser.SetLiteralContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitSetLiteral(StixPatternParser.SetLiteralContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterPrimitiveLiteral(StixPatternParser.PrimitiveLiteralContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitPrimitiveLiteral(StixPatternParser.PrimitiveLiteralContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterOrderableLiteral(StixPatternParser.OrderableLiteralContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitOrderableLiteral(StixPatternParser.OrderableLiteralContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void visitTerminal(TerminalNode node) {
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation does nothing.
     * </p>
     */
    @Override
    public void visitErrorNode(ErrorNode node) {
    }
}
