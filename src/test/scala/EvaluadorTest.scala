package cl.uchile.dcc

import cl.uchile.dcc.model.cards.*
import cl.uchile.dcc.logic.Evaluator
import cl.uchile.dcc.model.*
import munit.FunSuite

class EvaluadorTest extends FunSuite {
  // Tests de jugadas básicas
  test("Straight de Flush es identificada correctamente") {
    val cards = List(
      new Card(new Two, new Diamond),
      new Card(new Three, new Diamond),
      new Card(new Four, new Diamond),
      new Card(new Five, new Diamond),
      new Card(new Six, new Diamond)
    )
    assertEquals(Evaluator.identifyHand(cards), StraightFlush())
  }

  test("Flush es identificado correctamente") {
    val cards = List(
      new Card(new Two, new Diamond),
      new Card(new Five, new Diamond),
      new Card(new Seven, new Diamond),
      new Card(new Nine, new Diamond),
      new Card(new King, new Diamond)
    )
    assertEquals(Evaluator.identifyHand(cards), Flush())
  }

  test("Straight es identificada correctamente") {
    val cards = List(
      new Card(new Two, new Heart),
      new Card(new Three, new Diamond),
      new Card(new Four, new Club),
      new Card(new Five, new Spade),
      new Card(new Six, new Heart)
    )
    assertEquals(Evaluator.identifyHand(cards), Straight())
  }

  test("ThreeOfAKind es identificado correctamente") {
    val cards = List(
      new Card(new Two, new Heart),
      new Card(new Two, new Diamond),
      new Card(new Two, new Club)
    )
    assertEquals(Evaluator.identifyHand(cards), ThreeOfAKind())
  }

  test("Even es identificado correctamente") {
    val cards = List(
      new Card(new Two, new Heart),
      new Card(new Two, new Diamond)
    )
    assertEquals(Evaluator.identifyHand(cards), PairHand())
  }

  test("Card Alta es identificada correctamente") {
    val cards = List(
      new Card(new Two, new Heart),
      new Card(new Five, new Diamond),
      new Card(new King, new Club)
    )
    assertEquals(Evaluator.identifyHand(cards), HighCard())
  }

  // Tests de prioridad
  test("Straight de Flush tiene prioridad sobre Flush") {
    val cards = List(
      new Card(new Two, new Diamond),
      new Card(new Three, new Diamond),
      new Card(new Four, new Diamond),
      new Card(new Five, new Diamond),
      new Card(new Six, new Diamond)
    )
    assertEquals(Evaluator.identifyHand(cards), StraightFlush())
  }

  test("Straight de Flush tiene prioridad sobre Straight") {
    val cards = List(
      new Card(new Two, new Diamond),
      new Card(new Three, new Diamond),
      new Card(new Four, new Diamond),
      new Card(new Five, new Diamond),
      new Card(new Six, new Diamond)
    )
    assertEquals(Evaluator.identifyHand(cards), StraightFlush())
  }

  // Tests de As en escaleras
  test("As actua como Card baja en Straight A,2,3,4,5") {
    val cards = List(
      new Card(new As, new Heart),
      new Card(new Two, new Diamond),
      new Card(new Three, new Club),
      new Card(new Four, new Spade),
      new Card(new Five, new Heart)
    )
    assertEquals(Evaluator.identifyHand(cards), Straight())
  }

  test("As actua como Card alta en Straight 10,J,Q,K,A") {
    val cards = List(
      new Card(new Ten, new Heart),
      new Card(new Jack, new Diamond),
      new Card(new Queen, new Club),
      new Card(new King, new Spade),
      new Card(new As, new Heart)
    )
    assertEquals(Evaluator.identifyHand(cards), Straight())
  }

  test("As actua como Card alta en Straight de Flush 10,J,Q,K,A") {
    val cards = List(
      new Card(new Ten, new Heart),
      new Card(new Jack, new Heart),
      new Card(new Queen, new Heart),
      new Card(new King, new Heart),
      new Card(new As, new Heart)
    )
    assertEquals(Evaluator.identifyHand(cards), StraightFlush())
  }
}