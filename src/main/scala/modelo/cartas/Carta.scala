package cl.uchile.dcc
package modelo.cartas

class Carta(val rango: Rango, val pinta: Pinta) {
  def esIgual(otra:Carta): Boolean =
    this.rango.orden == otra.rango.orden &&
    this.rango.valor == otra.rango.valor &&
    this.rango.clasificacion.nombre == otra.rango.clasificacion.nombre &&
    this.pinta.tipo == otra.pinta.tipo
}
