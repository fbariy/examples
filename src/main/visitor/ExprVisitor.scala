package visitor

trait ExprVisitor[T] {
  def visit(expr: Expr): T = expr.accept(this)
  def visitLiteral(expr: Literal): T
  def visitPlus(expr: Plus): T
  def visitUMinus(expr: UMinus): T
  def visitMinus(expr: Minus): T
}
