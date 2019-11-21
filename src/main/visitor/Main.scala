package visitor

object Main {
  def main(args: Array[String]): Unit = {
    val expr = new Minus(new Literal(10), new Literal(1))
    println(expr.compute) // print: 9
    println(expr.print) // print: 10 - 1
  }
}
