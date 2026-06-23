package cl.uchile.dcc
package logica

import modelo.Mano

abstract class GameState {
  def startGame(): Unit = ???
  def playCards(indices: List[Int]): Unit = ???
  def discardCards(indices: List[Int]): Unit = ???
  def calculateScore(//me llega la jugada y la mano directo?)
}

class InitializingState(controller: GameController) extends GameState {
  override def startGame(): Unit = {
    //implementar metodo para dar 5 cartas random controller.mano = new Mano(obtenerCartasIniciales(), List())
    controller.playsLeft = 3
    controller.discardsLeft = 3
    controller.actualState = new PlayerInTurnState(controller)
  }

}
class PlayerInTurnState(controller: GameController) extends GameState {
  override def playCards(indices: List[Int]): Unit = {
    
  }

}
class PlayingCardsState(controller: GameController) extends GameState {

}
class DiscardingCards(controller: GameController) extends GameState {

}
