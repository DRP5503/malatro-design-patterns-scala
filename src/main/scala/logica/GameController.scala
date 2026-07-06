package cl.uchile.dcc
package logica

import modelo.Mano

class GameController(
    val minimumScore: Int = 300,
    val maxPlays: Int = 3,
    val maxDiscards: Int = 3
) {
  var mano: Mano = new Mano(List(), List())
  var playsLeft: Int = maxPlays
  var discardsLeft: Int = maxDiscards
  var score: Int = 0
  var actualState: GameState = new InitializingState(this)

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

  def addScore(points: Int): Unit = {
    score += points
  }

  def consumePlay(): Unit = {
    if (!canPlay)
      throw new NoQuedanJugadasException("No quedan jugadas disponibles")
    playsLeft -= 1
  }

  def consumeDiscard(): Unit = {
    if (!canDiscard)
      throw new NoQuedanDescartesException("No quedan descartes disponibles")
    discardsLeft -= 1
  }

  def canPlay: Boolean = playsLeft > 0

  def canDiscard: Boolean = discardsLeft > 0
}
