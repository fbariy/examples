package rng

case class RNG(seed: Long) {
  def nextDouble: (Double, RNG) = {
    val newSeed = (seed * 0x5DDEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL
    val nextRNG = RNG(newSeed)
    val n = newSeed >>> 16
    (n, nextRNG)
  }
}
