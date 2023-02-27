package nanol

/**
 * AST for nanoL.
 */
enum NanoElement derives CanEqual:
  case NanoList(children: NanoElement*)
  case NanoAtom(name: String)
