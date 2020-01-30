package rng

import scala.annotation.tailrec

case class RNG(seed: Long) {
  def nextInt: (Int, RNG) = {
    val newSeed = (seed * 0x5DDEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
    val nextRNG = RNG(newSeed)
    val n = newSeed >>> 16
    (n.toInt, nextRNG)
  }
}

object RNG {
  type Rand[+A] = RNG => (A, RNG)

  def nonNegativeInt(rng: RNG): (Int, RNG) = {
    val (number, nextRNG) = rng.nextInt
    val nonMinValueInt = if (number == Int.MinValue) number + 1 else number
    (nonMinValueInt.abs, nextRNG)
  }

  def double(rng: RNG): (Double, RNG) = {
    val (number, nextRNG) = rng.nextInt
    val nonMaxValueInt = if (number == Int.MaxValue) number - 1 else number
    ((nonMaxValueInt.toDouble / Int.MaxValue).abs, nextRNG)
  }

  def intDouble(rng: RNG): ((Int, Double), RNG) = {
    val (intNumber, intRNG) = rng.nextInt
    val (doubleNumber, doubleRNG) = RNG.double(intRNG)
    ((intNumber, doubleNumber), doubleRNG)
  }

  def doubleInt(rng: RNG): ((Double, Int), RNG) = {
    val (pair, nextRNG) = intDouble(rng)
    (pair.swap, nextRNG)
  }

  def double3(rng: RNG): ((Double, Double, Double), RNG) = {
    val (firstNumber, firstRNG) = RNG.double(rng)
    val (secondNumber, secondRNG) = RNG.double(firstRNG)
    val (thirdNumber, thirdRNG) = RNG.double(secondRNG)
    ((firstNumber, secondNumber, thirdNumber), thirdRNG)
  }

  def ints(count: Int)(rng: RNG): (List[Int], RNG) = {
    @tailrec
    def intsIter(currentRNG: RNG, acc: List[Int] = List()): (List[Int], RNG) = {
      if (acc.size >= count) (acc, currentRNG)
      else {
        val (number, nextRNG) = currentRNG.nextInt
        intsIter(nextRNG, acc :+ number)
      }
    }

    intsIter(rng)
  }
}
