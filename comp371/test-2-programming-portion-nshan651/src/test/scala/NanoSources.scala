package nanol

/**
 * Some valid and invalid nanoL source strings for testing.
 */
object NanoSources:

/*
 A. (hello (world what ()) (up))
 B. (hello world what up)
 C. (hello)
 D. the empty string
 E. ( ( ( ) ) ( ) )
 F. ("hello" "world" "what" "up")
 G. ( ( ) ) ( ) )
 H. hello
 I. ( ( hello world ) ( what up ) )
*/
  
  val a = "(hello (world what ()) (up))"
  val b = "(hello world what up)"
  val c = "(hello)"
  val d = "the empty string"
  val e = "( ( ( ) ) ( ) )"
  val f = "(\"hello\" \"world\" \"what\" \"up\")"
  val g = "( ( ) ) ( ) )"
  val h = "hello"
  val i = "( ( hello world ) ( what up ) )"

end NanoSources
