package cl.uchile.dcc
package logic.states

import logic.GameController
/**
 * Represents the concluding phase of the match.
 *
 * Upon entering this state, the game evaluates the player's accumulated
 * score against the minimum required score defined in the controller.
 * It determines whether the player won or lost the match and sets the
 * respective flags in the controller context.
 *
 * @param controller the context managing the data and flow of the match
 */
class FinalState(controller: GameController) extends GameState {
  if (controller.score < controller.minimumScore) {
    println("Game Over, no se llego al Score")
    controller.playerLost = true
  } else {
    println("Winner winner chicken dinner")
    controller.playerWon = true
  }
}
