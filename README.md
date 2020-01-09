# stix-pattern-evaluator
This library is still considered a prototype and should not be depended on in a production environment until more development, testing, and documentation has been completed.

This project is a STIX v2.1 pattern compiler and evaluator written in Java 8 using the [ANTLR 4](https://www.antlr.org/) language parser. The ANTLR grammar is based on the OASIS [cti-stix2-json-schemas](https://github.com/oasis-open/cti-stix2-json-schemas) with some minor changes. The goal of this application is to compile a STIX Pattern expression and convert it into a logical Java structure. The project also provides means to evaluate the compiled pattern expressions.

It also provides STIX annotations and an object mapper to map your Java objects to the Cyber Observable data model (it is still a WIP). The mapper provides an interface in the event you plan to provide your own object mapper while still using the built-in pattern evaluator.

This was originally designed for another project of mine that has since been abandoned. I am repurposing the code for public use, which means there will be a lack of authoritative documentation until I find the time to migrate the existing documentation over. Once I have finished rewriting the unit tests, I will work on documenting.

## Requirements
- Java 8 or higher
- Maven