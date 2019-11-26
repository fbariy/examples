package visitor

object PrintVisitor extends ExprVisitor[String] {
  def visitLiteral(expr: Literal): String = expr.number.toString

  def visitPlus(expr: Plus): String = s"${visit(expr.left)} + ${visit(expr.right)}"

  def visitUMinus(expr: UMinus): String = s"-${visit(expr.expr)}"

  def visitMinus(expr: Minus): String = s"${visit(expr.left)} - ${visit(expr.right)}"
}
