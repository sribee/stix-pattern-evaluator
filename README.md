# stix-pattern-evaluator
This library is still considered a prototype and should not be depended on in a production environment until more development, testing, and documentation has been completed.

This project is a Cyber Threat Intelligence (CTI) STIX v2.1 pattern compiler and expression evaluator written in Java 8 using the [ANTLR 4](https://www.antlr.org/) language parser. The ANTLR grammar is based on the OASIS [cti-stix2-json-schemas](https://github.com/oasis-open/cti-stix2-json-schemas) with [some minor changes](https://github.com/hashdelta/stix-pattern-evaluator/tree/master/src/main/resources/StixPattern.g4). The goal of this application is to compile a STIX Pattern expression and convert it into a logical Java expression tree. The project also provides means to evaluate the compiled pattern expressions and provide results right out of the box.

It also provides STIX annotations and an object mapper to map your Java objects to the Cyber Observable data model (it is still a WIP). The mapper provides an interface in the event you plan to provide your own object mapper while still using the built-in pattern evaluator.

This was originally designed for another project of mine that has since been abandoned. I am repurposing the code for public use, which means there will be a lack of authoritative documentation until I find the time to migrate the existing documentation over.

## Development Roadmap
Since this library is still in its very infant stages, it might be necessary to provide a roadmap to a minimum viable product. This project is open to contributions; I ask that you follow the proper procedures below for creating a pull request.

1. Repurpose unit tests from previous project
2. Create / migrate necessary documentation for general use
3. Develop basic examples to demonstrate the implications of the pattern evaluator and STIX object mapper
4. General improvements to existing codebase; specifically, the STIX object mapper implementation.
5. Support for stateful operations such as `FOLLOWEDBY`, `WITHIN`, `REPEATS`.

## Requirements
- Java 8 +
- Maven

## License
Copyright (C) 2020 - Christopher Carver

Licensed under the GNU General Public License v3.