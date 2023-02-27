package nanol

/**
 * Counts the number of atoms in a nanoL expression.
 */
object size:

  import NanoElement.*

  def apply(e: NanoElement): Int = e match
    case NanoAtom(n) => 1 
    case NanoList(ne) => 1 + apply(ne)

end size
