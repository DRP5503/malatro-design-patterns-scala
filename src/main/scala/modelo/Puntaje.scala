package cl.uchile.dcc
package modelo

class Puntaje(val chips: Int, val mult: Float) {
  def agregarChips(c:Int): Puntaje = new Puntaje(chips + c, mult)
  def agregarMult(m:Float): Puntaje = new Puntaje(chips, mult + m)

  def esIgual(otro: Puntaje): Boolean = {
    this.chips == otro.chips &&
      this.mult == otro.mult
  }
}
