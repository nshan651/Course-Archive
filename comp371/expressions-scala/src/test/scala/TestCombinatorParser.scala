package edu.luc.cs.laufer.cs371.expressions

import org.scalatest.funsuite.AnyFunSuite
import TestFixtures.*
import edu.luc.cs.laufer.cs371.expressions.ast.Expr

object MainCombinatorParser:
  def main(args: Array[String]): Unit =
    val parsedExpr = CombinatorParser.parseAll(CombinatorParser.expr, complex1string)
    println(parsedExpr.get)
    println(complex1)
    println(parsedExpr.get == complex1)
    println(behaviors.evaluate(parsedExpr.get))
end MainCombinatorParser

class TestCombinatorParser extends AnyFunSuite:
  val parsedExpr = CombinatorParser.parseAll(CombinatorParser.expr, complex1string)
  val parsedExpr2 = CombinatorParser.parseAll(CombinatorParser.expr, complex1string2)
  test("parser works 1") { assert(parsedExpr.get == complex1) }
  test("parser works 2") { assert(parsedExpr2.get == complex1) }
end TestCombinatorParser
