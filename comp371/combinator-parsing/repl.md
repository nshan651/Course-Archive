# Combinator Parsers

```scala
import scala.util.parsing.combinator.JavaTokenParser

val p = new JavaTokenParsers() {}
val h = p.literal("hello")
val w = p.literal("world")
val a = p.literal("what")
val u = p.literal("up")


val sentence = h ~ w ~ p.rep1(a) ~ u
p.parseAll(sentence, "hello world what up")
p.parseAll(sentence, "hello world up")
p.parseAll(sentence, "hello world what what what up")

res3.get
res4._1
res4._1._2
res4._1._2.length
    
enum Shape:
     |   case Circle(radius: Int)
     |   case Group(children: Shape*)
     |
val circleMatch = p.literal("Circle") ~ p.literal("(") ~ p.wholeNumber ~ p.literal(")")
p.parseAll(circleMatch, "Circle ( 33 ) ")

import p.~
val circleBuild = circleMatch ^^ { // Changed from circle
    case _ ~ _ ~ r ~ _ => Shape.Circle(r.toInt) 
}
p.parseAll(circleBuild, "Circle ( 33 ) ")
res10.get.asInstanceOf[Shape.Circle].radius
def groupBuild: p.Parser[Shape] = p.literal("Group") ~ p.literal("(") ~ p.rep(circleBuild) ~ p.literal(")")
p.parseAll(groupBuild, "Group( Circle( 3), Circle (12) ) ")
```
