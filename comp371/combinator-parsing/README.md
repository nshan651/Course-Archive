# Combinator Parsers Group Activity

## Quick Notes

* '!' operator is a cut operator
    * It increases efficiency by explicitly telling the parser we are committing and will not be going back
* '~' operator means A depends on B (A ~ B)
`import scala.util.parsing.combinator.JavaTokenParsers`

Work with your existing group of two, three, or four students you set up for project 0d. Pairs of teams of size two are encouraged to join forces during this activity.

The objective is to put together a simple parser for some of the imperative language constructs from project 3a, though we'll do this mostly by exploring pieces of the solution in the REPL (or in an IDE, if you prefer).

    Study the parser in the expressions-scala example using the additional references below.
    Start a REPL that includes the following dependency:

    libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "2.1.1"

    You can set this in Scastie or use sbt consoleQuick in the project root of expressions-scala, or you can add source files to the latter.
    Create some parsers from scratch using this technique and play around with them:

    scala> val p = new JavaTokenParsers() {}
    val p: util.parsing.combinator.JavaTokenParsers = anon$1@1db183ac

    scala> val h = p.literal("hello")
    val h: p.Parser[String] = Parser ()

    scala> val w = p.literal("world")
    val w: p.Parser[String] = Parser ()

    scala> val a = p.literal("what")
    val a: p.Parser[String] = Parser ()

    scala> val u = p.literal("up")
    val u: p.Parser[String] = Parser ()

    scala> val sentence = h ~ w ~ p.rep1(a) ~ u
    val sentence: p.Parser[String ~ String ~ List[String] ~ String] = Parser (~)

    scala> p.parseAll(sentence, "hello world what up")
    val res1: p.ParseResult[String ~ String ~ List[String] ~ String] = [1.20] parsed: (((hello~world)~List(what))~up)

    scala> p.parseAll(sentence, "hello world up")
    val res2: p.ParseResult[String ~ String ~ List[String] ~ String] = [1.13] failure: 'what' expected but 'u' found

    hello world up
                ^

    scala> p.parseAll(sentence, "hello world what what what up")
    val res3: p.ParseResult[String ~ String ~ List[String] ~ String] = [1.30] parsed: (((hello~world)~List(what, what, what))~up)

    scala> res3.get
    val res4: String ~ String ~ List[String] ~ String = (((hello~world)~List(what, what, what))~up)

    scala> res4._1
    val res6: String ~ String ~ List[String] = ((hello~world)~List(what, what, what))

    scala> res4._1._2
    val res7: List[String] = List(what, what, what)

    scala> res4._1._2.length
    val res8: Int = 3
    Note that strings automatically get converted to literal parsers inside a class that implements JavaTokenParsers.
    Now check out these steps toward a parser for shapes from our project 1a:

    scala> enum Shape:
         |   case Circle(radius: Int)
         |   case Group(children: Shape*)
         |
    // defined class Shape

    scala> val circleMatch = p.literal("Circle") ~ p.literal("(") ~ p.wholeNumber ~ p.literal(")")
    val circleMatch: p.Parser[String ~ String ~ String ~ String] = Parser (~)

    scala> p.parseAll(circleMatch, "Circle ( 33 ) ")
    val res9: p.ParseResult[String ~ String ~ String ~ String] = [1.15] parsed: (((Circle~()~33)~))

    scala> import p.~

    scala> val circleBuild = circle ^^ { case _ ~ _ ~ r ~ _ => Shape.Circle(r.toInt) }
    val circleBuild: p.Parser[Shape] = Parser (Parser (~)^^)

    scala> p.parseAll(circleBuild, "Circle ( 33 ) ")
    val res10: p.ParseResult[Shape] = [1.15] parsed: Circle(33)

    scala> res10.get.asInstanceOf[Shape.Circle].radius
    val res11: Int = 33

    Amazing, isn't it? Now we'll add the ability to parse groups of circles:

    scala> def groupBuild: p.Parser[Shape] = p.literal("Group") ~ p.literal("(") ~ p.rep(circleBuild) ~ p.literal(")")
    -- [E007] Type Mismatch Error: -------------------------------------------------
    1 |def groupBuild: p.Parser[Shape] = p.literal("Group") ~ p.literal("(") ~ p.rep(circleBuild) ~ p.literal(")")
      |                                  ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
      |                Found:    p.Parser[String ~ String ~ List[Shape] ~ String]
      |                Required: p.Parser[Shape]
      |
      | longer explanation available when compiling with −expla∈


    1 error found

    scala> def groupBuild: p.Parser[Shape] = p.literal("Group") ~ p.literal("(") ~ p.repsep(circleBuild, ",") ~ p.literal(")") ^^ { case _ ~ _ ~ circles ~ _ => Shape.Group(circles: _*) }
    def groupBuild: p.Parser[Shape]

    scala> p.parseAll(groupBuild, "Group( Circle( 3), Circle (12) ) ")
    val res13: p.ParseResult[Shape] = [1.34] parsed: Group(List(Circle(3), Circle(12)))

Discuss your thoughts on the following questions:

    What will it take to support nested groups of shapes?
    What will it take to add the other kinds of shapes from project 1a?
    How can you apply these techniques to project 3a?
    What else did you find noteworthy or worth wondering about?

Document your thoughts in a shared Google, Office 365, or similar document (be sure to share with TA and instructor); include your code snippets as you see fit. Only one Sakai inline submission per group is required. Clearly list all group members!

In addition, prepare to present your findings in class.
References

    github.com/lucproglangcourse/expressions-scala
    Scaladoc for JavaTokenParsers
    https://learning.oreilly.com/library/view/scala-for-the/9780134540627/ch20.html
    relevant section in the course notes

