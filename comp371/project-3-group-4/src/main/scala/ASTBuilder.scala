package edu.luc.cs.laufer.cs371.statements

import Expr.*

object ASTBuilder extends StatParser[Expr]:

  override def onLoop: Expr ~ String ~ Expr => Expr =
    case e ~ ")" ~ es => {
    Loop(e, es)
  }

  override def onCond: Expr ~ String ~ Expr ~ Option[String ~ Expr] => Expr =
    case e ~ ")" ~ es1 ~ None => {
      Cond(e, es1, Block(List.empty))
    }
    case e ~ ")" ~ es1 ~ Some("else" ~ es2) => {
      Cond(e, es1, es2)
    }

  override def onBlock: List[Expr] => Expr =
    case es => Block(es)

  override def onStat: Expr => Expr =
    case l => l

  override def onExpr: Expr ~ List[String ~ Expr] => Expr =
    case e ~ es => es.foldLeft(e){
      case (l, "+" ~ r) => Plus(l, r)
      case (l, "-" ~ r) => Minus(l, r)
    }

  override def onAssignment: String ~ String ~ Expr => Expr =
    case l ~ "=" ~ r => Assignment(Variable(l), r)
  
  override def onTerm: Expr ~ List[String ~ Expr] => Expr =
    case e ~ es => es.foldLeft(e) {
      case (l, "*" ~ r) => Times(l, r)
      case (l, "/" ~ r) => Div(l, r)
      case (l, "%" ~ r) => Mod(l, r)
    }

  override def onNumber = Constant.apply compose (_.toInt)

  override def onPlusFactor = identity

  override def onMinusFactor = UMinus.apply

  override def onParenExpr = identity
  
  override def onVariable = Variable.apply

end ASTBuilder
