package design.unstructured.stix.evaluator;

public class StixPattern {

    private String name;

    private String description;

    private String pattern;

    private Pattern parsedPattern;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPattern() {
        return pattern;
    }

    public Pattern getParsedPattern() {
        return parsedPattern;
    }

    public void setParsedPattern(Pattern parsedPattern) {
        this.parsedPattern = parsedPattern;
    }
}