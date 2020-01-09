package design.unstructured.stix.evaluator;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import design.unstructured.stix.evaluator.grammar.StixPatternLexer;
import design.unstructured.stix.evaluator.grammar.StixPatternParser;

public class PatternUtils {

    static class PatternList extends ArrayList<StixPattern> {

        private static final long serialVersionUID = 1L;

        void evaluateAll() throws ParseException {
            int pos = 0;

            for (StixPattern stixPattern : this) {
                Pattern pattern = PatternUtils.parsePattern(stixPattern.getPattern());

                if (pattern == null) {
                    throw new ParseException("Unable to parse pattern: " + stixPattern.getPattern(), pos);
                }

                stixPattern.setParsedPattern(pattern);
                pos++;
            }
        }
    }

    static PatternList loadPatternFile(File patternFile)
            throws IllegalStateException, JsonParseException, JsonMappingException, IOException {

        PatternList patterns = new PatternList();

        try (JsonParser parser = JacksonMapperProvider.getReader().getFactory().createParser(patternFile)) {
            if (parser.nextToken() != JsonToken.START_ARRAY) {
                throw new IllegalStateException("Expected an array");
            }
            while (parser.nextToken() == JsonToken.START_OBJECT) {
                StixPattern rule = JacksonMapperProvider.getMapper().readValue(parser, StixPattern.class);
                patterns.add(rule);
            }
        }
        return patterns;
    }

    static Pattern parsePattern(String rawPattern) {
        Pattern pattern = null;

        try {
            CharStream charStream = CharStreams.fromString(rawPattern);
            StixPatternLexer stixLexer = new StixPatternLexer(charStream);
            CommonTokenStream commonTokenStream = new CommonTokenStream(stixLexer);
            StixPatternParser stixParser = new StixPatternParser(commonTokenStream);
            stixParser.setBuildParseTree(true);
            StixPatternParser.PatternContext ctx = stixParser.pattern();
            StixPatternProcessor processor = new StixPatternProcessor();
            ParseTreeWalker.DEFAULT.walk(processor, ctx);
            pattern = processor.get();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }

        return pattern;
    }
}