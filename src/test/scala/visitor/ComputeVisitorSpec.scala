package visitor

import org.scalatest.FlatSpec

class ComputeVisitorSpec extends FlatSpec {
  "3" should behave like compute(
    new Literal(3),
    3
  )
  "20 - 5" should behave like compute(
    new Minus(new Literal(20), new Literal(5)),
    15
  )
  "2 - (-2)" should behave like compute(
    new Minus(new Literal(2), new UMinus(new Literal(2))),
    4
  )

  def compute(expr: Expr, result: Int): Unit = {
    it should "compute in result" in {
      assert(ComputeVisitor.visit(expr) == result)
    }
  }
}
