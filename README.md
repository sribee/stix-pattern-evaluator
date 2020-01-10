# stix-pattern-evaluator
This library is still considered a prototype and should not be depended on in a production environment until more development, testing, and documentation has been completed.

This project is a Cyber Threat Intelligence (CTI) STIX v2.1 pattern compiler and expression evaluator written in Java 8 using the [ANTLR 4](https://www.antlr.org/) language parser. The ANTLR grammar is based on the OASIS [cti-stix2-json-schemas](https://github.com/oasis-open/cti-stix2-json-schemas) with [some minor changes](https://github.com/hashdelta/stix-pattern-evaluator/tree/master/src/main/resources/StixPattern.g4). The goal of this application is to compile a STIX Pattern expression and convert it into a logical Java expression tree. The project also provides means to evaluate the compiled pattern expressions and provide results right out of the box.

It also provides STIX annotations and an object mapper to map your Java objects to the [STIX Cyber Observable](https://docs.oasis-open.org/cti/stix/v2.1/csprd02/stix-v2.1-csprd02.html#_Toc26789822) data model (it is still a WIP). The mapper provides an interface in the event you plan to provide your own object mapper while still using the built-in pattern evaluator.

This was originally designed for another project of mine that has since been abandoned. I am repurposing the code for public use, which means there will be a lack of authoritative documentation until I find the time to migrate the existing documentation over.

## Development Roadmap
Since this library is still in its infant stages, it might be necessary to provide a roadmap to a minimum viable product. This project is open to contributions.

1. Repurpose unit tests from previous project.
2. Create / migrate necessary documentation for general use.
3. Develop basic examples to demonstrate the implications of the pattern evaluator and STIX object mapper.
4. General improvements to existing codebase; specifically, the STIX object mapper implementation.
5. Support for stateful operations such as `FOLLOWEDBY`, `WITHIN`, `REPEATS`.

## Requirements
- Java 8 +
- Maven


## Using stix-pattern-evaluator in your project
This library is available through Maven Central Repository.

```xml
<dependency>
  <groupId>design.unstructured</groupId>
  <artifactId>stix-pattern-evaluator</artifactId>
  <version>1.0.0-M1</version>
</dependency>
```

There are a few different ways to implement this library. Depending on your use case, you may want to use your own expression tree evaluator or use the built-in evaluator. These snippets will, at the very minimum, get you started compiling and evaluating basic STIX expressions. For more  examples, take a look at the [examples](examples/) (still a WIP) directory.

### Compiling a basic STIX pattern
The [`StixPatternProcessor`](https://github.com/hashdelta/stix-pattern-evaluator/blob/master/src/main/java/design/unstructured/stix/evaluator/StixPatternProcessor.java) class is where the magic happens. When compiling a pattern, ANTLR will walk through the expression and notify the listener when a grammar rule is triggered. When ANTLR is finished walking through the STIX pattern, a binary expression tree structure is compiled and contained within the [`Pattern`](https://github.com/hashdelta/stix-pattern-evaluator/blob/master/src/main/java/design/unstructured/stix/evaluator/Pattern.java) object.

```java
// Compile our pattern
final Pattern compiledPattern = Pattern.build("[process:name = 'bad_behavior.exe']");
```

As you can see, it is very straight forward. This is a very basic expression tree with only one condition. This will not produce any results until you initialize a `PatternEvaluator` and provide an object resolver. As mentioned above, there may be a specific use case where you would want to provide your own.

### Resolving the object path
The object path is part of the [STIX Cyber Observable](https://docs.oasis-open.org/cti/stix/v2.1/csprd02/stix-v2.1-csprd02.html#_Toc26789822) data model. In our above example, this would be the `process:name` in our condition. This example will demonstrate how the `PatternEvaluator` interacts with the `ObjectPathResolver`.

First, we need to create a static resolver:
```java
public class StaticObjectPathResolver implements ObjectPathResolver {
    private final Map<String, Object> objectPaths = new HashMap<>();

    StaticObjectPathResolver add(String objectPath, Object objectValue) {
        objectPaths.put(objectPath, objectValue);

        return this;
    }

    @Override
    public Object getValue(Object object, String objectPath) throws StixMapperException {
        return objectPaths.get(objectPath);
    }
}
```

Initialize our static resolver with some dummy data and evaluate:
```java
// Create an instance of our static resolver and add some dummy data
final StaticObjectPathResolver resolver = new StaticObjectPathResolver()
    .add("process:name", "bad_behavior.exe")
    .add("process:id", 498);

// Create a pattern with AND comparator
final Pattern compiledPattern = Pattern.build("[process:name = 'bad_behavior.exe' AND process:id = 498]");

// Create an instance of our PatternEvaluator, pass an empty object
final PatternEvaluator evaluator = new PatternEvaluator(compiledPattern, resolver, null);

if (evaluator.get()) {
    System.out.println("Success!");
} else {
    System.out.println("Well, this is odd...");
}
```

## License
Copyright (C) 2020 - Christopher Carver

Licensed under the GNU General Public License v3.