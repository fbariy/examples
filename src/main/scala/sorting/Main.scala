package sorting

object Main {
  def main(args: Array[String]): Unit = {
    val list = List(2, 4, 7,1, 2, 4,81,123, 1234,123,43,6,124)
    println(iSort(list))

    def iSort(list: List[Int]): List[Int] = list match {
      case List() => List()
      case h :: tail => insert(h, iSort(tail))
    }

    def insert(x: Int, sorted: List[Int]): List[Int] = sorted match {
      case List() => List(x)
      case h :: tail => if (x < h) x :: sorted else h :: insert(x, tail)
    }
  }
}