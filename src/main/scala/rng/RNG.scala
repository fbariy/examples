package rng

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
    ((number.toDouble / Int.MaxValue).abs, nextRNG)
  }
}
