package cl.uchile.dcc

import java.io.ByteArrayOutputStream

import logica.*
import modelo.Mano
import modelo.cartas.*
import munit.FunSuite

class GameControllerTest extends FunSuite {
  private def cartaDosCorazon: Carta =
    new Carta(new Dos, new Corazon)

  private def cartaDosDiamante: Carta =
    new Carta(new Dos, new Diamante)

  private def cartaTresCorazon: Carta =
    new Carta(new Tres, new Corazon)

  private def cartaTresDiamante: Carta =
    new Carta(new Tres, new Diamante)

  private def cartaCuatroCorazon: Carta =
    new Carta(new Cuatro, new Corazon)

  private def cartaCuatroTrebol: Carta =
    new Carta(new Cuatro, new Trebol)

  private def cartaCincoCorazon: Carta =
    new Carta(new Cinco, new Corazon)

  private def cartaCincoPica: Carta =
    new Carta(new Cinco, new Pica)

  private def cartaSeisCorazon: Carta =
    new Carta(new Seis, new Corazon)

  private def cartaSieteCorazon: Carta =
    new Carta(new Siete, new Corazon)

  private def cartaNueveCorazon: Carta =
    new Carta(new Nueve, new Corazon)

  private def cartaKaiserCorazon: Carta =
    new Carta(new Kaiser, new Corazon)

  test("GameController parte en InitializingState") {
    val controller = new GameController()

    assert(controller.actualState.isInstanceOf[InitializingState])
  }

  test("GameController usa puntaje minimo por defecto") {
    val controller = new GameController()

    assertEquals(controller.minimumScore, 300)
  }

  test("GameController permite definir puntaje minimo custom") {
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

  test("startGame crea una mano nueva con jugadas y descartes disponibles") {
    val controller = new GameController()

    controller.startGame()

    assertEquals(controller.mano.playsLeft, 3)
    assertEquals(controller.mano.discardsLeft, 3)
  }

  test("resetGame deja al controller en PlayerInTurnState con score reiniciado") {
    val controller = new GameController()
    controller.startGame()
    controller.score = 200

    controller.resetGame()

    assertEquals(controller.score, 0)
    assert(controller.actualState.isInstanceOf[PlayerInTurnState])
    assertEquals(controller.mano.playsLeft, 3)
    assertEquals(controller.mano.discardsLeft, 3)
  }

  test("resetGame reemplaza la mano actual por una mano nueva") {
    val controller = new GameController()
    controller.startGame()
    val oldHand = controller.mano
    controller.mano = new Mano(List(cartaDosCorazon), List(), 1, 1)

    controller.resetGame()

    assertNotEquals(controller.mano, oldHand)
    assertEquals(controller.mano.cartas, List())
    assertEquals(controller.mano.playsLeft, 3)
    assertEquals(controller.mano.discardsLeft, 3)
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
    controller.mano = new Mano(
      List(cartaDosCorazon, cartaDosDiamante),
      List()
    )

    controller.playCards(List(0, 1))

    assertEquals(controller.score, 28)
    assertEquals(controller.mano.playsLeft, 2)
    assertEquals(controller.mano.cartas, List())
  }

  test("playCards acumula puntaje en jugadas consecutivas") {
    val controller = new GameController(minimumScore = 1000)
    controller.startGame()
    controller.mano = new Mano(
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
    assertEquals(controller.mano.playsLeft, 0)
    assert(controller.actualState.isInstanceOf[FinalState])
  }

  test("playCards puede resolver una escalera de color desde el controller") {
    val controller = new GameController(minimumScore = 1000)
    controller.startGame()
    controller.mano = new Mano(
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
    assertEquals(controller.mano.playsLeft, 2)
  }

  test("playCards puede resolver un color desde el controller") {
    val controller = new GameController(minimumScore = 1000)
    controller.startGame()
    controller.mano = new Mano(
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
    assertEquals(controller.mano.playsLeft, 2)
  }

  test("playCards puede resolver una escalera desde el controller") {
    val controller = new GameController(minimumScore = 1000)
    controller.startGame()
    controller.mano = new Mano(
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
    assertEquals(controller.mano.playsLeft, 2)
  }

  test("discardCards delega en el estado actual") {
    val controller = new GameController()
    controller.startGame()
    controller.mano = new Mano(
      List(cartaDosCorazon, cartaDosDiamante),
      List()
    )

    controller.discardCards(List(1))

    assertEquals(controller.mano.discardsLeft, 2)
    assertEquals(controller.mano.cartas.length, 1)
  }

  test("playCards antes de iniciar la partida propaga error del estado actual") {
    val controller = new GameController()

    intercept[AccionInvalidaEstadoException] {
      controller.playCards(List(0))
    }
  }

  test("discardCards antes de iniciar la partida propaga error del estado actual") {
    val controller = new GameController()

    intercept[AccionInvalidaEstadoException] {
      controller.discardCards(List(0))
    }
  }

  test("startGame propaga error si el estado actual no permite reiniciar") {
    val controller = new GameController()
    controller.startGame()

    intercept[AccionInvalidaEstadoException] {
      controller.startGame()
    }
  }

  test("resetGame permite volver a jugar despues de llegar a FinalState") {
    val controller = new GameController(minimumScore = 1000)
    controller.startGame()
    controller.mano = new Mano(
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
    assertEquals(controller.mano.playsLeft, 3)
    assertEquals(controller.mano.discardsLeft, 3)
  }

  test("flujo completo termina en victoria usando la API publica del controller") {
    val controller = new GameController(minimumScore = 10)
    controller.startGame()
    controller.mano = new Mano(
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
    controller.mano = new Mano(
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
    assert(output.toString.contains("Game Over, no se llego al puntaje"))
  }
}
