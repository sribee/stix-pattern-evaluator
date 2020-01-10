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

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import design.unstructured.stix.evaluator.grammar.StixPatternLexer;
import design.unstructured.stix.evaluator.grammar.StixPatternParser;

/**
 *
 * @author ccarv
 */
public class Pattern implements ExpressionEvaluator {

    private final BaseObservationExpression expression;

    Pattern(BaseObservationExpression expression) {
        this.expression = expression;
    }

    public static Pattern build(String rawPattern) {
        StixPatternParser stixParser = new StixPatternParser(
                new CommonTokenStream(new StixPatternLexer(CharStreams.fromString(rawPattern))));
        StixPatternProcessor processor = new StixPatternProcessor();

        stixParser.setBuildParseTree(true);
        ParseTreeWalker.DEFAULT.walk(processor, stixParser.pattern());

        return processor.get();
    }

    /**
     * @return the expression
     */
    public BaseObservationExpression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return "Pattern[" + this.getExpression() + "]";
    }

    @Override
    public boolean evaluate() {
        return ((ExpressionEvaluator) expression).evaluate();
    }
}
