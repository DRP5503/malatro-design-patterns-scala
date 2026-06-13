package cl.uchile.dcc
package modelo

import modelo.cartas.*

abstract class Jugada(val chipsBase: Int, val multBase: Int) {
  def applyScore(score: Puntaje, joker: Joker): Puntaje = {
    joker.applyScore(score, this)
  }

}
case class EscaleraColor() extends Jugada(100, 8)
case class Color() extends Jugada(35, 4)
case class Escalera() extends Jugada(30, 4)
case class Trio() extends Jugada(30, 4)
case class JugadaPar() extends Jugada(10, 2)
case class CartaAlta() extends Jugada(5, 1)