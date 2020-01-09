/*
 * stix-pattern-evaluator
 * Copyright (C) 2020 - Unstructured Design
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

import design.unstructured.stix.evaluator.grammar.StixPatternListener;
import design.unstructured.stix.evaluator.grammar.StixPatternParser;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.function.Function;
import java.util.function.Supplier;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * When ANTLR walks the grammer, this listener is notified when one of the
 * parsing rules is triggered. An instance of this class should be passed to the
 * ParseTreeWalker.
 */
@SuppressWarnings({ "unchecked" })
public class StixPatternProcessor implements StixPatternListener, Supplier<Pattern> {

    private static final Logger logger = LoggerFactory.getLogger(StixPatternProcessor.class);

    private static final DateTimeFormatter tsFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss[.SSS]'Z'");

    private final Stack<Object> scope = new Stack<>();

    private Stack<Object> backupScope;

    private static final Function<String, String> literalFilter = literal -> {
        return literal.replaceAll("^'+", "").replaceAll("'+$", "").replace("\\\\", "\\");
    };

    private static final Function<String, String> objectFilter = object -> {
        return object.replace("'", "");
    };

    /**
     * Returns the Pattern created by the lexer and parser.
     *
     * @return
     */
    @Override
    public Pattern get() {
        return (Pattern) scope.pop();
    }

    /**
     * Extracts the object path from the context of the pattern and inserts into our
     * scope. This is typically the first event that is fired when walking the
     * parser tree.
     *
     * @param ctx
     */
    @Override
    public void exitObjectPath(StixPatternParser.ObjectPathContext ctx) {
        String objectPath = objectFilter.apply(ctx.getText());

        logger.trace("exitObjectPath: {} {} | filtered={}", ctx, ctx.getText(), objectPath);
        scope.push(objectPath);
    }

    /**
     * Extracts the string literal, or value, from the context of the pattern and
     * inserts into our scope. This is typically the second event that is fired when
     * walking the parser tree.
     *
     * @param ctx
     */
    @Override
    public void exitStringLiteral(StixPatternParser.StringLiteralContext ctx) {
        String stringLiteral = literalFilter.apply(ctx.getText());
        logger.trace("exitStringLiteral: {} {} | filtered={}", ctx, ctx.getText(), stringLiteral);

        scope.push(stringLiteral);
    }

    @Override
    public void exitPropTestLike(StixPatternParser.PropTestLikeContext ctx) {
        logger.trace("exitPropTestLike: {} {} | stack={}", ctx, ctx.getText(), scope);

        String value = (String) scope.pop();
        String objectPath = (String) scope.pop();

        scope.push(new ComparisonExpression(objectPath, value, ComparisonComparators.Like, ctx.NOT()));
    }

    @Override
    public void exitPrimitiveLiteral(StixPatternParser.PrimitiveLiteralContext ctx) {
        logger.trace("exitPrimitiveLiteral: {} {}", ctx, ctx.getText());

        if (ctx.BoolLiteral() != null) {
            scope.push(Boolean.valueOf(ctx.getText()));
        }
    }

    @Override
    public void exitPropTestRegex(StixPatternParser.PropTestRegexContext ctx) {
        logger.trace("exitPropTestEqual: {} {} | stack={}", ctx, ctx.getText(), scope);

        String regex = (String) scope.pop();
        java.util.regex.Pattern value = java.util.regex.Pattern.compile(regex);
        String objectPath = (String) scope.pop();

        scope.push(new ComparisonExpression(objectPath, value, ComparisonComparators.Matches, ctx.NOT()));
    }

    @Override
    public void exitPropTestEqual(StixPatternParser.PropTestEqualContext ctx) {
        logger.trace("exitPropTestEqual: {} {} | stack={}", ctx, ctx.getText(), scope);

        Object value = scope.pop();
        String objectPath = (String) scope.pop();
        ComparisonComparators comparator = (ctx.EQ() != null ? ComparisonComparators.Equal
                : ComparisonComparators.NotEqual);

        scope.push(new ComparisonExpression(objectPath, value, comparator, ctx.NOT()));
    }

    @Override
    public void exitPropTestOrder(StixPatternParser.PropTestOrderContext ctx) {
        logger.trace("exitPropTestOrder: {} {} | stack={}", ctx, ctx.getText(), scope);

        Object value = scope.pop();
        String objectPath = (String) scope.pop();
        ComparisonComparators comparator = null;

        if (ctx.GT() != null) {
            comparator = ComparisonComparators.GreaterThan;
        } else if (ctx.LT() != null) {
            comparator = ComparisonComparators.LessThan;
        } else if (ctx.GE() != null) {
            comparator = ComparisonComparators.GreaterThanOrEqual;
        } else if (ctx.LE() != null) {
            comparator = ComparisonComparators.GreaterThan;
        } else {
            logger.error(
                    "an unrecognized ordering comparator was used in this pattern and cannot be evaluated, this may lead to an inconsistent or invalid result.");
        }

        scope.push(new ComparisonExpression(objectPath, value, comparator, ctx.NOT()));

    }

    @Override
    public void exitPropTestSet(StixPatternParser.PropTestSetContext ctx) {
        logger.trace("exitPropTestSet: {} {} | stack={}", ctx, ctx.getText(), scope);

        Set<Object> value = (Set<Object>) scope.pop();
        String objectPath = (String) scope.pop();

        scope.push(new ComparisonExpression(objectPath, value, ComparisonComparators.In, ctx.NOT()));
    }

    /**
     * This event indicates that we are about parse a set literal. Since the length
     * of the set is indeterminate, we must move everything from our scope onto
     * another scope to create this set.
     *
     *
     * @param ctx
     */
    @Override
    public void enterSetLiteral(StixPatternParser.SetLiteralContext ctx) {
        backupScope = (Stack<Object>) scope.clone();
        scope.clear();
    }

    /**
     * End parsing of our set literal. We will want to convert our scope to a
     * HashSet while pruning it, merge our backup scope onto our main scope, and
     * finally add the set literal onto the main scope.
     *
     * Using a HashSet opposed to an array allows for better performance down
     * stream. A HashSet will guarantee a constant lookup time of O(1) and
     * uniqueness.
     *
     * @param ctx
     */
    @Override
    public void exitSetLiteral(StixPatternParser.SetLiteralContext ctx) {
        Set<Object> setLiteral = new HashSet<>();

        while (!scope.empty()) {
            setLiteral.add(scope.pop());
        }

        scope.addAll(backupScope);
        scope.add(setLiteral);
    }

    @Override
    public void exitOrderableLiteral(StixPatternParser.OrderableLiteralContext ctx) {
        logger.trace("exitOrderableLiteral: {} {} | stack={}", ctx, ctx.getText(), scope);

        if (ctx.IntPosLiteral() != null || ctx.IntNegLiteral() != null) {
            scope.push(Integer.parseInt(ctx.getText()));
        } else if (ctx.FloatPosLiteral() != null || ctx.FloatNegLiteral() != null) {
            scope.push(Float.parseFloat(ctx.getText()));
        } else if (ctx.BinaryLiteral() != null) {
            scope.push(ctx.getText());
        } else if (ctx.HexLiteral() != null) {
            scope.push(ctx.getText());
        } else if (ctx.TimestampLiteral() != null) {
            scope.push(tsFormatter.parse(ctx.getText()));
        }
    }

    @Override
    public void exitComparisonExpressionAnded(StixPatternParser.ComparisonExpressionAndedContext ctx) {
        logger.trace("exitComparisonExpressionAnded: {} {}", ctx, ctx.getText());

        BaseComparisonExpression firstExpression = (BaseComparisonExpression) scope.pop();
        BaseComparisonExpression secondExpression = (BaseComparisonExpression) scope.pop();

        scope.push(
                new CombinedComparisonExpression(firstExpression, secondExpression, ComparisonExpressionOperators.And));
    }

    @Override
    public void exitComparisonExpressionOred(StixPatternParser.ComparisonExpressionOredContext ctx) {
        logger.trace("exitComparisonExpressionOred: {} {}", ctx, ctx.getText());

        BaseComparisonExpression firstExpression = (BaseComparisonExpression) scope.pop();
        BaseComparisonExpression secondExpression = (BaseComparisonExpression) scope.pop();

        scope.push(
                new CombinedComparisonExpression(firstExpression, secondExpression, ComparisonExpressionOperators.Or));
    }

    @Override
    public void exitObservationExpressionSimple(StixPatternParser.ObservationExpressionSimpleContext ctx) {
        logger.trace("exitObservationExpressionSimple: {} {}", ctx, ctx.getText());

        BaseComparisonExpression expression = (BaseComparisonExpression) scope.pop();

        scope.push(new ObservationExpression(expression));
        // comparison_expression = self.pop()
        //
        // # object_name = self._current_object_type
        // # self._current_object_type = None
        //
        // logger.trace("Current Parser Stack: {}".format(self._stack))
        // # logger.trace("Building DMQ with object_name={}, action={},
        // query={}".format(object_name, self.action, query))
        // # self.push(DataModelQuery(object_name=object_name, action=self.action,
        // query=query))
        // self.push(ObservationExpression(comparison_expression))
    }

    @Override
    public void exitObservationExpressionAnd(StixPatternParser.ObservationExpressionAndContext ctx) {
        logger.trace("exitObservationExpressionAnd: {} {}", ctx, ctx.getText());

        if (ctx.AND() != null) {
            BaseObservationExpression secondExpression = (BaseObservationExpression) scope.pop();
            BaseObservationExpression firstExpression = (BaseObservationExpression) scope.pop();

            scope.push(new CombinedObservationExpression(firstExpression, secondExpression, ObservationOperators.And));
        }
    }

    @Override
    public void exitObservationExpressionOr(StixPatternParser.ObservationExpressionOrContext ctx) {
        logger.trace("exitObservationExpressionOr: {} {}", ctx, ctx.getText());

        if (ctx.OR() != null) {
            BaseObservationExpression secondExpression = (BaseObservationExpression) scope.pop();
            BaseObservationExpression firstExpression = (BaseObservationExpression) scope.pop();

            scope.push(new CombinedObservationExpression(firstExpression, secondExpression, ObservationOperators.Or));
        }
    }

    @Override
    public void exitObservationExpressionStartStop(StixPatternParser.ObservationExpressionStartStopContext ctx) {
        logger.trace("exitObservationExpressionStartStop: {} {}", ctx, ctx.getText());

        String qualifierText = ctx.startStopQualifier().getText();
        BaseObservationExpression expression = (BaseObservationExpression) scope.pop();

        scope.push(new Qualifier(qualifierText, expression));
    }

    @Override
    public void exitObservationExpressions(StixPatternParser.ObservationExpressionsContext ctx) {
        logger.trace("exitObservationExpressions: {} {}", ctx, ctx.getText());

        if (ctx.FOLLOWEDBY() != null) {
            BaseObservationExpression secondExpression = (BaseObservationExpression) scope.pop();
            BaseObservationExpression firstExpression = (BaseObservationExpression) scope.pop();

            scope.push(new CombinedObservationExpression(firstExpression, secondExpression,
                    ObservationOperators.FollowedBy));
        }
    }

    @Override
    public void exitPattern(StixPatternParser.PatternContext ctx) {
        logger.trace("exitPattern: {} {}", ctx, ctx.getText());

        BaseObservationExpression expression = (BaseObservationExpression) scope.pop();

        scope.push(new Pattern(expression));
        logger.debug("observed pattern ({}): {}", scope.size(), scope);
    }

    // **END OF IMPLEMENTATION**
    // The remaining implementation was not implemented by OASIS,
    // which is our stopping point.
    //
    /**
     *
     * @param ctx
     */
    @Override
    public void enterPattern(StixPatternParser.PatternContext ctx) {
        // System.out.println("->enterPattern");
    }

    @Override
    public void enterObservationExpressions(StixPatternParser.ObservationExpressionsContext ctx) {
        // System.out.println("->enterObservationExpressions");
    }

    @Override
    public void enterObservationExpressionOr(StixPatternParser.ObservationExpressionOrContext ctx) {
        // System.out.println("->enterObservationExpressionOr");
    }

    @Override
    public void enterObservationExpressionAnd(StixPatternParser.ObservationExpressionAndContext ctx) {
        // System.out.println("->enterObservationExpressionAnd");
    }

    @Override
    public void enterObservationExpressionRepeated(StixPatternParser.ObservationExpressionRepeatedContext ctx) {
        // System.out.println("->enterObservationExpressionRepeated");
    }

    @Override
    public void exitObservationExpressionRepeated(StixPatternParser.ObservationExpressionRepeatedContext ctx) {
        // System.out.println("->exitObservationExpressionRepeated");
    }

    @Override
    public void enterObservationExpressionSimple(StixPatternParser.ObservationExpressionSimpleContext ctx) {
        // System.out.println("->enterObservationExpressionSimple");
    }

    @Override
    public void enterObservationExpressionCompound(StixPatternParser.ObservationExpressionCompoundContext ctx) {
        // System.out.println("->enterObservationExpressionCompound");
    }

    @Override
    public void exitObservationExpressionCompound(StixPatternParser.ObservationExpressionCompoundContext ctx) {
        // System.out.println("->exitObservationExpressionCompound");
    }

    @Override
    public void enterObservationExpressionWithin(StixPatternParser.ObservationExpressionWithinContext ctx) {
        // System.out.println("->enterObservationExpressionWithin");
    }

    @Override
    public void exitObservationExpressionWithin(StixPatternParser.ObservationExpressionWithinContext ctx) {
        // System.out.println("->exitObservationExpressionWithin");
    }

    @Override
    public void enterObservationExpressionStartStop(StixPatternParser.ObservationExpressionStartStopContext ctx) {
        // System.out.println("->enterObservationExpressionStartStop");
    }

    @Override
    public void enterPropTestEqual(StixPatternParser.PropTestEqualContext ctx) {
        // System.out.println("->enterPropTestEqual");
    }

    @Override
    public void enterPropTestOrder(StixPatternParser.PropTestOrderContext ctx) {
        // System.out.println("->enterPropTestOrder");
    }

    @Override
    public void enterPropTestSet(StixPatternParser.PropTestSetContext ctx) {
        // System.out.println("->enterPropTestSet");
    }

    @Override
    public void enterPropTestLike(StixPatternParser.PropTestLikeContext ctx) {
        // System.out.println("->enterPropTestLike");
    }

    @Override
    public void enterPropTestRegex(StixPatternParser.PropTestRegexContext ctx) {
        // System.out.println("->enterPropTestRegex");
    }

    @Override
    public void enterPropTestIsSubset(StixPatternParser.PropTestIsSubsetContext ctx) {
        // System.out.println("->enterPropTestIsSubset");
    }

    @Override
    public void exitPropTestIsSubset(StixPatternParser.PropTestIsSubsetContext ctx) {
        // System.out.println("->exitPropTestIsSubset");
    }

    @Override
    public void enterPropTestIsSuperset(StixPatternParser.PropTestIsSupersetContext ctx) {
        // System.out.println("->enterPropTestIsSuperset");
    }

    @Override
    public void exitPropTestIsSuperset(StixPatternParser.PropTestIsSupersetContext ctx) {
        // System.out.println("->exitPropTestIsSuperset");
    }

    @Override
    public void enterPropTestParen(StixPatternParser.PropTestParenContext ctx) {
        // System.out.println("->enterPropTestParen");
    }

    @Override
    public void exitPropTestParen(StixPatternParser.PropTestParenContext ctx) {
        // System.out.println("->exitPropTestParen");
    }

    @Override
    public void enterPropTestExists(StixPatternParser.PropTestExistsContext ctx) {
        // System.out.println("->enterPropTestExists");
    }

    @Override
    public void exitPropTestExists(StixPatternParser.PropTestExistsContext ctx) {
        // System.out.println("->exitPropTestExists");
    }

    @Override
    public void enterStartStopQualifier(StixPatternParser.StartStopQualifierContext ctx) {
        // System.out.println("->enterStartStopQualifier");
    }

    @Override
    public void exitStartStopQualifier(StixPatternParser.StartStopQualifierContext ctx) {
        // System.out.println("->exitStartStopQualifier");
    }

    @Override
    public void enterWithinQualifier(StixPatternParser.WithinQualifierContext ctx) {
        // System.out.println("->enterWithinQualifier");
    }

    @Override
    public void exitWithinQualifier(StixPatternParser.WithinQualifierContext ctx) {
        // System.out.println("->exitWithinQualifier");
    }

    @Override
    public void enterRepeatedQualifier(StixPatternParser.RepeatedQualifierContext ctx) {
        // System.out.println("->enterRepeatedQualifier");
    }

    @Override
    public void exitRepeatedQualifier(StixPatternParser.RepeatedQualifierContext ctx) {
        // System.out.println("->exitRepeatedQualifier");
    }

    @Override
    public void enterObjectPath(StixPatternParser.ObjectPathContext ctx) {
        // System.out.println("->enterObjectPath");
    }

    @Override
    public void enterObjectType(StixPatternParser.ObjectTypeContext ctx) {
        // System.out.println("->enterObjectType");
    }

    @Override
    public void exitObjectType(StixPatternParser.ObjectTypeContext ctx) {
        // System.out.println("->exitObjectType");
    }

    @Override
    public void enterFirstPathComponent(StixPatternParser.FirstPathComponentContext ctx) {
        // System.out.println("->enterFirstPathComponent");
    }

    @Override
    public void exitFirstPathComponent(StixPatternParser.FirstPathComponentContext ctx) {
        // System.out.println("->exitFirstPathComponent");
    }

    @Override
    public void enterIndexPathStep(StixPatternParser.IndexPathStepContext ctx) {
        // System.out.println("->enterIndexPathStep");
    }

    @Override
    public void exitIndexPathStep(StixPatternParser.IndexPathStepContext ctx) {
        // System.out.println("->exitIndexPathStep");
    }

    @Override
    public void enterPathStep(StixPatternParser.PathStepContext ctx) {
        // System.out.println("->enterPathStep");
    }

    @Override
    public void exitPathStep(StixPatternParser.PathStepContext ctx) {
        // System.out.println("->exitPathStep");
    }

    @Override
    public void enterKeyPathStep(StixPatternParser.KeyPathStepContext ctx) {
        // System.out.println("->enterKeyPathStep");
    }

    @Override
    public void exitKeyPathStep(StixPatternParser.KeyPathStepContext ctx) {
        // System.out.println("->exitKeyPathStep");
    }

    @Override
    public void enterPrimitiveLiteral(StixPatternParser.PrimitiveLiteralContext ctx) {
        // System.out.println("->exitPrimitiveLiteral");
    }

    @Override
    public void enterOrderableLiteral(StixPatternParser.OrderableLiteralContext ctx) {
        // System.out.println("->enterOrderableLiteral");
    }

    @Override
    public void visitTerminal(TerminalNode tn) {
        // System.out.println("->visitTerminal");
    }

    @Override
    public void visitErrorNode(ErrorNode en) {
        // System.out.println("->visitErrorNode");
    }

    @Override
    public void enterEveryRule(ParserRuleContext prc) {
        //// System.out.println("Rule entered: " + prc.getText());
    }

    @Override
    public void exitEveryRule(ParserRuleContext prc) {
        // System.out.println("->exitEveryRule");
    }

    // CREATED AFTER CHANGES TO STIXPATTERN GRAMMAR
    @Override
    public void enterOrderingComparator(StixPatternParser.OrderingComparatorContext ctx) {
        // System.out.println("->enterOrderingComparator");
    }

    @Override
    public void exitOrderingComparator(StixPatternParser.OrderingComparatorContext ctx) {
        // System.out.println("->exitOrderingComparator");
    }

    @Override
    public void enterStringLiteral(StixPatternParser.StringLiteralContext ctx) {
        // System.out.println("->enterStringLiteral");
    }

    @Override
    public void enterComparisonExpressionAnd_(StixPatternParser.ComparisonExpressionAnd_Context ctx) {
        // System.out.println("->enterComparisonExpressionAnd_");
    }

    @Override
    public void exitComparisonExpressionAnd_(StixPatternParser.ComparisonExpressionAnd_Context ctx) {
        // System.out.println("->exitComparisonExpressionAnd_");
    }

    @Override
    public void enterComparisonExpressionOred(StixPatternParser.ComparisonExpressionOredContext ctx) {
        // System.out.println("->enterComparisonExpressionOred");
    }

    @Override
    public void enterComparisonExpressionAndPropTest(StixPatternParser.ComparisonExpressionAndPropTestContext ctx) {
        // System.out.println("->enterComparisonExpressionAndPropTest");
    }

    @Override
    public void exitComparisonExpressionAndPropTest(StixPatternParser.ComparisonExpressionAndPropTestContext ctx) {
        // System.out.println("->exitComparisonExpressionAndPropTest");
    }

    @Override
    public void enterComparisonExpressionAnded(StixPatternParser.ComparisonExpressionAndedContext ctx) {
        // System.out.println("->enterComparisonExpressionAnded");
    }

}