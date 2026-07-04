package cl.uchile.dcc
package logica

import modelo.Mano
import modelo.cartas.Carta

class GameController(
    val minimumScore: Int = 300,
    val maxPlays: Int = 3,
    val maxDiscards: Int = 3
) {
  var mano: Mano = new Mano(List(), List())
  var deck: List[Carta] = List()
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
    mano = new Mano(List(), List())
    deck = List()
    playsLeft = maxPlays
    discardsLeft = maxDiscards
    score = 0
    actualState = new InitializingState(this)
  }

  def addScore(points: Int): Unit = {
    score += points
  }

  def consumePlay(): Unit = {
    if (!canPlay)
      throw new IllegalStateException("No quedan jugadas disponibles")
    playsLeft -= 1
  }

  def consumeDiscard(): Unit = {
    if (!canDiscard)
      throw new IllegalStateException("No quedan descartes disponibles")
    discardsLeft -= 1
  }

  def canPlay: Boolean = playsLeft > 0

  def canDiscard: Boolean = discardsLeft > 0

  def hasWon: Boolean = score >= minimumScore

  def hasLost: Boolean = playsLeft == 0 && !hasWon
}
