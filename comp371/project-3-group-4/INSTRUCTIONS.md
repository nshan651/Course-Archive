
Learning objectives

    An understanding of
        the compilation tool chain
        lexical analysis (tokenization)
        regular expressions
        syntax analysis (parsing)
        EBNF
        parser combinators
    Proficiency in
        domain models as algebraic data types and the pertinent Scala idiom
        structural recursion for defining behaviors for these domain models
        Scala parser combinators: API tutorial

Description

In this project, you will write a front end (lexical analysis and syntax analysis) for a Scala-based interpreter/REPL for the simple imperative language MiniJS (you will work on the actual interpreter in project 3b and subsequent stages).

Your recommended starting point is the combinator parser in the expressions-scala example.
Starting point for grammar

Here is a grammar in EBNF for the expression language from this example.

expression ::= term { { "+" | "-" } term }*       
term       ::= factor { { "*" | "/" | "%" } factor }*
factor     ::= number | "+" factor | "-" factor | "(" expression ")"

Extend grammar to support MiniJS's imperative constructs (statements)

First of all, you will need to add support for variables to factor:

factor ::= ident | ...

where an ident is a valid identifier of the form

ident ::= [a-zA-Z] [a-zA-Z0-9]*

meaning a letter followed by a combination of zero or more letters or digits. (You are welcome to include underscores as well.)

Next, you will add support for blocks, conditionals, loops, and assignments.

statement   ::= expression ";" | assignment | conditional | loop | block
assignment  ::= ident "=" expression ";"
conditional ::= "if" "(" expression ")" block [ "else" block ]
loop        ::= "while" "(" expression ")" block
block       ::= "{" statement* "}"

Extend abstract syntax tree to support the imperative constructs

Add case classes to the domain model (abstract syntax tree) to represent the new imperative constructs.

Follow the familiar domain-model-as-algebraic-data-type examples (shapes, org charts, etc.) These examples of abstract syntax trees for a simple imperative language should also help:

    misc-scala/imperative
    simpleimperative-algebraic-scala (advanced)

Optionally, extend toFormattedString to handle the new constructs; this behavior converts an AST instance to a string that shows the tree structure with indentation. Alternatively, print the AST instance directly (using its built-in toString method).
Extend the parser to support the imperative constructs

The parser converts concrete syntax (source code) to an abstract syntax tree (AST).

    Introduce new grammar productions for the five new constructs.
    Modify the top-level one to start with statement* (zero or more statements, same as a block without curly braces) instead of expression.
    Add tests for your parser including concrete syntax for at least all your existing examples and test fixtures as well as examples such as the ones below.

Extend the tree printer (toFormattedString) to support the imperative constructs

The tree printer (toFormattedString) converts an AST instance to a string that shows the indented tree structure with the node names spelled out, such as "Plus".
Implement an unparser (pretty-printer) to support expressions and imperative constructs

The unparser converts an AST instance back to concrete source syntax following some uniform rules. 

    Start with the behaviors in the bottom half of this example.
    Every expression should be converted back to infix for better human readability.
    Every statement should be on a separate line.
    Format if and while statements like so:

if (...) {
  ...
} else {
  ...
}

    Indent automatically in increments of two spaces based on nesting depth.

Example using my solution (the parsed statements DO NOT have to match exactly):

minijs>  if(-3+4+5*6){while(0){x=3;y=5;{xy=88;}}}
You entered:  if(-3+4+5*6){while(0){x=3;y=5;{xy=88;}}}
The parsed statements are:
Block(Cond(Plus(Plus(UMinus(Constant(3)),Constant(4)),Times(Constant(5),Constant(6))),Block(Loop(Constant(0),Block(Assign(Variable(x),Constant(3)),Assign(Variable(y),Constant(5)),Block(Variable(Assign(Variable(xy),Constant(88))))))),Block()))
The unparsed statements are:
{
  if (((-3 + 4) + (5 * 6))) {
    while (0) {
      x = 3;
      y = 5;
      {
        xy = 88;
      }
    }
  } else {
  }
}

Examples and testing

In addition to the expression examples, you should test your parser and unparser for various combinations of the imperative constructs, e.g.:

x = 5;

x = 5 ; y = 7;

((1 + y2) - (3 * y4)) / 5;

x = ((1 + y2) - (3 * y4)) / 5;

if (1) { x = 2; }

if (1) { x = 2; } else { x = 3; }

{ r = r + x; y = y + 1 ; }

if (4) { r = r + x; y = y + 1; }

while (y) { r = r + x; y = y - 1; }

while (y) { r = r + x ; y = y - 1 ;}

Also derive some incorrect examples from these and ensure that your parser rejects them.
Submission

For all stages of project 3, you will be developing your solution in a single GitHub group repository.

    As first step, you should have formed a team of total size two or three or four per project 0d.
    You should then accept this Github Classroom repository invite; at this point, you will be required to join or create a team.
    Therefore, team members should coordinate this step; the first team member to accept the invite should create a new team and share the team name with the remaining team members.
    The remaining team members should then also accept the repository invite and join their team.

As you work on this project, keep your repo current by committing and pushing frequently. Once your project is ready to grade, one group member should make a brief inline submission on Sakai with the complete repo URL and the short (7-digit) commit hash (or explicitly created tag p3a) corresponding to the submission of a particular project stage; also include the list of team members.

Only one submission per team is required.
Grading criteria

    1.5 grammar and resulting AST
    1.5 parser
    1 toFormattedString (showing tree structure)
    1 unparser/pretty printer (showing source code)
    1 testing
    1 REPL

TOTAL 7
Extra credit

    Use jline3 in your REPL; this is a low-hanging fruit that will make your testing experience more fun and efficient, so we recommend adding this right away. To do so, add this library dependency (look at doc for current version): "org.jline" % "jline" % "3.21.0"
    Support multi-line input: concatenate successive lines until the user enters a blank line.
    Support successive else if-branches.
    For the mathematically inclined, follow an F-algebraic approach for your AST and unparser using Droste. The guiding examples for this are here and here.
    Open to other suggestions...

