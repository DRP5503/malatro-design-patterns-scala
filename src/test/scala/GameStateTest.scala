package cl.uchile.dcc

import java.io.ByteArrayOutputStream
import logic.*
import model.*
import model.cards.*

import cl.uchile.dcc.logic.states.{FinalState, PlayerInTurnState}
import munit.FunSuite

class GameStateTest extends FunSuite {
  private def cartaDosCorazon: Card =
    new Card(new Two, new Heart)

  private def cartaDosDiamante: Card =
    new Card(new Two, new Diamond)

  private def cartaTresTrebol: Card =
    new Card(new Three, new Club)

  test("InitializingState.startGame reinicia la partida y pasa a PlayerInTurnState") {
    val controller = new GameController()
    controller.score = 500

    controller.actualState.startGame()

    assertEquals(controller.score, 0)
    assertEquals(controller.Hand.cards, List())
    assertEquals(controller.Hand.jokers, List())
    assertEquals(controller.Hand.playsLeft, 3)
    assertEquals(controller.Hand.discardsLeft, 3)
    assert(controller.actualState.isInstanceOf[PlayerInTurnState])
  }

  test("PlayerInTurnState.playCards juega cards, suma Score y descuenta una PokerHand") {
    val controller = new GameController(minimumScore = 1000)
    controller.startGame()
    controller.Hand = new Hand(
      List(cartaDosCorazon, cartaDosDiamante),
      List()
    )

    controller.playCards(List(0, 1))

    assertEquals(controller.score, 28)
    assertEquals(controller.Hand.playsLeft, 2)
    assertEquals(controller.Hand.cards.length, 0)
    assert(controller.actualState.isInstanceOf[PlayerInTurnState])
  }

  test("PlayerInTurnState.discardCards descarta cards y descuenta un descarte") {
    val controller = new GameController()
    controller.startGame()
    controller.Hand = new Hand(
      List(cartaDosCorazon, cartaTresTrebol),
      List()
    )

    controller.discardCards(List(0))

    assertEquals(controller.Hand.discardsLeft, 2)
    assertEquals(controller.Hand.cards.length, 1)
  }

  test("cuando se acaban las jugadas, el estado cambia a FinalState") {
    val controller = new GameController(minimumScore = 1000)
    controller.startGame()
    controller.Hand = new Hand(
      List(cartaDosCorazon),
      List(),
      1,
      3
    )

    controller.playCards(List(0))

    assert(controller.actualState.isInstanceOf[FinalState])
    assertEquals(controller.Hand.playsLeft, 0)
  }

  test("FinalState no permite jugar cards") {
    val controller = new GameController()
    controller.changeState(new FinalState(controller))

    intercept[InvalidStateActionException] {
      controller.playCards(List(0))
    }
  }

  test("FinalState no permite descartar cards") {
    val controller = new GameController()
    controller.changeState(new FinalState(controller))

    intercept[InvalidStateActionException] {
      controller.discardCards(List(0))
    }
  }

  test("PlayerInTurnState no permite iniciar la partida de nuevo desde startGame") {
    val controller = new GameController()
    controller.startGame()

    intercept[InvalidStateActionException] {
      controller.startGame()
    }
  }

  test("FinalState no permite iniciar la partida desde startGame") {
    val controller = new GameController()
    controller.changeState(new FinalState(controller))

    intercept[InvalidStateActionException] {
      controller.startGame()
    }
  }

  test("GameState.changeState por defecto lanza exception") {
    val controller = new GameController()

    intercept[InvalidStateActionException] {
      controller.actualState.changeState(new PlayerInTurnState(controller))
    }
  }

  test("no se puede jugar desde InitializingState antes de startGame") {
    val controller = new GameController()

    intercept[InvalidStateActionException] {
      controller.playCards(List(0))
    }
  }

  test("no se puede descartar desde InitializingState antes de startGame") {
    val controller = new GameController()

    intercept[InvalidStateActionException] {
      controller.discardCards(List(0))
    }
  }

  test("no se puede jugar si la Hand no tiene jugadas disponibles") {
    val controller = new GameController()
    controller.startGame()
    controller.Hand = new Hand(
      List(cartaDosCorazon),
      List(),
      0,
      3
    )

    intercept[NoPlaysLeftException] {
      controller.playCards(List(0))
    }
  }

  test("no se puede descartar si la Hand no tiene descartes disponibles") {
    val controller = new GameController()
    controller.startGame()
    controller.Hand = new Hand(
      List(cartaDosCorazon),
      List(),
      3,
      0
    )

    intercept[NoDiscardsLeftException] {
      controller.discardCards(List(0))
    }
  }

  test("FinalState imprime message de victoria si se alcanza el Score minimo") {
    val controller = new GameController(minimumScore = 10)
    controller.startGame()
    controller.Hand = new Hand(
      List(cartaDosCorazon),
      List(),
      1,
      3
    )
    val output = new ByteArrayOutputStream()

    Console.withOut(output) {
      controller.playCards(List(0))
    }

    assert(controller.actualState.isInstanceOf[FinalState])
    assert(output.toString.contains("Winner winner chicken dinner"))
  }

  test("FinalState imprime message de derrota si no se alcanza el Score minimo") {
    val controller = new GameController(minimumScore = 1000)
    controller.startGame()
    controller.Hand = new Hand(
      List(cartaDosCorazon),
      List(),
      1,
      3
    )
    val output = new ByteArrayOutputStream()

    Console.withOut(output) {
      controller.playCards(List(0))
    }

    assert(controller.actualState.isInstanceOf[FinalState])
    assert(output.toString.contains("Game Over, no se llego al Score"))
  }
}
