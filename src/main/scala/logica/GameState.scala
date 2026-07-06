package cl.uchile.dcc
package logica

import modelo.Mano

abstract class GameState {
  def startGame(): Unit =
    throw new AccionInvalidaEstadoException("No se puede iniciar la partida desde este estado")

  def playCards(indices: List[Int]): Unit =
    throw new AccionInvalidaEstadoException("No se pueden jugar cartas desde este estado")

  def discardCards(indices: List[Int]): Unit =
    throw new AccionInvalidaEstadoException("No se pueden descartar cartas desde este estado")

  def changeState(newState: GameState): Unit =
    throw new AccionInvalidaEstadoException("No se puede cambiar de estado desde este estado")
}

class InitializingState(controller: GameController) extends GameState {
  override def startGame(): Unit = {
    controller.mano = new Mano(List(), List())
    controller.playsLeft = controller.maxPlays
    controller.discardsLeft = controller.maxDiscards
    controller.score = 0
    controller.changeState(new PlayerInTurnState(controller))
  }
}

class PlayerInTurnState(controller: GameController) extends GameState {
  override def playCards(indices: List[Int]): Unit = {
    if (!controller.canPlay)
      throw new NoQuedanJugadasException("No quedan jugadas disponibles")

    val playedCards = controller.mano.jugarCartas(indices)
    val points = Calculator.calcularPuntaje(playedCards, controller.mano.jokers)

    controller.addScore(points)
    controller.consumePlay()
    if (controller.playsLeft == 0)
      controller.changeState(new FinalState(controller))
  }

  override def discardCards(indices: List[Int]): Unit = {
    if (!controller.canDiscard)
      throw new NoQuedanDescartesException("No quedan descartes disponibles")

    controller.mano.descartarCartas(indices)
    controller.consumeDiscard()
  }
}


class FinalState(controller: GameController) extends GameState {
  if (controller.score < controller.minimumScore)
    println("Game Over, no se llego al puntaje")
  else
    println("Winner winner chicken dinner")
}
