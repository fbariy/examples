package visitor

object ComputeVisitor extends ExprVisitor[Int] {
  def visitLiteral(expr: Literal): Int = expr.number

  def visitPlus(expr: Plus): Int = visit(expr.left) + visit(expr.right)

  def visitUMinus(expr: UMinus): Int = -visit(expr.expr)

  def visitMinus(expr: Minus): Int = visit(new Plus(expr.left, new UMinus(expr.right)))
}
