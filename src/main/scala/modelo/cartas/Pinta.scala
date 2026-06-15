package cl.uchile.dcc
package modelo.cartas

import modelo.Puntaje


abstract class Pinta(private val _tipo: String) {
  def tipo: String = _tipo

  def applyScore(score: Puntaje, joker: Joker): Puntaje = {
    joker.applyScore(score, this)
  }
}

case class Trebol() extends Pinta("Trebol")
case class Pica() extends Pinta("Pica")
case class Corazon() extends Pinta("Corazon")
case class Diamante() extends Pinta("Diamante") {
  override def applyScore(score: Puntaje, joker: Joker): Puntaje = {
    joker.applyScore(score, this)
  }
}