package cl.uchile.dcc
package logic.states

abstract class GameState {
  def startGame(): Unit =
    throw new InvalidStateActionException("No se puede iniciar la partida desde este estado")

  def playCards(indices: List[Int]): Unit =
    throw new InvalidStateActionException("No se pueden jugar cards desde este estado")

  def discardCards(indices: List[Int]): Unit =
    throw new InvalidStateActionException("No se pueden descartar cards desde este estado")

  def changeState(newState: GameState): Unit =
    throw new InvalidStateActionException("No se puede cambiar de estado desde este estado")
}

