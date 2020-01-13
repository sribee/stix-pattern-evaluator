
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
    void stringLiteralEquality_ReturnsTrue()
            throws PatternEvaluatorException, StixMapperException, StixPatternProcessorException {
        Pattern pattern = buildTestPattern("[process:name = 'cmd.exe']");
        PatternEvaluator evaluator = new PatternEvaluator(pattern, resolver, null);

        assertEquals(ObservationExpression.class, pattern.getExpression().getClass());
        assertEquals(ComparisonExpression.class,
                ((ObservationExpression) pattern.getExpression()).getComparisonExpression().getClass());
        assertTrue(evaluator.get());
    }

    @Test
    void stringLiteralEquality_ReturnsFalse()
            throws PatternEvaluatorException, StixMapperException, StixPatternProcessorException {
        Pattern pattern = buildTestPattern("[process:name = 'shouldnotequate.exe']");
        PatternEvaluator evaluator = new PatternEvaluator(pattern, resolver, null);

        assertFalse(evaluator.get());
    }

    @Test
    void stringLiteralNotEquality_ReturnsTrue()
            throws PatternEvaluatorException, StixMapperException, StixPatternProcessorException {
        Pattern pattern = buildTestPattern("[process:name != 'shouldnotequate.exe']");
        PatternEvaluator evaluator = new PatternEvaluator(pattern, resolver, null);

        assertTrue(evaluator.get());
    }

    @Test
    void integerLiteralEquality_ReturnsTrue()
            throws PatternEvaluatorException, StixMapperException, StixPatternProcessorException {
        Pattern pattern = buildTestPattern("[process:id = 459]");
        PatternEvaluator evaluator = new PatternEvaluator(pattern, resolver, null);

        assertTrue(evaluator.get());
    }

    @Test
    void negativeIntegerLiteralEquality_ReturnsTrue()
            throws PatternEvaluatorException, StixMapperException, StixPatternProcessorException {
        resolver.add("process:negative_id", -459);
        Pattern pattern = buildTestPattern("[process:negative_id = -459]");
        PatternEvaluator evaluator = new PatternEvaluator(pattern, resolver, null);

        assertTrue(evaluator.get());
    }

    @Test
    void floatLiteralGreaterThan_ReturnsTrue()
            throws PatternEvaluatorException, StixMapperException, StixPatternProcessorException {
        Pattern pattern = buildTestPattern("[process:float_test > 5.3]");
        resolver.add("process:float_test", 5.4);
        PatternEvaluator evaluator = new PatternEvaluator(pattern, resolver, null);

        assertTrue(evaluator.get());
    }

    @Test
    void negativeFloatLiteralEquality_ReturnsTrue()
            throws PatternEvaluatorException, StixMapperException, StixPatternProcessorException {
        Pattern pattern = buildTestPattern("[process:neg_float_test = -5.3]");
        resolver.add("process:neg_float_test", -5.3);
        PatternEvaluator evaluator = new PatternEvaluator(pattern, resolver, null);

        assertTrue(evaluator.get());
    }

    @Test
    void booleanLiteralEquality_ReturnsTrue()
            throws PatternEvaluatorException, StixMapperException, StixPatternProcessorException {
        Pattern pattern = buildTestPattern("[process:boolean_test = true]");
        resolver.add("process:boolean_test", true);
        PatternEvaluator evaluator = new PatternEvaluator(pattern, resolver, null);

        assertTrue(evaluator.get());
    }

    @Test
    void hexLiteralEquality_ReturnsTrue()
            throws PatternEvaluatorException, StixMapperException, StixPatternProcessorException {
        Pattern pattern = buildTestPattern("[process:hex_test = h'FFFFFF']");
        resolver.add("process:hex_test", 0xFFFFFF);
        PatternEvaluator evaluator = new PatternEvaluator(pattern, resolver, null);

        assertTrue(evaluator.get());

        pattern = buildTestPattern("[process:hex_test > h'FFFF']");
        evaluator = new PatternEvaluator(pattern, resolver, null);

        assertTrue(evaluator.get());
    }

    @Test
    void binaryLiteralEquality_ReturnsTrue()
            throws PatternEvaluatorException, StixMapperException, StixPatternProcessorException {
        Pattern pattern = buildTestPattern("[process:base64_test = b'dGVzdA==']");
        resolver.add("process:base64_test", "test");
        PatternEvaluator evaluator = new PatternEvaluator(pattern, resolver, null);

        assertTrue(evaluator.get());
    }

    @Test
    void multipleConditionsWithAndComparator_ReturnsTrue()
            throws PatternEvaluatorException, StixMapperException, StixPatternProcessorException {
        Pattern pattern = buildTestPattern(
                "[process:name = 'cmd.exe' AND process:parent.command_line = 'cmd.exe --help --this --test_a_long_argument' AND process:id = 459 AND process:administrator = true]");
        PatternEvaluator evaluator = new PatternEvaluator(pattern, resolver, null);

        assertTrue(evaluator.get());
    }

    @Test
    void multipleConditionsWithOrComparator_ReturnsTrue()
            throws PatternEvaluatorException, StixMapperException, StixPatternProcessorException {
        Pattern pattern = buildTestPattern(
                "[process:name = 'thisreturnsfalse.exe' OR process:parent.command_line = 'cmd.exe --help --this --test_a_long_argument'] OR [process:name = 'cmd.exe']");
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
     * @throws StixPatternProcessorException
     */
    @Test
    void evaluatePatternFile() throws ParseException, JsonParseException, JsonMappingException, IllegalStateException,
            IOException, PatternEvaluatorException, StixMapperException, StixPatternProcessorException {

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
        assertThrows(StixPatternProcessorException.class, () -> {
            buildTestPattern("[process:name ! 'invalid syntax']");
        });
    }

    @Test
    void nullResolverArgument_ThrowsException() {
        assertThrows(PatternEvaluatorException.class, () -> {
            new PatternEvaluator(buildTestPattern("[process:name = 'test']"), null, null);
        });
    }

    @Test
    void nullPatternArgument_ThrowsException() {
        assertThrows(PatternEvaluatorException.class, () -> {
            new PatternEvaluator(null, null, null);
        });
    }

    private static Pattern buildTestPattern(final String rawPattern) throws StixPatternProcessorException {
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