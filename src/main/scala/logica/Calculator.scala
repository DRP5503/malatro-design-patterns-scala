package cl.uchile.dcc
package logica
import modelo.cartas.{Carta, Joker}

import cl.uchile.dcc.modelo.Puntaje

object Calculator {
  def calcularPuntaje(cartas: List[Carta], jokers: List[Joker]): Int = {

    val jugada = Evaluador.identificarJugada(cartas)

    var puntajeActual = new Puntaje(jugada.chipsBase, jugada.multBase)

    for (carta <- cartas) {
      puntajeActual = puntajeActual.agregarChips(carta.rango.valor)
    }

    for (carta <- cartas) {
      puntajeActual = carta.applyScore(puntajeActual, jokers)
    }

    (puntajeActual.chips * puntajeActual.mult).toInt
  }


}
