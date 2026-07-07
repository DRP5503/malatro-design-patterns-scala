package cl.uchile.dcc
package logic.states

import logic.{Calculator, GameController}
/**
 * Represents the active phase of the game where the player takes their turn.
 *
 * During this state, the player can choose to play cards to accumulate points
 * or discard cards to refresh their hand. If the player runs out of allowed
 * plays, the game automatically transitions to the final state.
 *
 * @param controller the context managing the data and flow of the match
 */
class PlayerInTurnState(controller: GameController) extends GameState {
  /** * Adds the calculated points to the controller's total score.
   */
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