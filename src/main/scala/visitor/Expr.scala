package visitor

sealed trait Expr {
  def accept[T](visitor: ExprVisitor[T]): T
}

class Plus(val left: Expr, val right: Expr) extends Expr {
  def accept[T](visitor: ExprVisitor[T]): T = visitor.visitPlus(this)
}

class Literal(val number: Int) extends Expr {
  def accept[T](visitor: ExprVisitor[T]): T = visitor.visitLiteral(this)
}

class UMinus(val expr: Expr) extends Expr {
  def accept[T](visitor: ExprVisitor[T]): T = visitor.visitUMinus(this)
}

class Minus(val left: Expr, val right: Expr) extends Expr {
  def accept[T](visitor: ExprVisitor[T]): T = visitor.visitMinus(this)
}
