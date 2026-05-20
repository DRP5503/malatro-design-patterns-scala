package cl.uchile.dcc
package modelo.cartas

class Carta(private val _rango: Rango, private val _pinta: Pinta) {

  //getter y setter
  def rango: Rango = _rango
  def pinta: Pinta = _pinta
  
  def esIgual(otra:Carta): Boolean =
    this.rango.orden == otra.rango.orden &&
    this.rango.valor == otra.rango.valor &&
    this.rango.clasificacion.nombre == otra.rango.clasificacion.nombre &&
    this.pinta.tipo == otra.pinta.tipo
}
