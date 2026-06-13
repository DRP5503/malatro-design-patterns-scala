package cl.uchile.dcc
package modelo.cartas

import modelo.Puntaje

class Carta(private val _rango: Rango, private val _pinta: Pinta) {

  //getter y setter
  def rango: Rango = _rango

  def pinta: Pinta = _pinta

  def esIgual(otra: Carta): Boolean = {
    this.rango.orden == otra.rango.orden &&
      this.rango.valor == otra.rango.valor &&
      this.rango.clasificacion.nombre == otra.rango.clasificacion.nombre &&
      this.pinta.tipo == otra.pinta.tipo
  }

  def applyScore(score: Puntaje, jokers: List[Joker]): Puntaje = {
    var puntajeActual = score
    for (joker <- jokers) {
      puntajeActual = rango.applyScore(puntajeActual, joker)
      puntajeActual = pinta.applyScore(puntajeActual, joker)
    }
    puntajeActual
  }
}
