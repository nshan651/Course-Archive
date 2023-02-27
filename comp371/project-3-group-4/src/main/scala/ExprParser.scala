package edu.luc.cs.laufer.cs371.statements

import scala.util.parsing.combinator.JavaTokenParsers
import Expr.*

trait ExprParser[Result] extends JavaTokenParsers:

  /**
   * Enable missing typesafe equality for `~`.
   * TODO remove once the combinator parser library provides this.
   */
  given [A, B](using CanEqual[A, A], CanEqual[B, B]): CanEqual[A ~ B, A ~ B] = CanEqual.derived

  // NOTE: Use this: def expr: Parser[Result] = term ~ rep(("+" | "-") ~ term)
  
  /** expr ::= term { { "+" | "-" } term }* */
  //def expr: Parser[Result] = term ~! opt(("+" | "-") ~ term) ^^ onExpr
  def expr: Parser[Result] = term ~! rep(("+" | "-") ~ term) ^^ onExpr
  

  /** term ::= factor { { "*" | "/" | "%" } factor }* */
  //def term: Parser[Result] = factor ~! opt(("*" | "/" | "%") ~ factor) ^^ onTerm
  def term: Parser[Result] = factor ~! rep(("*" | "/" | "%") ~ factor) ^^ onTerm

  /** factor ::= wholeNumber | "+" factor | "-" factor | "(" expr ")" */
  def factor: Parser[Result] = 
    wholeNumber ^^ onNumber
    | "+" ~> factor ^^ onPlusFactor
    | "-" ~> factor ^^ onMinusFactor
    | "(" ~> expr <~ ")" ^^ onParenExpr
    //| ident ^^ onIdent

  // TODO: Add these statements

  /**
   * todo: to add support for stat in the program, change lines 7 and 13 in Calculator.scala:
   * instead of this ASTBuilder.expr use this ASTBuilder.stat
   * @return
   */
  def stat: Parser[Result] =
    expr ~ ";" ^^ onStat
    //| assignment ^^ onAssign
    //| conditional ^^ onCond
    //| loop ^^ onLoop
    //| block ^^ onBlock
  
  /** statement   ::= expression ";" | assignment | conditional | loop | block */
  //def statement: Parser[Expr] = 
  //  ident ~ "=" ~ expr ^^ { case s ~ _ ~ r => Assignment(Variable(s), r) } 
  /** assignment  ::= ident "=" expression ";" */
  /** conditional ::= "if" "(" expression ")" block [ "else" block ] */
  /** loop        ::= "while" "(" expression ")" block */
  /** block       ::= "{" statement* "}" */


  //def onExpr: Result ~ Option[String ~ Result] => Result
  //def onTerm: Result ~ Option[String ~ Result] => Result
  def onExpr: Result ~ List[String ~ Result] => Result
  def onTerm: Result ~ List[String ~ Result] => Result
  def onNumber: String => Result
  def onPlusFactor: Result => Result
  def onMinusFactor: Result => Result
  def onParenExpr: Result => Result
  // TODO: Add ident
  def onIdent: String => Result
  def onStat: Result ~ String => Result


end ExprParser
