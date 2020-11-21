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

    public static Pattern build(String rawPattern) throws StixPatternProcessorException {
        StixPatternProcessor processor = null;

        try {
            StixPatternParser stixParser = new StixPatternParser(
                    new CommonTokenStream(new StixPatternLexer(CharStreams.fromString(rawPattern))));
            processor = new StixPatternProcessor();

            stixParser.setBuildParseTree(true);
            ParseTreeWalker.DEFAULT.walk(processor, stixParser.pattern());

        } catch (Exception ex) {
            throw new StixPatternProcessorException("Failed compiling pattern: " + ex.getMessage());
        }

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
