package cl.uchile.dcc
package logic

import model.Hand

import cl.uchile.dcc.logic.states.{GameState, InitializingState}

class GameController(
    val minimumScore: Int = 300
) {
  var Hand: Hand = new Hand(List(), List())
  var score: Int = 0
  var actualState: GameState = new InitializingState(this)
  var playerWon: Boolean = false
  var playerLost: Boolean = false

  def startGame(): Unit = actualState.startGame()

  def playCards(indices: List[Int]): Unit = actualState.playCards(indices)

  def discardCards(indices: List[Int]): Unit = actualState.discardCards(indices)

  def changeState(newState: GameState): Unit = {
    actualState = newState
  }

  def resetGame(): Unit = {
    actualState = new InitializingState(this)
    actualState.startGame()
  }
  
}
