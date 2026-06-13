package cl.uchile.dcc
package modelo.cartas


import cl.uchile.dcc.modelo.{Jugada, Puntaje}

abstract class Joker(private val _nombre: String) {
  def nombre: String = _nombre
  def applyScore(score: Puntaje, jugada: Jugada): Puntaje = ???
  def applyScore(score: Puntaje, pinta: Pinta): Puntaje = ???
  def applyScore(score: Puntaje, rango: Rango): Puntaje = ???
}

class Greedy extends Joker("Greedy Joker")
class Devious extends Joker("Devious Joker")
class EvenSteven extends Joker("EvenSteven Joker")
class ScaryFace extends Joker("ScaryFace Joker")