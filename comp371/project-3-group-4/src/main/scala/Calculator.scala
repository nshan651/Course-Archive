package edu.luc.cs.laufer.cs371.statements

import scala.collection.mutable
import scala.util.Try

object Calculator:

  var mem = mutable.Map.empty[String, Int]

  def processExpr(input: String): Unit =
    println("You entered: " + input)
    val result = ASTBuilder.parseAll(ASTBuilder.repl, input)
    if result.isEmpty then
      println("This expression could not be parsed")
    else
      import org.json4s.native.JsonMethods.{pretty, render}
      import behaviors.*
      val raw = RawBuilder.parseAll(RawBuilder.repl, input).get
      println("The untyped parse tree is: " + raw)
      val stat = result.get
      println("The resulting expression is: " + stat)
      println("The corresponding JSON structure is:")
      println(pretty(render(toJson(stat))))
      println("It evaluates to " + Try(apply(mem)(stat)) + "\n")
      println("Pretty printed:\n" + pprinter(stat))

  def main(argscala/scala3-cross.g8s: Array[String]): Unit =
    if args.length > 0 then
      processExpr(args mkString " ")
    else
      println("mem: " + mem)
      print("minijs> ")
      scala.io.Source.stdin.getLines() foreach { line =>
        processExpr(line)
        println("mem: " + mem)
        print("minijs> ")
      }

end Calculator
