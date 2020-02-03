package design.unstructured.stix.evaluator.mapper;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

/**
 * This utility class is designed to provide minimal helper functionality around the STIX
 * Cyber-observable Objects per STIX v2.1 documentation. It does not provide linting in any form.
 */
public class ObjectPathUtils {

    /**
     * {@code propertyFilters} defaults to {@code null}.
     *
     * @see #toArray(String, List)
     */
    public static String[] toArray(String objectString) {
        return toArray(objectString, null);
    }

    /**
     * Separates a STIX observable object path string into a string array. Property filters will be
     * applied to the returned object path array. A null will be returned for an invalid object path.
     * 
     * @param objectString
     * @param propertyFilters
     * @return An array of STIX observable object and properties
     */
    public static String[] toArray(String objectString, List<String> propertyFilters) {
        int objectPosition = objectString.indexOf(':');

        if (objectPosition < 1) {
            // No object was specified
            return null;
        }

        String object = objectString.substring(0, objectPosition);
        String properties = objectString.substring(objectPosition + 1);

        if (properties.isEmpty()) {
            // No property path specified
            return null;
        }

        // Filter out any paths defined in our propertyFilters
        if (propertyFilters != null && !propertyFilters.isEmpty()) {
            for (String filter : propertyFilters) {
                properties = properties.replace(filter + ".", "");
            }
        }

        String[] propertyPath = stringSplit(properties, '.');

        if (propertyPath.length == 1) {
            // There is only a single property, we can create the array manually to avoid overhead
            propertyPath = new String[] {object, properties};

        } else {
            // There is more than one property, use ArrayUtils to prepend our observable 'object'
            propertyPath = ArrayUtils.insert(0, propertyPath, object);
        }

        return propertyPath;
    }

    /**
     * Joins a string array to form a valid STIX observable object path. This does not validate whether
     * the path points a known observable.
     * 
     * @param objectPath
     * @return
     */
    public static String toPath(String[] objectPath) {
        StringBuilder joined = new StringBuilder();

        for (int i = 0; i < objectPath.length; i++) {
            char delimeter = (i == 0 ? ':' : '.');

            joined.append(objectPath[i]).append(delimeter);
        }

        return joined.substring(0, joined.length() - 1);
    }

    /**
     * To avoid using Java's {@code String::split(...)} and taking a performance hit, here is an
     * implementation that does not use regular expression.
     * 
     * Unknown author.
     * 
     * @param line
     * @param delimiter
     * @return
     */
    private static String[] stringSplit(final String line, final char delimiter) {
        CharSequence[] temp = new CharSequence[(line.length() / 2) + 1];
        int wordCount = 0;
        int i = 0;
        int j = line.indexOf(delimiter, 0); // first substring

        while (j >= 0) {
            temp[wordCount++] = line.substring(i, j);
            i = j + 1;
            j = line.indexOf(delimiter, i); // rest of substrings
        }

        temp[wordCount++] = line.substring(i); // last substring

        String[] result = new String[wordCount];
        System.arraycopy(temp, 0, result, 0, wordCount);

        return result;
    }
}
