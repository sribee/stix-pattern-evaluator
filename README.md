# stix-pattern-evaluator
<p style="text-align: center;">
    <a href="https://travis-ci.com/hashdelta/stix-pattern-evaluator">
        <img src="https://travis-ci.com/hashdelta/stix-pattern-evaluator.svg?branch=master" alt="build status">
    </a>
    <a href="https://codecov.io/gh/hashdelta/stix-pattern-evaluator">
        <img src="https://codecov.io/gh/hashdelta/stix-pattern-evaluator/branch/master/graph/badge.svg" alt="coverage" />
    </a>
    <a href="https://search.maven.org/artifact/design.unstructured/stix-pattern-evaluator">
        <img src="https://maven-badges.herokuapp.com/maven-central/design.unstructured/stix-pattern-evaluator/badge.svg" alt="maven central" />
    </a>
</p>

This library is still considered a prototype and should not be depended on in a production environment until more development, testing, and documentation has been completed.

This project is a Cyber Threat Intelligence (CTI) STIX v2.1 pattern compiler and expression evaluator written in Java 8 using the [ANTLR 4](https://www.antlr.org/) language parser. The ANTLR grammar is based on the OASIS [cti-stix2-json-schemas](https://github.com/oasis-open/cti-stix2-json-schemas) with [some minor changes](https://github.com/hashdelta/stix-pattern-evaluator/tree/master/src/main/resources/StixPattern.g4). The goal of this application is to compile a STIX Pattern expression and convert it into a logical Java expression tree. The project also provides means to evaluate the compiled pattern expressions and provide results right out of the box.

It also provides STIX annotations and an object mapper to map your Java objects to the [STIX Cyber Observable](https://docs.oasis-open.org/cti/stix/v2.1/csprd02/stix-v2.1-csprd02.html) (SCO) data model (it is still a WIP). The mapper provides an interface in the event you plan to provide your own object mapper while still using the built-in pattern evaluator.

This was originally designed for another project of mine that has since been abandoned. I am repurposing the code for public use, which means there will be a lack of authoritative documentation until I find the time to migrate the existing documentation over. If you are interested in the STIX Patterning semantics 

## Roadmap
Since this library is still in its infant stages, it might be necessary to provide a roadmap to a minimum viable product. This project is open to contributions.

1. Repurpose unit tests from previous project.
2. Create / migrate necessary documentation for general use.
3. Develop basic examples to demonstrate the implications of the pattern evaluator and STIX object mapper.
4. General improvements to existing codebase; specifically, the STIX object mapper implementation.
5. Support for stateful operations / qualifiers such as `FOLLOWEDBY`, `WITHIN`, `REPEATS`.

## Requirements
- Java 8 +
- Maven

## Getting Started
It is recommended to become familiar with STIX Patterning. The [OASIS specification](https://docs.oasis-open.org/cti/stix/v2.1/csprd02/stix-v2.1-csprd02.html) might be overwhelming; to make your life easier, take a look at [New Context's Quick Reference Card](examples/assets/STIX-Patterning-Quick-Reference-Card.pdf).

There are a few different ways to implement this library. Depending on your use case, you may want to use your own [`ComparisonEvaluator`](src/main/java/design/unstructured/stix/evaluator/ComparisonEvaluator.java) or use the built-in evaluator. These snippets will, at the very minimum, get you started compiling and evaluating basic STIX patterns. For more  examples, take a look at the [examples](examples/) (currently a placeholder) directory.

This library is available through Maven Central Repository.

```xml
<dependency>
  <groupId>design.unstructured</groupId>
  <artifactId>stix-pattern-evaluator</artifactId>
  <version>1.0.0-M1</version>
</dependency>
```

A simple `install` will run the necessary phases:
```bash
mvn install
```

### Compiling a STIX Pattern
The [`StixPatternProcessor`](src/main/java/design/unstructured/stix/evaluator/StixPatternProcessor.java) class is an implementation of the `StixPatternListener`. This is where the grammar rules are parsed and compiled. When compiling a pattern, ANTLR will walk through the expression and notify the listener when a grammar rule is triggered. When ANTLR is finished walking through the STIX pattern, a binary expression tree is compiled and wrapped in a [`Pattern`](src/main/java/design/unstructured/stix/evaluator/Pattern.java) object.


```java
// Compile our pattern
final Pattern compiledPattern = Pattern.build("[process:name = 'bad_behavior.exe']");

System.out.println(compiledPattern);
```

Output:
```
Pattern[ObservationExpression(ComparisonExpression(process:name, Equal, bad_behavior.exe))]
```

What is left is a `Pattern` object. This will not produce any results until you initialize a [`PatternEvaluator`](src/main/java/design/unstructured/stix/evaluator/PatternEvaluator.java) and provide an object path resolver. If you plan to implement your object path resolver, the `PatternEvaluator` provides a constructor to facilitate this.

### Resolving an Object Path
The object path is part of the SCO data model. In our above example, this would have been [`process:name`](https://docs.oasis-open.org/cti/stix/v2.1/csprd02/stix-v2.1-csprd02.html#_Toc26789822) in our expression. This example will demonstrate how to create your own `ObjectPathResolver` and pass it to an instance of `PatternEvaluator`.

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

## Operator Reference Guide
The STIX v2.1 specification supports 12 comparison operators. In the table below, operand `a` reprents the object path and operand `b` as a constant.

| Comparison Operators | Description                                                                                | Status              |
|:--------------------:|--------------------------------------------------------------------------------------------|---------------------|
|       `[a = b]`      | `a` equals `b`                                                                             | Supported           |
|      `[a != b]`      | `a` not equal to `b`                                                                       | Supported           |
|       `[a > b]`      | `a` greater than `b`                                                                       | Supported           |
|      `[a >= b]`      | `a` greater than or equal to `b`                                                           | Supported           |
|       `[a < b]`      | `a` less than `b`                                                                          | Supported           |
|      `[a <= b]`      | `a` less than or equal to `b`                                                              | Supported           |
|      `[a IN b]`      | `a` is equal to one of the constants in `b`. Operand `b` represents a `java.util.Set`.     | Supported           |
|    `[a MATCHES b]`   | `a` evaluates to `b`. Operand `b` represents a `java.util.regex.Pattern`.                  | Partially Supported |
|     `[a LIKE b]`     | `a` evaluates to `b`. Operand `b` represents an SQL `LIKE` in a `java.util.regex.Pattern`. | Work in Progress    |
|  `[a ISSUPERSET b]`  | More research required                                                                     | Not Supported       |
|   `[a ISSUBSET b]`   | More research required                                                                     | Not Supported       |

## License
Copyright (C) 2020 - Christopher Carver

Licensed under the GNU General Public License v3.