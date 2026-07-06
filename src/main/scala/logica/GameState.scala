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
    controller.score = 0
    controller.changeState(new PlayerInTurnState(controller))
  }
}

class PlayerInTurnState(controller: GameController) extends GameState {

  private def addScore(points: Int): Unit = {
    controller.score += points
  }

  override def playCards(indices: List[Int]): Unit = {
    val playedCards = controller.mano.jugarCartas(indices)
    val points = Calculator.calcularPuntaje(playedCards, controller.mano.jokers)

    addScore(points)
    if (controller.mano.playsLeft == 0)
      controller.changeState(new FinalState(controller))
  }

  override def discardCards(indices: List[Int]): Unit = {
    controller.mano.descartarCartas(indices)
  }
}


class FinalState(controller: GameController) extends GameState {
  if (controller.score < controller.minimumScore)
    println("Skill issue :(")
  else
    println("Winner winner chicken dinner")
}
