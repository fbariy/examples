package rng

import org.scalatest.FlatSpec
import rng.RNG.Rand

class RNGSpec extends FlatSpec {
  "A nonNegativeInt" should "generates non negative int numbers" in
    assert(forAll(RNG.nonNegativeInt)(_ >= 0))

  "A double" should "generates double numbers between 0 and 1" in
    assert (forAll(RNG.double)(number => number >= 0 && number <= 1))


  def forAll[A](rand: Rand[A])(p: A => Boolean, seed: Long = 0): Boolean = {
    var rng = RNG(seed)

    (1 to Math.pow(2, 10).toInt).toStream.forall(_ => {
      val (number, nextRNG) = rand(rng)
      rng = nextRNG
      p(number)
    })
  }
}
