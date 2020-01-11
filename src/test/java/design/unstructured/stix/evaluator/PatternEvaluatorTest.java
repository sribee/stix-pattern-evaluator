
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
        System.out.println();
        resolver.add("process:name", "cmd.exe")
                .add("process:parent.command_line", "cmd.exe --help --this --test_a_long_argument")
                .add("process:administrator", true).add("process:id", 459);
    }

    @Test
    void processNameEvaluation_ReturnsTrue() throws PatternEvaluatorException, StixMapperException {
        final String rawPattern = "[process:name = 'cmd.exe']";
        printPattern(rawPattern);
        PatternEvaluator evaluator = new PatternEvaluator(Pattern.build(rawPattern), resolver, null);

        System.out.println(Pattern.build(rawPattern));

        assertTrue(evaluator.get());
    }

    @Test
    void processNameEvaluation_ReturnsFalse() throws PatternEvaluatorException, StixMapperException {
        final String rawPattern = "[process:name = 'shouldnotequate.exe']";
        printPattern(rawPattern);
        PatternEvaluator evaluator = new PatternEvaluator(Pattern.build(rawPattern), resolver, null);

        assertFalse(evaluator.get());
    }

    @Test
    void multipleConditionsWithAndComparator_ReturnsTrue() throws PatternEvaluatorException, StixMapperException {
        final String rawPattern = "[process:name = 'cmd.exe' AND process:parent.command_line = 'cmd.exe --help --this --test_a_long_argument' AND process:id = 459 AND process:administrator = true]";
        printPattern(rawPattern);
        PatternEvaluator evaluator = new PatternEvaluator(Pattern.build(rawPattern), resolver, null);

        assertTrue(evaluator.get());
    }

    @Test
    void multipleConditionsWithOrComparator_ReturnsTrue() throws PatternEvaluatorException, StixMapperException {
        final String rawPattern = "[process:name = 'thisreturnsfalse.exe' OR process:parent.command_line = 'cmd.exe --help --this --test_a_long_argument']";
        printPattern(rawPattern);
        PatternEvaluator evaluator = new PatternEvaluator(Pattern.build(rawPattern), resolver, null);

        assertTrue(evaluator.get());
    }

    @Test
    void evaluatePatternFile() throws ParseException, JsonParseException, JsonMappingException, IllegalStateException,
            IOException, PatternEvaluatorException, StixMapperException {

        PatternList patterns = PatternUtils.loadPatternFile(
                new File(PatternEvaluatorTest.class.getClassLoader().getResource(TEST_PATTERN_FILE).getFile()));

        resolver.add("process:parent.name", "explorer.exe");
        resolver.add("process:command_line", "ftp://127.0.0.1/virus.exe");
        resolver.add("process:name_in_test", "looking_for_this_process_name.exe");

        System.out.println("Loaded " + patterns.size() + " patterns from " + TEST_PATTERN_FILE);
        System.out.println("Evaluating all patterns...");

        patterns.evaluateAll();

        for (StixPattern stixPattern : patterns) {
            PatternEvaluator evaluator = new PatternEvaluator(stixPattern.getParsedPattern(), resolver, null);

            System.out.println();
            printPattern(stixPattern);
            Boolean evaluation = evaluator.get();
            System.out.println("Result: " + evaluation);
            assertTrue(evaluation);
        }
    }

    private static void printPattern(StixPattern stixPattern) {
        System.out.println("Evaluating pattern '" + stixPattern.getName() + "':\n    " + stixPattern.getPattern());
    }

    private static void printPattern(String stixPattern) {
        System.out.println("Evaluating pattern:\n    " + stixPattern);
    }

}