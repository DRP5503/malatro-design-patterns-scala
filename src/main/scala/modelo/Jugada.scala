package cl.uchile.dcc
package modelo

import modelo.cartas.Carta


enum TipoJugada:
  case CartaAlta, Par, Trio, Escalera, Color, EscaleraColor
  
case class Jugada(tipo:TipoJugada, cartas: List[Carta])
