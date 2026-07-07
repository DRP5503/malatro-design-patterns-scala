package cl.uchile.dcc
package logic.states

/**
 * Defines the blueprint for all game states in the match.
 *
 * This abstract class provides a default implementation for all possible
 * actions a player or the game engine can take. By default, every action
 * throws an `InvalidStateActionException`. Concrete state subclasses must
 * override the specific methods that are valid for their phase of the game.
 */
abstract class GameState {
  /**
   * Initiates the game setup and transitions to the playing phase.
   *
   * @throws InvalidStateActionException if the game cannot be started from the current state
   */
  def startGame(): Unit =
    throw new InvalidStateActionException("No se puede iniciar la partida desde este estado")

  /**
   * Plays a selection of cards from the player's hand.
   *
   * @param indices list of positions in the hand representing the cards to play
   * @throws InvalidStateActionException if cards cannot be played in the current state
   */
  def playCards(indices: List[Int]): Unit =
    throw new InvalidStateActionException("No se pueden jugar cards desde este estado")

  /**
   * Discards a selection of cards from the player's hand.
   *
   * @param indices list of positions in the hand representing the cards to discard
   * @throws InvalidStateActionException if cards cannot be discarded in the current state
   */
  def discardCards(indices: List[Int]): Unit =
    throw new InvalidStateActionException("No se pueden descartar cards desde este estado")

  /**
   * Transitions the game engine to a different state.
   *
   * @param newState the target state to transition to
   * @throws InvalidStateActionException if the state cannot be changed from the current state
   */
  def changeState(newState: GameState): Unit =
    throw new InvalidStateActionException("No se puede cambiar de estado desde este estado")
}

