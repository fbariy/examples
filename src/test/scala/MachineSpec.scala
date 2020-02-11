import org.scalatest.{Assertion, FlatSpec}
import rng._

class MachineSpec extends FlatSpec {
  private val case1 = (Machine(true, 4, 2), List(), (4, 2))
  private val case2 = (Machine(true, 4, 2), List(Turn, Coin, Turn, Turn, Coin, Turn), (2, 4))
  private val case3 = (Machine(false, 0, 0), List(Turn, Coin, Turn), (0, 0))
  private val case4 = (Machine(false, 1, 0), List(Turn, Turn, Coin), (0, 0))

  "simulateMachine" should "not modify machine without inputs" in
    assertMachine(case1)

  it should "modify to 2 candies, 4 coins" in
    assertMachine(case2)

  it should "modify to 0 candies, 0 coins" in
    assertMachine(case3)

  it should "modify to 0 candies, 1 coins" in
    assertMachine(case4)

  def assertMachine(caseN: (Machine, List[Input], (Int, Int))): Assertion = {
    val (machine, inputs, expected) = caseN
    val (_, actual) = Machine.simulateMachine(inputs).run(machine)
    assert(actual == expected)
  }
}
