package cl.uchile.dcc
package logica
import modelo.cartas.{Carta, Joker}

import cl.uchile.dcc.modelo.Puntaje

object Calculator {
  def calcularPuntaje(cartas: List[Carta], jokers: List[Joker]): Int = {

    val jugada = Evaluador.identificarJugada(cartas)

    var puntajeActual = new Puntaje(jugada.chipsBase, jugada.multBase)
    println(s"Base: chips=${puntajeActual.chips} mult=${puntajeActual.mult}")

    for (carta <- cartas) {
      puntajeActual = puntajeActual.agregarChips(carta.rango.valor)
    }
    for (joker <- jokers) {
      puntajeActual = jugada.applyScore(puntajeActual, joker)
    }

    println(s"Tras cartas: chips=${puntajeActual.chips} mult=${puntajeActual.mult}")
    for (carta <- cartas) {
      puntajeActual = carta.applyScore(puntajeActual, jokers)
    }
    println(s"Tras jokers: chips=${puntajeActual.chips} mult=${puntajeActual.mult}")
    (puntajeActual.chips * puntajeActual.mult).toInt
  }


}
