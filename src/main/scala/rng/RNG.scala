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

  def unit[A](value: A): Rand[A] = rng => (value, rng)

  def map[A,B](rand: Rand[A])(f: A => B): Rand[B] = {
    rng => {
      val (value, nextRNG) = rand(rng)
      (f(value), nextRNG)
    }
  }

  def map2[A,B,C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] =
    rng => {
      val (a, aRNG) = ra(rng)
      val (b, bRNG) = rb(aRNG)
      (f(a, b), bRNG)
    }

  def both[A,B](ra: Rand[A], rb: Rand[B]): Rand[(A, B)] =
    map2(ra, rb)((_, _))

  def sequence[A](fs: List[Rand[A]]): Rand[List[A]] = fs match {
    case List() => rng => (List(), rng)
    case head :: tail => map2(head, sequence(tail))((a, b) => b :+ a)
  }

  def nonNegativeEvenInt(rng: RNG): (Int, RNG) =
    map(nonNegativeInt)(value => value - value % 2)(rng)

  def nonNegativeInt(rng: RNG): (Int, RNG) = {
    val (number, nextRNG) = rng.nextInt
    val nonMinValueInt = if (number == Int.MinValue) number + 1 else number
    (nonMinValueInt.abs, nextRNG)
  }

  def int: Rand[Int] = _.nextInt

  def double(rng: RNG): (Double, RNG) =
    map(_.nextInt)(number => {
      val nonMaxValueInt = if (number == Int.MaxValue) number - 1 else number
      nonMaxValueInt.toDouble.abs / Int.MaxValue
    })(rng)

  def intDouble(rng: RNG): ((Int, Double), RNG) =
    both(_.nextInt, RNG.double)(rng)

  def doubleInt(rng: RNG): ((Double, Int), RNG) =
    map(intDouble)(_.swap)(rng)

  def double3(rng: RNG): ((Double, Double, Double), RNG) = {
    val (firstNumber, firstRNG) = RNG.double(rng)
    val (secondNumber, secondRNG) = RNG.double(firstRNG)
    val (thirdNumber, thirdRNG) = RNG.double(secondRNG)
    ((firstNumber, secondNumber, thirdNumber), thirdRNG)
  }

  def ints(count: Int)(rng: RNG): (List[Int], RNG) =
    sequence(List.fill(count)(int))(rng)
}
