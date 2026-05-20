package cl.uchile.dcc
package modelo.cartas

import modelo.cartas.Joker

abstract class Joker(private val _nombre: String) {
  def nombre: String = _nombre
}

class Greedy extends Joker("Greedy Joker")
class Devious extends Joker("Devious Joker")
class EvenSteven extends Joker("EvenSteven Joker")
class ScaryFace extends Joker("ScaryFace Joker")