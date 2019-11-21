package visitor

sealed trait Expr {
  def compute: Int

  def print: String
}

class Plus(val left: Expr, val right: Expr) extends Expr {
  def compute: Int = left.compute + right.compute

  def print: String = s"${left.print} + ${right.print}"
}

class Literal(val number: Int) extends Expr {
  def compute: Int = number

  def print: String = number.toString
}

class UMinus(val expr: Expr) extends Expr {
  def compute: Int = -expr.compute

  def print: String = s"-${expr.print}"
}

class Minus(val left: Expr, val right: Expr) extends Expr {
  def compute: Int = new Plus(left, new UMinus(right)).compute

  def print: String = s"${left.print} - ${right.print}"
}
