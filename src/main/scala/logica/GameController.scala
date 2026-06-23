package cl.uchile.dcc
package logica
import modelo.{Mano, Puntaje}


class GameController {
  var mano: Mano = new Mano(List(), List())
  var playsLeft: Int = 3
  var discardsLeft: Int = 3
  var actualState: GameState = InitializingState(this)
  var score: Puntaje = new Puntaje(0,0)
  
  def startGame(): Unit = actualState.startGame()

}
