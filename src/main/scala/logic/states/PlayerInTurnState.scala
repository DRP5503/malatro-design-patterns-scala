package cl.uchile.dcc
package logic.states

import logic.{Calculator, GameController}

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