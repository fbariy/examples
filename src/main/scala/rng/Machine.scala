package rng

sealed trait Input
case object Coin extends Input
case object Turn extends Input

case class Machine(locked: Boolean, candies: Int, coins: Int)

object Machine {
  def simulateMachine(inputs: List[Input]): State[Machine, (Int, Int)] =
    for {
      _ <- State.sequence(inputs.map(State.modify[Machine] _ compose action))
      s <- State.get
    } yield (s.candies, s.coins)

  def action(input: Input)(machine: Machine): Machine = (input, machine) match {
    case (_, Machine(_, 0, _)) => machine
    case (Turn, Machine(true, _, _)) => machine
    case (Coin, Machine(false, _, _)) => machine
    case (Coin, Machine(true, candies, coins)) =>
      Machine(false, candies, coins + 1)
    case (Turn, Machine(false, candies, coins)) =>
      Machine(true, candies - 1, coins)
  }
}