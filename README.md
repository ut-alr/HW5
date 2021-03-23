# Congruence Closure Decision Procedure.

This repository contains the framework you must extend to solve
homework 5. In the following sections, we describe how you can build
and extend the framework. We present commands for UNIX-based
systems. However, these commands are simple enough, and it shouldn't
be hard to port them to other platforms.

## Building and Running the Project.

### Prerequisite.

This project is built with maven. If your system does not have maven
installed, follow system-specific instructions on how to install it
[here](https://maven.apache.org/install.html).

### Building the JAR file.

In order to build the project simply type the following command:

```
$ mvn package
```

This will create a JAR named
`hw5-1.0-SNAPSHOT-jar-with-dependencies.jar` under directory `target`.


### Running the Project.

To run the project simply run the following command:

```
$ java -cp target/hw5-1.0-SNAPSHOT-jar-with-dependencies.jar <fully qualified name of Main class>
```

The program accepts the formula through the standard input. If you are 
typing the expression manually on the terminal, you must also explicitly
type EOF (Ctrl+d in UNIX systems). The easiest way would be to redirect
the input from a file to the application, like the following:

```
$ java -cp target/hw5-1.0-SNAPSHOT-jar-with-dependencies.jar <fully qualified name of Main class> < <path to input file>
```

The JAR contains two main classes:

1. `edu.utexas.cs.alr.CCDriver`: This is the main class for the first
part of the assignment. It invokes the method `ExprUtils.checkSAT` which should run
your implementation of the Congurence Closure algorithm outlined in class.

2. `edu.utexas.cs.alr.CCInjectiveDriver`: This is the main class for the second
part of the assignment. It invokes method `ExprUtils.checkSATInjective` which should run
the variation of the congruence closure algorithm under the assumption that
the functions are injective.

By default, invoking the tool with any of the above classes is going
to throw an `UnsupportedOperationException` with the message
"implement this". To solve this problem set, you must implement the
body of all methods that throw this type of exceptions.

### Input Format.

The program accepts formulas with the following syntactic constraints. 

* Formulas are conjunctions of literals
* Literals are applications of equality predicates
* The only predicates allowed are equality predicates.
* Terms can be variables or function applications
* Function applications must take at least 1 argument
 
The input formulas expect equality predicates and functions to be in infix format e.g `(f1(x1, x2) = x2)` or
 `(f1(x2, x1) != x4)`. Function constants are of the form `fN` and variables are of the form `xN`,
where N is a positive integer (i.e., `N > 0`). The BNF grammar for the
input format can be found in
`src/main/antlr4/edu/utexas/cs/alr/parser/Expr.g4`. You can find some
sample input formulas under directory
`resources/sample-inputs`. 

## Framework Outline.

The framework provides some basic functionality for creating and
manipulating ASTs for propositional formulas. You must use the
provided components in order to implement the missing parts of the
framework (as also mentioned above). You can generally create any
additional classes you need for implementing the required
functionality. But you **cannot** modify the main classes (mentioned
above) and use any libraries except for Java's standard library.
