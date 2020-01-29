package rng

object Main {
  type Rand[+A] = RNG => (A, RNG)

  def main(args: Array[String]): Unit = {

  }

//  val nonNegative: Rand[Int] = nonNegativeInt
//  val nonNegativeIntEven: Rand[Int] = map(nonNegative)(a => a - a % 2)
//
////  def nonNegativeInt: Rand[Int] = ???
//
//
//  def unit[A](a: A): Rand[A] =
//    rng => (a, rng)
//
//  def map[A, B](rand: Rand[A])(f: A => B): Rand[B] = {
//    rng => {
//      val (value, nextRNG) = rand(rng)
//      (f(value), nextRNG)
//    }
//  }
}
