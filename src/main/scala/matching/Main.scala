package matching

object Main {
  def main(args: Array[String]): Unit = {

    // 3 * (2 + 4) * (2 * 3 + 2)
    val expr = Prod(Prod(Number(3), Sum(Number(2), Number(4))), Sum(Prod(Number(2), Number(3)), Number(2)))

    println (expr show)
  }
}
