package cl.uchile.dcc
package logic

import model.Hand

import cl.uchile.dcc.logic.states.{GameState, InitializingState}
/**
 * Controls the main flow and data of a game match.
 *
 * This class acts as the Context in the State design pattern. It holds
 * the current game state and delegates player actions to it. It also stores
 * all the relevant match data, such as the player's hand, current score,
 * and win/loss conditions.
 *
 * @param minimumScore target score required to win the game
 */
class GameController(
    val minimumScore: Int = 300
) {
  /** The player's current hand of cards and jokers. */
  var Hand: Hand = new Hand(List(), List())
  /** The player's accumulated score in the current match. */
  var score: Int = 0
  /** The active state of the game engine. */
  var actualState: GameState = new InitializingState(this)
  /** Flag indicating if the player has reached the minimum score and won. */
  var playerWon: Boolean = false
  /** Flag indicating if the player has failed to reach the score and lost. */
  var playerLost: Boolean = false

  /**
   * Starts a new game by delegating the action to the current state.
   */
  def startGame(): Unit = actualState.startGame()

  /**
   * Plays the selected cards from the player's hand.
   *
   * @param indices list of positions in the hand representing the cards to play
   */
  def playCards(indices: List[Int]): Unit = actualState.playCards(indices)

  /**
   * Discards the selected cards from the player's hand.
   *
   * @param indices list of positions in the hand representing the cards to discard
   */
  def discardCards(indices: List[Int]): Unit = actualState.discardCards(indices)

  /**
   * Transitions the game to a new phase or state.
   *
   * @param newState the next state to be set as the active state
   */
  def changeState(newState: GameState): Unit = {
    actualState = newState
  }

  /**
   * Resets the match back to its initial state and starts a new game.
   */
  def resetGame(): Unit = {
    actualState = new InitializingState(this)
    actualState.startGame()
  }
  
}
