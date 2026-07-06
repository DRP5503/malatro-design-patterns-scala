package cl.uchile.dcc
package logic.states

import logic.GameController
import model.Hand

class InitializingState(controller: GameController) extends GameState {
  override def startGame(): Unit = {
    controller.Hand = new Hand(List(), List())
    controller.score = 0
    controller.changeState(new PlayerInTurnState(controller))
  }
}
