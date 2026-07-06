package cl.uchile.dcc

import java.io.ByteArrayOutputStream
import logic.*
import model.Hand
import model.cards.*

import cl.uchile.dcc.logic.states.{FinalState, InitializingState, PlayerInTurnState}
import munit.FunSuite

class GameControllerTest extends FunSuite {
  private def cartaDosCorazon: Card =
    new Card(new Two, new Heart)

  private def cartaDosDiamante: Card =
    new Card(new Two, new Diamond)

  private def cartaTresCorazon: Card =
    new Card(new Three, new Heart)

  private def cartaTresDiamante: Card =
    new Card(new Three, new Diamond)

  private def cartaCuatroCorazon: Card =
    new Card(new Four, new Heart)

  private def cartaCuatroTrebol: Card =
    new Card(new Four, new Club)

  private def cartaCincoCorazon: Card =
    new Card(new Five, new Heart)

  private def cartaCincoPica: Card =
    new Card(new Five, new Spade)

  private def cartaSeisCorazon: Card =
    new Card(new Six, new Heart)

  private def cartaSieteCorazon: Card =
    new Card(new Seven, new Heart)

  private def cartaNueveCorazon: Card =
    new Card(new Nine, new Heart)

  private def cartaKaiserCorazon: Card =
    new Card(new King, new Heart)

  test("GameController parte en InitializingState") {
    val controller = new GameController()

    assert(controller.actualState.isInstanceOf[InitializingState])
  }

  test("GameController usa Score minimo por defecto") {
    val controller = new GameController()

    assertEquals(controller.minimumScore, 300)
  }

  test("GameController permite definir Score minimo custom") {
    val controller = new GameController(minimumScore = 50)

    assertEquals(controller.minimumScore, 50)
  }

  test("startGame cambia el estado a PlayerInTurnState") {
    val controller = new GameController()

    controller.startGame()

    assert(controller.actualState.isInstanceOf[PlayerInTurnState])
  }

  test("startGame reinicia el score en cero") {
    val controller = new GameController()
    controller.score = 100

    controller.startGame()

    assertEquals(controller.score, 0)
  }

  test("startGame crea una Hand nueva con jugadas y descartes disponibles") {
    val controller = new GameController()

    controller.startGame()

    assertEquals(controller.Hand.playsLeft, 3)
    assertEquals(controller.Hand.discardsLeft, 3)
  }

  test("resetGame deja al controller en PlayerInTurnState con score reiniciado") {
    val controller = new GameController()
    controller.startGame()
    controller.score = 200

    controller.resetGame()

    assertEquals(controller.score, 0)
    assert(controller.actualState.isInstanceOf[PlayerInTurnState])
    assertEquals(controller.Hand.playsLeft, 3)
    assertEquals(controller.Hand.discardsLeft, 3)
  }

  test("resetGame reemplaza la Hand actual por una Hand nueva") {
    val controller = new GameController()
    controller.startGame()
    val oldHand = controller.Hand
    controller.Hand = new Hand(List(cartaDosCorazon), List(), 1, 1)

    controller.resetGame()

    assertNotEquals(controller.Hand, oldHand)
    assertEquals(controller.Hand.cards, List())
    assertEquals(controller.Hand.playsLeft, 3)
    assertEquals(controller.Hand.discardsLeft, 3)
  }

  test("changeState actualiza el estado actual") {
    val controller = new GameController()
    val nextState = new PlayerInTurnState(controller)

    controller.changeState(nextState)

    assertEquals(controller.actualState, nextState)
  }

  test("playCards delega en el estado actual") {
    val controller = new GameController(minimumScore = 1000)
    controller.startGame()
    controller.Hand = new Hand(
      List(cartaDosCorazon, cartaDosDiamante),
      List()
    )

    controller.playCards(List(0, 1))

    assertEquals(controller.score, 28)
    assertEquals(controller.Hand.playsLeft, 2)
    assertEquals(controller.Hand.cards, List())
  }

  test("playCards acumula Score en jugadas consecutivas") {
    val controller = new GameController(minimumScore = 1000)
    controller.startGame()
    controller.Hand = new Hand(
      List(cartaDosCorazon, cartaDosDiamante, cartaTresCorazon, cartaTresDiamante),
      List(),
      2,
      3
    )

    controller.playCards(List(0, 1))
    Console.withOut(new ByteArrayOutputStream()) {
      controller.playCards(List(0, 1))
    }

    assertEquals(controller.score, 60)
    assertEquals(controller.Hand.playsLeft, 0)
    assert(controller.actualState.isInstanceOf[FinalState])
  }

  test("playCards puede resolver una Straight de Flush desde el controller") {
    val controller = new GameController(minimumScore = 1000)
    controller.startGame()
    controller.Hand = new Hand(
      List(
        cartaDosCorazon,
        cartaTresCorazon,
        cartaCuatroCorazon,
        cartaCincoCorazon,
        cartaSeisCorazon
      ),
      List()
    )

    controller.playCards(List(0, 1, 2, 3, 4))

    assertEquals(controller.score, 960)
    assertEquals(controller.Hand.playsLeft, 2)
  }

  test("playCards puede resolver un Flush desde el controller") {
    val controller = new GameController(minimumScore = 1000)
    controller.startGame()
    controller.Hand = new Hand(
      List(
        cartaDosCorazon,
        cartaCincoCorazon,
        cartaSieteCorazon,
        cartaNueveCorazon,
        cartaKaiserCorazon
      ),
      List()
    )

    controller.playCards(List(0, 1, 2, 3, 4))

    assertEquals(controller.score, 272)
    assertEquals(controller.Hand.playsLeft, 2)
  }

  test("playCards puede resolver una Straight desde el controller") {
    val controller = new GameController(minimumScore = 1000)
    controller.startGame()
    controller.Hand = new Hand(
      List(
        cartaDosCorazon,
        cartaTresDiamante,
        cartaCuatroTrebol,
        cartaCincoPica,
        cartaSeisCorazon
      ),
      List()
    )

    controller.playCards(List(0, 1, 2, 3, 4))

    assertEquals(controller.score, 200)
    assertEquals(controller.Hand.playsLeft, 2)
  }

  test("discardCards delega en el estado actual") {
    val controller = new GameController()
    controller.startGame()
    controller.Hand = new Hand(
      List(cartaDosCorazon, cartaDosDiamante),
      List()
    )

    controller.discardCards(List(1))

    assertEquals(controller.Hand.discardsLeft, 2)
    assertEquals(controller.Hand.cards.length, 1)
  }

  test("playCards antes de iniciar la partida propaga error del estado actual") {
    val controller = new GameController()

    intercept[InvalidStateActionException] {
      controller.playCards(List(0))
    }
  }

  test("discardCards antes de iniciar la partida propaga error del estado actual") {
    val controller = new GameController()

    intercept[InvalidStateActionException] {
      controller.discardCards(List(0))
    }
  }

  test("startGame propaga error si el estado actual no permite reiniciar") {
    val controller = new GameController()
    controller.startGame()

    intercept[InvalidStateActionException] {
      controller.startGame()
    }
  }

  test("resetGame permite volver a jugar despues de llegar a FinalState") {
    val controller = new GameController(minimumScore = 1000)
    controller.startGame()
    controller.Hand = new Hand(
      List(cartaDosCorazon),
      List(),
      1,
      3
    )

    Console.withOut(new ByteArrayOutputStream()) {
      controller.playCards(List(0))
    }
    controller.resetGame()

    assert(controller.actualState.isInstanceOf[PlayerInTurnState])
    assertEquals(controller.score, 0)
    assertEquals(controller.Hand.playsLeft, 3)
    assertEquals(controller.Hand.discardsLeft, 3)
  }

  test("flujo completo termina en victoria usando la API publica del controller") {
    val controller = new GameController(minimumScore = 10)
    controller.startGame()
    controller.Hand = new Hand(
      List(cartaDosCorazon, cartaDosDiamante),
      List(),
      1,
      3
    )
    val output = new ByteArrayOutputStream()

    Console.withOut(output) {
      controller.playCards(List(0, 1))
    }

    assert(controller.actualState.isInstanceOf[FinalState])
    assertEquals(controller.score, 28)
    assert(output.toString.contains("Winner winner chicken dinner"))
  }

  test("flujo completo termina en derrota usando la API publica del controller") {
    val controller = new GameController(minimumScore = 1000)
    controller.startGame()
    controller.Hand = new Hand(
      List(cartaDosCorazon, cartaDosDiamante),
      List(),
      1,
      3
    )
    val output = new ByteArrayOutputStream()

    Console.withOut(output) {
      controller.playCards(List(0, 1))
    }

    assert(controller.actualState.isInstanceOf[FinalState])
    assertEquals(controller.score, 28)
    assert(output.toString.contains("Game Over, no se llego al Score"))
  }
}
