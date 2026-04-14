package cl.uchile.dcc
import cl.uchile.dcc.modelo.*
import munit.FunSuite
class CartaTest extends FunSuite {
  test("dos cartas iguales deben ser iguales") {
    val carta1 = new Carta(new As, new Corazon)
    val carta2 = new Carta(new As, new Corazon)

    assert(carta1.esIgual(carta2))
  }
  test("dos cartas distintas no deben ser iguales") {
    val carta1 = new Carta(new As, new Corazon)
    val carta2 = new Carta(new Kaiser, new Pica)

    assert(!carta1.esIgual(carta2))
  }
  test("cartas con mismo rango pero distinta pinta no son iguales") {
    val carta1 = new Carta(new As, new Corazon)
    val carta2 = new Carta(new As, new Pica)

    assert(!carta1.esIgual(carta2))
  }
}
