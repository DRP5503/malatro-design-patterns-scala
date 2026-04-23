package cl.uchile.dcc
package modelo.cartas

import modelo.cartas.Joker

abstract class Joker(val nombre: String)

class Greedy extends Joker("Greedy Joker")
class Devious extends Joker("Devious Joker")
class EvenSteven extends Joker("EvenSteven Joker")
class ScaryFace extends Joker("ScaryFace Joker")