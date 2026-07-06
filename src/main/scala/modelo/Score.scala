package cl.uchile.dcc
package model

class Score(val chips: Int, val mult: Float) {
  def addChips(c:Int): Score = new Score(chips + c, mult)
  def addMult(m:Float): Score = new Score(chips, mult + m)

  def isEqual(other: Score): Boolean = {
    this.chips == other.chips &&
      this.mult == other.mult
  }
}
