package rng

import org.scalatest.FlatSpec
import rng.RNG.Rand

class RNGSpec extends FlatSpec {
  "A nonNegativeInt" should "generates non negative int numbers" in
    assert(forAll(RNG.nonNegativeInt)(_ >= 0))

  "A double" should "generates double numbers between 0 and 1" in
    assert(forAll(RNG.double)(number => number >= 0 && number < 1))

  "A ints(5)" should "generates list of int with given length" in
    assert(forAll(RNG.ints(5))(_.length == 5))

  "A ints(0)" should "generates empty list" in
    assert(forAll(RNG.ints(0))(_.isEmpty))

  "A ints(-1)" should "generates empty list" in
    assert(forAll(RNG.ints(-1))(_.isEmpty))

  "A nonNegativeEventInt" should "generates non negative even int numbers" in
    assert(forAll(RNG.nonNegativeEventInt)(_ % 2 == 0))

  def forAll[A](rand: Rand[A])(p: A => Boolean, seed: Long = 0): Boolean = {
    var rng = RNG(seed)

    (1 to Math.pow(2, 10).toInt).toStream.forall(_ => {
      val (generated, nextRNG) = rand(rng)
      rng = nextRNG
      p(generated)
    })
  }
}
