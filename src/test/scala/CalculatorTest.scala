package cl.uchile.dcc
import modelo.*
import modelo.cartas.*
import logica.*
import munit.FunSuite

class CalculatorTest extends FunSuite {
  test("Calculo de puntaje escalera de color con Greedy y Devious") {
    val cartas = List(
      new Carta(new Dos, new Diamante),
      new Carta(new Tres, new Diamante),
      new Carta(new Cuatro, new Diamante),
      new Carta(new Cinco, new Diamante),
      new Carta(new Seis, new Diamante)
    )
    val jokers = List(new Greedy, new Devious)
    assertEquals(Calculator.calcularPuntaje(cartas, jokers), 5060)
  }

  test("Calculo de puntaje sin jokers") {
    val cartas = List(
      new Carta(new Dos, new Corazon),
      new Carta(new Dos, new Diamante)
    )
    val jokers = List()
    // Par base: 10 chips + 2 + 2 = 14, mult 2 → 28
    assertEquals(Calculator.calcularPuntaje(cartas, jokers), 28)
  }

    test("Calculo de puntaje con EvenSteven") {
      val cartas = List(
        new Carta(new Dos, new Corazon),
        new Carta(new Dos, new Diamante)
      )
      val jokers = List(new EvenSteven)
      // Par base: 10 chips + 2 + 2 = 14, mult 2 + 4 + 4 = 10 → 140
      assertEquals(Calculator.calcularPuntaje(cartas, jokers), 140)
    }

  test("Calculo de puntaje con ScaryFace") {
    val cartas = List(
      new Carta(new Jota, new Corazon),
      new Carta(new Jota, new Diamante)
    )
    val jokers = List(new ScaryFace)
    // Par base: 10 chips + 10 + 10 = 30, mult 2
    // ScaryFace: +30 por cada figura → +60 chips
    // Total: 90 * 2 = 180
    assertEquals(Calculator.calcularPuntaje(cartas, jokers), 180)
  }
}



