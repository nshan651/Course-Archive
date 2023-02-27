package edu.luc.cs.laufer.cs371.statements

/** An initial algebra of arithmetic expressions. */
enum Expr derives CanEqual:
  
  case Constant(value: Int)
  case UMinus(expr: Expr)
  case Plus(left: Expr, right: Expr)
  case Minus(left: Expr, right: Expr)
  case Times(left: Expr, right: Expr)
  case Div(left: Expr, right: Expr)
  case Mod(left: Expr, right: Expr)
  case Variable(value: String)
  case Assignment(left: Expr, right: Expr)
  case Block(stats: List[Expr])
  case Cond(cond: Expr, ifBody: Expr, elseBody: Expr)
  case Loop(guard: Expr, body: Expr)
  