package cl.uchile.dcc
package logic.states

import logic.GameController

class FinalState(controller: GameController) extends GameState {
  if (controller.score < controller.minimumScore) {
    println("Game Over, no se llego al Score")
    controller.playerLost = true
  } else {
    println("Winner winner chicken dinner")
    controller.playerWon = true
  }
}
