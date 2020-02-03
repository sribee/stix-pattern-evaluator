package design.unstructured.stix.evaluator.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.LogManager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * StixMapperTest
 */
public class StixMapperTest {

    Process testProcess = new Process();

    StixObservableMapper mapper;

    @BeforeAll
    static void configureLogger() throws SecurityException, IOException {
        LogManager.getLogManager().readConfiguration(ClassLoader.getSystemResource("jul-log.properties").openStream());
    }

    @BeforeEach
    void setup() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(Process.class);
        classes.add(ProcessInfo.class);
        mapper = new StixObservableMapper(classes);

        ProcessInfo processInfo = new ProcessInfo();

        processInfo.setName("ping.exe");
        processInfo.setPath("C:\\Windows\\System32");
        processInfo.setCommandLine("ping.exe google.com -t");
        processInfo.setRemapToId(598);
        testProcess.setInfo(processInfo);
    }

    @Test
    void validateObservableObjects() {
        assertTrue(mapper.getObservableTree().containsKey("process"));
        assertTrue(mapper.getObservableTree().containsKey("process:parent_ref"));
        assertTrue(mapper.getObservableTree().containsKey("process"));
        assertTrue(mapper.getObservableTree().containsKey("process:name"));
        assertTrue(mapper.getObservableTree().containsKey("process:command_line"));
        assertTrue(mapper.getObservableTree().containsKey("process:pid"));
        assertTrue(mapper.getObservableTree().containsKey("process:id"));
        assertFalse(mapper.getObservableTree().containsKey("process:remapToId"));
    }

    @Test
    void getValue_FromObservables() throws StixMappingException {
        assertEquals("ping.exe", mapper.getValue(testProcess, "process:name"));
        assertEquals("C:\\Windows\\System32", mapper.getValue(testProcess, "process:path"));
        assertEquals("ping.exe google.com -t", mapper.getValue(testProcess, "process:command_line"));
        assertEquals(598, mapper.getValue(testProcess, "process:pid"));
    }

    @Test
    void getValue_FromParentObservables() throws StixMappingException {
        ProcessInfo processInfo = new ProcessInfo();

        processInfo.setName("cmd.exe");
        processInfo.setPath("C:\\Windows\\System32");
        processInfo.setCommandLine("cmd.exe --nothing-special");
        processInfo.setRemapToId(387);
        testProcess.setParent(new Process());
        testProcess.getParent().setInfo(processInfo);

        assertEquals("cmd.exe", mapper.getValue(testProcess, "process:parent_ref.name"));
        assertEquals("C:\\Windows\\System32", mapper.getValue(testProcess, "process:parent_ref.path"));
        assertEquals("cmd.exe --nothing-special", mapper.getValue(testProcess, "process:parent_ref.command_line"));
        assertEquals(387, mapper.getValue(testProcess, "process:parent_ref.pid"));
    }

    @Test
    void getValue_FromNotExistentObservable_ThrowsException() throws StixMappingException {
        assertThrows(StixMappingException.class, () -> {
            mapper.getValue(testProcess, "process:ad");
        });

        assertThrows(StixMappingException.class, () -> {
            mapper.getValue(testProcess, "process1");
        });

        assertThrows(StixMappingException.class, () -> {
            mapper.getValue(testProcess, "");
        });
    }
}
