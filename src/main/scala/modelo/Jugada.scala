package cl.uchile.dcc
package modelo

import modelo.cartas.Carta


enum Jugada(val chipsBase: Int, val multBase: Int):
  case EscaleraColor extends Jugada(100, 8)
  case Color extends Jugada(35, 4)
  case Escalera extends Jugada(30, 4)
  case Trio extends Jugada(30, 4)
  case Par extends Jugada(10, 2)
  case CartaAlta extends Jugada(5, 1)