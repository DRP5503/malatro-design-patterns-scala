package cl.uchile.dcc
import cl.uchile.dcc.model.*
import cl.uchile.dcc.model.cards.{As, Two, Jack, King, Queen, Three}
import munit.FunSuite

class RangoTest extends FunSuite {

  test("Two As deben ser iguales") {
    val r1 = new As()
    val r2 = new As()

    assert(r1.isEqual(r2))
  }

  test("As y Two no deben ser iguales") {
    val r1 = new As()
    val r2 = new Two()

    assert(!r1.isEqual(r2))
  }

  test("Two tiene order, value y classification correctos") {
    val Two = new Two()

    assert(Two.order == 2)
    assert(Two.value == 2)
    assert(Two.classification.name == "Even")
  }

  test("Three es Odd") {
    val Three = new Three()

    assert(Three.classification.name == "Odd")
  }

  test("Jack es Face y vale 10") {
    val Jack = new Jack()

    assert(Jack.order == 11)
    assert(Jack.value == 10)
    assert(Jack.classification.name == "Face")
  }

  test("Queen es Face y vale 10") {
    val Queen = new Queen()

    assert(Queen.order == 12)
    assert(Queen.value == 10)
    assert(Queen.classification.name == "Face")
  }

  test("King es Face y vale 10") {
    val King = new King()

    assert(King.order == 13)
    assert(King.value == 10)
    assert(King.classification.name == "Face")
  }

  test("Queen y King no son iguales") {
    val Queen = new Queen()
    val King = new King()

    assert(!Queen.isEqual(King))
  }
}