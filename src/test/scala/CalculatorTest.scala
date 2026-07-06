package cl.uchile.dcc
import model.*
import model.cards.*
import logic.*
import munit.FunSuite

class CalculatorTest extends FunSuite {
  test("Calculo de Score Straight de Flush con Greedy y Devious") {
    val cards = List(
      new Card(new Two, new Diamond),
      new Card(new Three, new Diamond),
      new Card(new Four, new Diamond),
      new Card(new Five, new Diamond),
      new Card(new Six, new Diamond)
    )
    val jokers = List(new Greedy, new Devious)
    assertEquals(Calculator.calculateScore(cards, jokers), 5060)
  }

  test("Calculo de Score sin jokers") {
    val cards = List(
      new Card(new Two, new Heart),
      new Card(new Two, new Diamond)
    )
    val jokers = List()
    // Even base: 10 chips + 2 + 2 = 14, mult 2 → 28
    assertEquals(Calculator.calculateScore(cards, jokers), 28)
  }

    test("Calculo de Score con EvenSteven") {
      val cards = List(
        new Card(new Two, new Heart),
        new Card(new Two, new Diamond)
      )
      val jokers = List(new EvenSteven)
      // Even base: 10 chips + 2 + 2 = 14, mult 2 + 4 + 4 = 10 → 140
      assertEquals(Calculator.calculateScore(cards, jokers), 140)
    }

  test("Calculo de Score con ScaryFace") {
    val cards = List(
      new Card(new Jack, new Heart),
      new Card(new Jack, new Diamond)
    )
    val jokers = List(new ScaryFace)
    // Even base: 10 chips + 10 + 10 = 30, mult 2
    // ScaryFace: +30 por cada Face → +60 chips
    // Total: 90 * 2 = 180
    assertEquals(Calculator.calculateScore(cards, jokers), 180)
  }
}



