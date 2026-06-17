package cl.uchile.dcc
package logica

abstract class GameState {
  def startGame(): Unit = ???
  def playCards(indices: List[Int]): Unit = ???
  def discardCards(indices: List[Int]): Unit = ???
  def calculateScore(//me llega la jugada y la mano directo?)
}

class InitializingState(controller: GameController) extends GameState {

}
class PlayerInTurnState(controller: GameController) extends GameState {

}
class PlayingCardsState(controller: GameController) extends GameState {

}
class DiscardingCards(controller: GameController) extends GameState {

}
