
package design.unstructured.stix.evaluator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import design.unstructured.stix.evaluator.PatternUtils.PatternList;
import design.unstructured.stix.evaluator.mapper.StixMapperException;

public class PatternEvaluatorTest {

    private static final String TEST_PATTERN_FILE = "test-patterns.json";

    private StaticObjectPathProvider resolver = new StaticObjectPathProvider();

    @BeforeEach
    void setup() {
        resolver.add("process:name", "cmd.exe")
                .add("process:parent.command_line", "cmd.exe --help --this --test_a_long_argument")
                .add("process:administrator", true).add("process:id", 459);
    }

    @Test
    void processNameEvaluation_ReturnsTrue() throws PatternEvaluatorException, StixMapperException {
        final String rawPattern = "[process:name = 'cmd.exe']";
        System.out.println("Evaluating pattern: " + rawPattern);
        PatternEvaluator evaluator = new PatternEvaluator(PatternUtils.parsePattern(rawPattern), resolver, null);

        assertTrue(evaluator.get());
    }

    @Test
    void processNameEvaluation_ReturnsFalse() throws PatternEvaluatorException, StixMapperException {
        final String rawPattern = "[process:name = 'shouldnotequate.exe']";
        System.out.println("Evaluating pattern: " + rawPattern);
        PatternEvaluator evaluator = new PatternEvaluator(PatternUtils.parsePattern(rawPattern), resolver, null);

        assertFalse(evaluator.get());
    }

    @Test
    void multipleConditionsWithAndComparator_ReturnsTrue() throws PatternEvaluatorException, StixMapperException {
        final String rawPattern = "[process:name = 'cmd.exe' AND process:parent.command_line = 'cmd.exe --help --this --test_a_long_argument' AND process:id = 459 AND process:administrator = true]";
        System.out.println("Evaluating pattern: " + rawPattern);
        PatternEvaluator evaluator = new PatternEvaluator(PatternUtils.parsePattern(rawPattern), resolver, null);

        assertTrue(evaluator.get());
    }

    @Test
    void multipleConditionsWithOrComparator_ReturnsTrue() throws PatternEvaluatorException, StixMapperException {
        final String rawPattern = "[process:name = 'thisreturnsfalse.exe' OR process:parent.command_line = 'cmd.exe --help --this --test_a_long_argument']";
        System.out.println("Evaluating pattern: " + rawPattern);
        PatternEvaluator evaluator = new PatternEvaluator(PatternUtils.parsePattern(rawPattern), resolver, null);

        assertTrue(evaluator.get());
    }

    @Test
    void evaluatePatternFile() throws ParseException, JsonParseException, JsonMappingException, IllegalStateException,
            IOException, PatternEvaluatorException, StixMapperException {

        PatternList patterns = PatternUtils.loadPatternFile(
                new File(PatternEvaluatorTest.class.getClassLoader().getResource(TEST_PATTERN_FILE).getFile()));

        resolver.add("process:parent.name", "explorer.exe");
        resolver.add("process:command_line", "ftp://127.0.0.1/virus.exe");

        System.out.println("Loaded " + patterns.size() + " patterns from " + TEST_PATTERN_FILE);
        System.out.println("Evaluating all patterns...");

        patterns.evaluateAll();

        for (StixPattern stixPattern : patterns) {
            PatternEvaluator evaluator = new PatternEvaluator(stixPattern.getParsedPattern(), resolver, null);

            System.out.println("Evaluating rule " + stixPattern.getName() + ": " + stixPattern.getPattern());
            assertTrue(evaluator.get());
        }
    }

}