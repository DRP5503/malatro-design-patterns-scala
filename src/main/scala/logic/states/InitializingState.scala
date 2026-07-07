package cl.uchile.dcc
package logic.states

import logic.GameController
import model.Hand
/**
 * Represents the setup phase of the game.
 *
 * This state is responsible for initializing the match conditions, such as
 * resetting the score to zero and dealing the initial hand, before
 * immediately transitioning the game to the player's active turn.
 *
 * @param controller the context managing the data and flow of the match
 */
class InitializingState(controller: GameController) extends GameState {
  override def startGame(): Unit = {
    controller.Hand = new Hand(List(), List())
    controller.score = 0
    controller.changeState(new PlayerInTurnState(controller))
  }
}
