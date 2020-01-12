
package design.unstructured.stix.evaluator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import design.unstructured.stix.evaluator.PatternUtils.PatternList;
import design.unstructured.stix.evaluator.mapper.StixMapperException;

public class PatternEvaluatorTest {

    static final Logger logger = Logger.getLogger(PatternEvaluatorTest.class.getName());

    static final String TEST_PATTERN_FILE = "test-patterns.json";

    static final StaticObjectPathProvider resolver = new StaticObjectPathProvider();

    @BeforeAll
    static void configureLogger() throws SecurityException, IOException {
        LogManager.getLogManager().readConfiguration(ClassLoader.getSystemResource("jul-log.properties").openStream());
    }

    @BeforeEach
    void setup() {
        System.out.println();
        resolver.add("process:name", "cmd.exe")
                .add("process:parent.command_line", "cmd.exe --help --this --test_a_long_argument")
                .add("process:administrator", true).add("process:id", 459);
    }

    @Test
    void stringLiteralComparison_ReturnsTrue() throws PatternEvaluatorException, StixMapperException {
        Pattern pattern = buildTestPattern("[process:name = 'cmd.exe']");
        PatternEvaluator evaluator = new PatternEvaluator(pattern, resolver, null);

        assertEquals(ObservationExpression.class, pattern.getExpression().getClass());
        assertEquals(ComparisonExpression.class,
                ((ObservationExpression) pattern.getExpression()).getComparisonExpression().getClass());
        assertTrue(evaluator.get());
    }

    @Test
    void stringLiteralComparison_ReturnsFalse() throws PatternEvaluatorException, StixMapperException {
        Pattern pattern = buildTestPattern("[process:name = 'shouldnotequate.exe']");
        PatternEvaluator evaluator = new PatternEvaluator(pattern, resolver, null);

        assertFalse(evaluator.get());
    }

    @Test
    void floatLiteralComparison_ReturnsTrue() throws PatternEvaluatorException, StixMapperException {
        Pattern pattern = buildTestPattern("[process:float_test > 5.3]");
        resolver.add("process:float_test", 5.4);
        PatternEvaluator evaluator = new PatternEvaluator(pattern, resolver, null);

        assertTrue(evaluator.get());
    }

    @Test
    void booleanLiteralComparison_ReturnsTrue() throws PatternEvaluatorException, StixMapperException {
        Pattern pattern = buildTestPattern("[process:boolean_test = true]");
        resolver.add("process:boolean_test", true);
        PatternEvaluator evaluator = new PatternEvaluator(pattern, resolver, null);

        assertTrue(evaluator.get());
    }

    @Test
    void hexLiteralComparison_ReturnsTrue() throws PatternEvaluatorException, StixMapperException {
        Pattern pattern = buildTestPattern("[process:hex_test = h'FFFFFF']");
        resolver.add("process:hex_test", 0xFFFFFF);
        PatternEvaluator evaluator = new PatternEvaluator(pattern, resolver, null);

        assertTrue(evaluator.get());

        pattern = buildTestPattern("[process:hex_test > h'FFFF']");
        evaluator = new PatternEvaluator(pattern, resolver, null);

        assertTrue(evaluator.get());
    }

    @Test
    void binaryLiteralComparison_ReturnsTrue() throws PatternEvaluatorException, StixMapperException {
        Pattern pattern = buildTestPattern("[process:base64_test = b'dGVzdA==']");
        resolver.add("process:base64_test", "test");
        PatternEvaluator evaluator = new PatternEvaluator(pattern, resolver, null);

        assertTrue(evaluator.get());
    }

    @Test
    void multipleConditionsWithAndComparator_ReturnsTrue() throws PatternEvaluatorException, StixMapperException {
        Pattern pattern = buildTestPattern(
                "[process:name = 'cmd.exe' AND process:parent.command_line = 'cmd.exe --help --this --test_a_long_argument' AND process:id = 459 AND process:administrator = true]");
        PatternEvaluator evaluator = new PatternEvaluator(pattern, resolver, null);

        assertTrue(evaluator.get());
    }

    @Test
    void multipleConditionsWithOrComparator_ReturnsTrue() throws PatternEvaluatorException, StixMapperException {
        Pattern pattern = buildTestPattern(
                "[process:name = 'thisreturnsfalse.exe' OR process:parent.command_line = 'cmd.exe --help --this --test_a_long_argument']");
        PatternEvaluator evaluator = new PatternEvaluator(pattern, resolver, null);

        assertTrue(evaluator.get());
    }

    /**
     * You are probably wondering why this test even exists. I am still wondering
     * too.
     * 
     * @throws ParseException
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IllegalStateException
     * @throws IOException
     * @throws PatternEvaluatorException
     * @throws StixMapperException
     */
    @Test
    void evaluatePatternFile() throws ParseException, JsonParseException, JsonMappingException, IllegalStateException,
            IOException, PatternEvaluatorException, StixMapperException {

        PatternList patterns = PatternUtils.loadPatternFile(
                new File(PatternEvaluatorTest.class.getClassLoader().getResource(TEST_PATTERN_FILE).getFile()));

        resolver.add("process:parent.name", "explorer.exe");
        resolver.add("process:command_line", "ftp://127.0.0.1/virus.exe");
        resolver.add("process:name_in_test", "looking_for_this_process_name.exe");

        logger.info("Loaded " + patterns.size() + " patterns from " + TEST_PATTERN_FILE);
        logger.info("Evaluating all patterns...");

        patterns.evaluateAll();

        for (StixPattern stixPattern : patterns) {
            PatternEvaluator evaluator = new PatternEvaluator(stixPattern.getParsedPattern(), resolver, null);

            System.out.println();
            printPattern(stixPattern);
            printPattern(stixPattern.getParsedPattern());

            Boolean evaluation = evaluator.get();

            if (!evaluation) {
                logger.info("Bad result from pattern.");
            }
            assertTrue(evaluation);
        }
    }

    @Test
    void badPatternInput_ThrowsException() {
        assertThrows(Exception.class, () -> {
            buildTestPattern("[process:name ! 'invalid syntax']");
        });
    }

    private static Pattern buildTestPattern(final String rawPattern) {
        printPattern(rawPattern);
        Pattern pattern = Pattern.build(rawPattern);
        printPattern(pattern);
        return pattern;
    }

    private static void printPattern(StixPattern stixPattern) {
        logger.info("Evaluating pattern '" + stixPattern.getName() + "': " + stixPattern.getPattern());
    }

    private static void printPattern(String stixPattern) {
        logger.info("Evaluating pattern: " + stixPattern);
    }

    private static void printPattern(Pattern pattern) {
        logger.info(pattern.toString());
    }

}