package cl.uchile.dcc

import java.io.ByteArrayOutputStream

import logica.*
import modelo.*
import modelo.cartas.*
import munit.FunSuite

class GameStateTest extends FunSuite {
  private def cartaDosCorazon: Carta =
    new Carta(new Dos, new Corazon)

  private def cartaDosDiamante: Carta =
    new Carta(new Dos, new Diamante)

  private def cartaTresTrebol: Carta =
    new Carta(new Tres, new Trebol)

  test("InitializingState.startGame reinicia la partida y pasa a PlayerInTurnState") {
    val controller = new GameController()
    controller.score = 500

    controller.actualState.startGame()

    assertEquals(controller.score, 0)
    assertEquals(controller.mano.cartas, List())
    assertEquals(controller.mano.jokers, List())
    assertEquals(controller.mano.playsLeft, 3)
    assertEquals(controller.mano.discardsLeft, 3)
    assert(controller.actualState.isInstanceOf[PlayerInTurnState])
  }

  test("PlayerInTurnState.playCards juega cartas, suma puntaje y descuenta una jugada") {
    val controller = new GameController(minimumScore = 1000)
    controller.startGame()
    controller.mano = new Mano(
      List(cartaDosCorazon, cartaDosDiamante),
      List()
    )

    controller.playCards(List(0, 1))

    assertEquals(controller.score, 28)
    assertEquals(controller.mano.playsLeft, 2)
    assertEquals(controller.mano.cartas.length, 0)
    assert(controller.actualState.isInstanceOf[PlayerInTurnState])
  }

  test("PlayerInTurnState.discardCards descarta cartas y descuenta un descarte") {
    val controller = new GameController()
    controller.startGame()
    controller.mano = new Mano(
      List(cartaDosCorazon, cartaTresTrebol),
      List()
    )

    controller.discardCards(List(0))

    assertEquals(controller.mano.discardsLeft, 2)
    assertEquals(controller.mano.cartas.length, 1)
  }

  test("cuando se acaban las jugadas, el estado cambia a FinalState") {
    val controller = new GameController(minimumScore = 1000)
    controller.startGame()
    controller.mano = new Mano(
      List(cartaDosCorazon),
      List(),
      1,
      3
    )

    controller.playCards(List(0))

    assert(controller.actualState.isInstanceOf[FinalState])
    assertEquals(controller.mano.playsLeft, 0)
  }

  test("FinalState no permite jugar cartas") {
    val controller = new GameController()
    controller.changeState(new FinalState(controller))

    intercept[AccionInvalidaEstadoException] {
      controller.playCards(List(0))
    }
  }

  test("FinalState no permite descartar cartas") {
    val controller = new GameController()
    controller.changeState(new FinalState(controller))

    intercept[AccionInvalidaEstadoException] {
      controller.discardCards(List(0))
    }
  }

  test("PlayerInTurnState no permite iniciar la partida de nuevo desde startGame") {
    val controller = new GameController()
    controller.startGame()

    intercept[AccionInvalidaEstadoException] {
      controller.startGame()
    }
  }

  test("FinalState no permite iniciar la partida desde startGame") {
    val controller = new GameController()
    controller.changeState(new FinalState(controller))

    intercept[AccionInvalidaEstadoException] {
      controller.startGame()
    }
  }

  test("GameState.changeState por defecto lanza exception") {
    val controller = new GameController()

    intercept[AccionInvalidaEstadoException] {
      controller.actualState.changeState(new PlayerInTurnState(controller))
    }
  }

  test("no se puede jugar desde InitializingState antes de startGame") {
    val controller = new GameController()

    intercept[AccionInvalidaEstadoException] {
      controller.playCards(List(0))
    }
  }

  test("no se puede descartar desde InitializingState antes de startGame") {
    val controller = new GameController()

    intercept[AccionInvalidaEstadoException] {
      controller.discardCards(List(0))
    }
  }

  test("no se puede jugar si la mano no tiene jugadas disponibles") {
    val controller = new GameController()
    controller.startGame()
    controller.mano = new Mano(
      List(cartaDosCorazon),
      List(),
      0,
      3
    )

    intercept[NoQuedanJugadasException] {
      controller.playCards(List(0))
    }
  }

  test("no se puede descartar si la mano no tiene descartes disponibles") {
    val controller = new GameController()
    controller.startGame()
    controller.mano = new Mano(
      List(cartaDosCorazon),
      List(),
      3,
      0
    )

    intercept[NoQuedanDescartesException] {
      controller.discardCards(List(0))
    }
  }

  test("FinalState imprime mensaje de victoria si se alcanza el puntaje minimo") {
    val controller = new GameController(minimumScore = 10)
    controller.startGame()
    controller.mano = new Mano(
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

  test("FinalState imprime mensaje de derrota si no se alcanza el puntaje minimo") {
    val controller = new GameController(minimumScore = 1000)
    controller.startGame()
    controller.mano = new Mano(
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
    assert(output.toString.contains("Game Over, no se llego al puntaje"))
  }
}
