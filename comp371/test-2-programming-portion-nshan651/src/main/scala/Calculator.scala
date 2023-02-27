package nanol

import NanoParser.*
import size.*

object Calculator:

  def processExpr(input: String): Unit =
    println("___________________________________")
    println("You entered: " + input)
    val result = NanoParser.parseAll(NanoParser.elem, input)
    if result.isEmpty then
      println("This expression could not be parsed")
      println("___________________________________")
    else
      val stat = result.get
      println("The resulting expression is: " + stat)
      println("It has size " + size(stat))
      println("___________________________________")


  def main(args: Array[String]): Unit =
    val lines: Seq[String] = Seq(
      "(hello (world what ()) (up))",
      "(hello world what up)",
      "(hello)",
      "the empty string", 
      "( ( ( ) ) ( ) )", 
      "(\"hello\" \"world\" \"what\" \"up\")",
      "( ( ) ) ( ) )",
      "hello",
      "( ( hello world ) ( what up ) )",
      "atom1", 
      "( ( hello world ) ( what up ) )"
    )
    
    for (line <- lines) 
      processExpr(line)

end Calculator
