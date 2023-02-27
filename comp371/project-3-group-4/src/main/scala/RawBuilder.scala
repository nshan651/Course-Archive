package edu.luc.cs.laufer.cs371.statements

object RawBuilder extends StatParser[Any]:
  override def onExpr = identity
  override def onTerm = identity
  override def onNumber = identity
  override def onPlusFactor = identity
  override def onMinusFactor = identity
  override def onParenExpr = identity
  override def onStat = identity
  override def onAssignment = identity
  override def onVariable = identity
  override def onBlock = identity
  override def onCond = identity
  override def onLoop = identity

end RawBuilder
