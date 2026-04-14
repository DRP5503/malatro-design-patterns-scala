package cl.uchile.dcc
package modelo

class Puntaje(val chips: Int, val mult: Float) {

  def esIgual(otro: Puntaje): Boolean = {
    this.chips == otro.chips &&
      this.mult == otro.mult
  }
}
