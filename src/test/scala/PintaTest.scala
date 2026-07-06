package cl.uchile.dcc
import cl.uchile.dcc.model.*
import cl.uchile.dcc.model.cards.{Heart, Diamond, Spade, Club}
import munit.FunSuite

class PintaTest extends FunSuite {

  test("Heart tiene el name correcto") {
    val Heart = new Heart()

    assert(Heart.suitType == "Heart")
  }

  test("Spade tiene el name correcto") {
    val Spade = new Spade()

    assert(Spade.suitType == "Spade")
  }

  test("Diamond tiene el name correcto") {
    val Diamond = new Diamond()

    assert(Diamond.suitType == "Diamond")
  }

  test("Club tiene el name correcto") {
    val Club = new Club()

    assert(Club.suitType == "Club")
  }
  test("suits distintas tienen nombres distintos") {
    val Heart = new Heart()
    val Spade = new Spade()

    assert(Heart.suitType != Spade.suitType)
  }
}
