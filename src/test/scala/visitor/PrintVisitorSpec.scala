package visitor

import org.scalatest.FlatSpec

class PrintVisitorSpec extends FlatSpec {
  "3" should behave like print(
    new Literal(3),
    "3"
  )
  "20 - 5" should behave like print(
    new Minus(new Literal(20), new Literal(5)),
    "20 - 5"
  )
  "2 - -2" should behave like print(
    new Minus(new Literal(2), new UMinus(new Literal(2))),
    "2 - -2"
  )

  def print(expr: Expr, result: String): Unit = {
    it should "print as result" in {
      assert(PrintVisitor.visit(expr) == result)
    }
  }
}
