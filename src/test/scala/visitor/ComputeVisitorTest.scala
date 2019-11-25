package visitor

import org.scalatest.FunSuite

class ComputeVisitorTest extends FunSuite {
  test("ComputeExprVisitor.visitLiteral") {
    val literal = new Literal(42)
    assert(ComputeVisitor.visitLiteral(literal) === 42)
  }

  //@todo: overwrite with provider of cases
}
