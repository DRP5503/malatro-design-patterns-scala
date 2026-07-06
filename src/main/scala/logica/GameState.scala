package cl.uchile.dcc
package logic

import model.Hand

abstract class GameState {
  def startGame(): Unit =
    throw new InvalidStateActionException("No se puede iniciar la partida desde este estado")

  def playCards(indices: List[Int]): Unit =
    throw new InvalidStateActionException("No se pueden jugar cards desde este estado")

  def discardCards(indices: List[Int]): Unit =
    throw new InvalidStateActionException("No se pueden descartar cards desde este estado")

  def changeState(newState: GameState): Unit =
    throw new InvalidStateActionException("No se puede cambiar de estado desde este estado")
}

class InitializingState(controller: GameController) extends GameState {
  override def startGame(): Unit = {
    controller.Hand = new Hand(List(), List())
    controller.score = 0
    controller.changeState(new PlayerInTurnState(controller))
  }
}

class PlayerInTurnState(controller: GameController) extends GameState {

  private def addScore(points: Int): Unit = {
    controller.score += points
  }

  override def playCards(indices: List[Int]): Unit = {
    val playedCards = controller.Hand.playCards(indices)
    val points = Calculator.calculateScore(playedCards, controller.Hand.jokers)

    addScore(points)
    if (controller.Hand.playsLeft == 0)
      controller.changeState(new FinalState(controller))
  }

  override def discardCards(indices: List[Int]): Unit = {
    controller.Hand.discardCards(indices)
  }
}


class FinalState(controller: GameController) extends GameState {
  if (controller.score < controller.minimumScore)
    println("Game Over, no se llego al Score")
  else
    println("Winner winner chicken dinner")
}
