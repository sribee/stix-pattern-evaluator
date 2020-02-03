package design.unstructured.stix.evaluator.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;

/**
 * ObjectPathUtilsTest
 */
public class ObjectPathUtilsTest {

    @Test
    public void splitObjectPath_SingleProperty_NoFilter() {
        String[] split = ObjectPathUtils.explode("process:name");

        assertEquals("process", split[0]);
        assertEquals("name", split[1]);
    }

    @Test
    public void splitObjectPath_MultipleProperties() {
        String[] split = ObjectPathUtils.explode("process:parent_ref.name");

        assertEquals("process", split[0]);
        assertEquals("parent_ref", split[1]);
        assertEquals("name", split[2]);
    }

    @Test
    public void splitObjectPath_10Properties() {
        String[] split = ObjectPathUtils.explode("process:one.two.three.four.five.six.seven.eight.nine.ten");

        assertEquals("process", split[0]);
        assertEquals("one", split[1]);
        assertEquals("two", split[2]);
        assertEquals("three", split[3]);
        assertEquals("four", split[4]);
        assertEquals("five", split[5]);
        assertEquals("six", split[6]);
        assertEquals("seven", split[7]);
        assertEquals("eight", split[8]);
        assertEquals("nine", split[9]);
        assertEquals("ten", split[10]);
    }

    @Test
    public void splitObjectPath_SingleFilterProperty() {
        String[] split = ObjectPathUtils.explode("process:parent_ref.name", Arrays.asList("parent_ref"));

        assertEquals("process", split[0]);
        assertEquals("name", split[1]);
    }

    @Test
    public void splitObjectPath_MultipleFilterProperty() {
        String[] split = ObjectPathUtils.explode("process:parent_ref.test.name", Arrays.asList("parent_ref", "test"));

        assertEquals("process", split[0]);
        assertEquals("name", split[1]);
    }

    @Test
    public void splitObjectPath_InvalidObjectPath_ReturnsNull() {
        assertNull(ObjectPathUtils.explode("process"));
        assertNull(ObjectPathUtils.explode("process.name"));
        assertNull(ObjectPathUtils.explode("object:"));
        assertNull(ObjectPathUtils.explode("o"));
        assertNull(ObjectPathUtils.explode(""));
    }
}
