package cl.uchile.dcc
import cl.uchile.dcc.model.*
import cl.uchile.dcc.model.cards.{As, Card, Heart, King, Spade}
import munit.FunSuite
class CartaTest extends FunSuite {
  test("Two cards iguales deben ser iguales") {
    val carta1 = new Card(new As, new Heart)
    val carta2 = new Card(new As, new Heart)

    assert(carta1.isEqual(carta2))
  }
  test("Two cards distintas no deben ser iguales") {
    val carta1 = new Card(new As, new Heart)
    val carta2 = new Card(new King, new Spade)

    assert(!carta1.isEqual(carta2))
  }
  test("cards con mismo Rank pero distinta Suit no son iguales") {
    val carta1 = new Card(new As, new Heart)
    val carta2 = new Card(new As, new Spade)

    assert(!carta1.isEqual(carta2))
  }
}
