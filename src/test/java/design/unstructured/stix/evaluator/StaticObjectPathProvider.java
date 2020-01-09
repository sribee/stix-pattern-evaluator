package design.unstructured.stix.evaluator;

import java.util.HashMap;
import java.util.Map;

import design.unstructured.stix.evaluator.mapper.ObjectPathResolver;
import design.unstructured.stix.evaluator.mapper.StixMapperException;

public class StaticObjectPathProvider implements ObjectPathResolver {

    private final Map<String, Object> objectPaths = new HashMap<>();

    StaticObjectPathProvider add(String objectPath, Object objectValue) {
        objectPaths.put(objectPath, objectValue);

        return this;
    }

    @Override
    public Object getValue(Object object, String objectPath) throws StixMapperException {
        return objectPaths.get(objectPath);
    }

}