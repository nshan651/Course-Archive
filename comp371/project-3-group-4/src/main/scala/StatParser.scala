package edu.luc.cs.laufer.cs371.statements

import scala.util.parsing.combinator.JavaTokenParsers
import Expr.*

trait StatParser[Result] extends JavaTokenParsers {
  given[A, B](using CanEqual[A, A], CanEqual[B, B]): CanEqual[A ~ B, A ~ B] = CanEqual.derived

  def repl: Parser[Result] = rep(stat) ^^ onBlock
  
  def stat: Parser[Result] =
    expr <~ ";" ^^ onStat | assignment | block | cond | loop

  def cond: Parser[Result] = "if" ~ "(" ~> expr ~ ")" ~ block ~ opt("else" ~ block) ^^ onCond

  def loop: Parser[Result] = "while" ~ "(" ~> expr ~ ")" ~ block ^^ onLoop

  def block: Parser[Result] = "{" ~> rep(stat) <~ "}" ^^ onBlock
  
  def assignment: Parser[Result] = ident ~ "=" ~ expr <~ ";" ^^ onAssignment

  /** expr ::= term { { "+" | "-" } term }* */
  def expr: Parser[Result] = term ~! rep(("+" | "-") ~ term) ^^ onExpr

  /** term ::= factor { { "*" | "/" | "%" } factor }* */
  def term: Parser[Result] = factor ~! rep(("*" | "/" | "%") ~ factor) ^^ onTerm

  /** factor ::= wholeNumber | "+" factor | "-" factor | "(" expr ")" */
  def factor: Parser[Result] =
    wholeNumber ^^ onNumber
      | "+" ~> factor ^^ onPlusFactor
      | "-" ~> factor ^^ onMinusFactor
      | "(" ~> expr <~ ")" ^^ onParenExpr
      | ident ^^ onVariable

  def onExpr: Result ~ List[String ~ Result] => Result
  def onTerm: Result ~ List[String ~ Result] => Result
  def onNumber: String => Result
  def onPlusFactor: Result => Result
  def onMinusFactor: Result => Result
  def onParenExpr: Result => Result
  def onVariable: String => Result
  def onAssignment: String ~ String ~ Result  => Result
  def onStat: Result => Result
  def onBlock: List[Result] => Result
  def onCond: Result ~ String ~ Result ~ Option[String ~ Result] => Result
  def onLoop: Result ~ String ~ Result => Result

}
