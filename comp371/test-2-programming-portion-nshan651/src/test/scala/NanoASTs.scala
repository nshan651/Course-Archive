package nanol

/**
 * Some nanoL AST instances for testing.
 */
object NanoASTs:

  import NanoElement.*

  val atom = NanoAtom("hello")

  val li = NanoList(
      NanoAtom("hello"),
      NanoAtom("world")
  )

  /* Using the algebraic data type defined above (NanoElement, NanoList, and NanoAtom), construct an AST for the nanoL expression
  
    ( ( hello world ) ( what up ) ) 
  */
  val elem = NanoList(
    NanoList(
      NanoAtom("hello"),
      NanoAtom("world")
    ),
    NanoList(
      NanoAtom("what"),
      NanoAtom("up")
    )
  )


end NanoASTs
