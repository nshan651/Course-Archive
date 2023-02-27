package nanol

import scala.util.parsing.combinator.JavaTokenParsers
import NanoElement.*

/**
 * An expression in nanoL is a list of the following form:
 *
 * (1a) an opening parenthesis, followed by
 * (1b) zero or more list elements, followed by
 * (1c) a closing parenthesis
 *
 * where a list element is either
 *
 * (2) a nanoL list (expression), or
 *
 * (3) an alphanumeric "atom" (i.e., an identifier starting with a letter)
 *
 * nanoL ignores whitespace around its tokens (parentheses and atoms).
 *
 * Define a parser that recognizes any expression in nanoL and constructs an
 * abstract syntax tree (AST) for it using the provided algebraic data type NanoElement.
 *
 * Be sure to include the following elements:
 *
 * - parsing rule(s) for recognizing expressions in the language
 * - semantic action(s) for building the corresponding abstract syntax tree using the data structure you defined previously
 */
object NanoParser extends JavaTokenParsers:

  // TODO implement the *three* productions (grammar rules) described above

 /** An opening parenthesis, followed by
    zero or more list elements, followed by
    a closing parenthesis
  */
  def elem: Parser[NanoElement] = 
    list

  /* A nanoL list (expression) */
  def list: Parser[NanoElement] = 
    atom 
    | "(" ~> rep(list) <~ ")" ^^ { case ne => NanoList(ne: _*) }

  /** Alphanumeric "atom" (i.e., an identifier starting with a letter) */
  def atom: Parser[NanoElement] = 
    ident ^^ { case n => NanoAtom(n) } 

end NanoParser
