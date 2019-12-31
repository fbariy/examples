package matching

sealed trait Expr {
  val priority: Int

  def show: String = this match {
    case Number(x) => s"$x"
    case Var(x) => s"$x"
    case Sum(left, right) => s"${left.show} + ${right.show}"
    case Prod(left, right) =>
      val leftStr = if (left.isOperation && left.priority < this.priority) s"(${left.show})" else left.show
      val rightStr = if (right.isOperation && right.priority < this.priority) s"(${right.show})" else right.show
      //todo: another time doing refactor
      leftStr + " * " + rightStr
  }

  def isOperation = this match {
    case Number(_) => false
    case Var(_) => false
    case _ => true
  }
}

case class Number(number: Int) extends Expr {
  val priority: Int = 0
}

case class Sum(left: Expr, right: Expr) extends Expr {
  val priority: Int = 1
}

case class Prod(left: Expr, right: Expr) extends Expr {
  val priority: Int = 2
}

case class Var(variable: String) extends Expr {
  val priority: Int = 0
}