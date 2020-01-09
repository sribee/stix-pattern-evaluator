package design.unstructured.stix.evaluator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * Singleton provider for Jackson ObjectMapper reader and writer instances.
 */
public final class JacksonMapperProvider {

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Retrieves the core object mapper instance.
     */
    public static final ObjectMapper getMapper() {
        return mapper;
    }

    /**
     * Retrieves a generic JSON reader.
     */
    public static ObjectReader getReader() {
        return mapper.reader();
    }

    /**
     * Retrieves a JSON reader for the specified POJO class.
     */
    public static ObjectReader getReaderFor(Class<?> clazz) {
        return mapper.readerFor(clazz);
    }

    /**
     * Retrieves a generic JSON writer.
     */
    public static ObjectWriter getWriter() {
        return mapper.writer();
    }
}