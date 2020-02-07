package rng

import org.scalatest.FlatSpec
import rng.RNG.Rand

class RNGSpec extends FlatSpec {
  "A nonNegativeInt" should "generates non negative int numbers" in
    assert(forAll(RNG.nonNegativeInt)(_ >= 0))

  "A double" should "generates double numbers between 0 and 1" in
    assert(forAll(RNG.double)(number => number >= 0 && number < 1))

  "A ints" should "generates list of int with 5 length" in
    assert(forAll(RNG.ints(5))(_.length == 5))

  it should "generates empty list by 0 count" in
    assert(forAll(RNG.ints(0))(_.isEmpty))

  it should "generates empty list by -1 count" in
    assert(forAll(RNG.ints(-1))(_.isEmpty))

  "A nonNegativeEvenInt" should "generates non negative even int numbers" in
    assert(forAll(RNG.nonNegativeEvenInt)(_ % 2 == 0))

  "A nonNegativeLessThan" should "generates non negative number less than 5" in
    assert(forAll(RNG.nonNegativeLessThan(5))(number => number >= 0 && number < 5))

  def forAll[A](rand: Rand[A])(p: A => Boolean, seed: Long = 0): Boolean = {
    var rng = RNG(seed)

    (1 to Math.pow(2, 10).toInt).toStream.forall(_ => {
      val (generated, nextRNG) = rand(rng)
      rng = nextRNG
      val bool = p(generated)
      bool
    })
  }
}
