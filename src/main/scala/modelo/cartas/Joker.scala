package cl.uchile.dcc
package modelo.cartas


import cl.uchile.dcc.modelo.{Escalera, Jugada, Puntaje,EscaleraColor}
import cl.uchile.dcc.modelo.cartas.Par

abstract class Joker(private val _nombre: String) {
  def nombre: String = _nombre
  def applyScore(score: Puntaje, jugada: Jugada): Puntaje = score
  def applyScore(score: Puntaje, pinta: Pinta): Puntaje = score
  def applyScore(score: Puntaje, rango: Rango): Puntaje = score
  def applyScore(score: Puntaje, clasificacion: ClasificacionRango): Puntaje = score
  
  def applyScore(score: Puntaje, pinta: Diamante): Puntaje = applyScore(score, pinta: Pinta)
  def applyScore(score: Puntaje, jugada: Escalera): Puntaje = applyScore(score, jugada: Jugada)
  def applyScore(score: Puntaje, clasificacion: Par): Puntaje = applyScore(score, clasificacion: ClasificacionRango)
  def applyScore(score: Puntaje, clasificacion: Figura): Puntaje = applyScore(score, clasificacion: ClasificacionRango)
  def applyScore(score: Puntaje, jugada: EscaleraColor): Puntaje = applyScore(score, jugada: Jugada)
}

class Greedy extends Joker("Greedy Joker") {
  override def applyScore(score: Puntaje, pinta: Diamante): Puntaje = score.agregarMult(3)
}
class Devious extends Joker("Devious Joker") {
  override def applyScore(score: Puntaje, jugada: Escalera): Puntaje = score.agregarChips(100)
  override def applyScore(score: Puntaje, jugada: EscaleraColor): Puntaje = score.agregarChips(100)
}
class EvenSteven extends Joker("EvenSteven Joker") {
  override def applyScore(score: Puntaje, clasificacion: Par): Puntaje = score.agregarMult(4)
}
class ScaryFace extends Joker("ScaryFace Joker") {
  override def applyScore(score: Puntaje, clasificacion: Figura): Puntaje = score.agregarChips(30)
}