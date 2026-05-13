package cl.uchile.dcc
package modelo

import modelo.cartas.{Carta, Joker}

class Mano(var cartas: List[Carta], var jokers: List[Joker]) {
  def añadirCartas(carta:Carta): Unit={
    cartas = cartas :+ carta
  }
  def añadirJoker(joker: Joker): Unit={
    jokers = jokers :+ joker
  }
  def eliminarCartas(indices: List[Int]): Unit={
    var cartasNuevas = List[Carta]()
    for (i <- cartas.indices) {
      if (!indices.contains(i))
        cartasNuevas = cartasNuevas :+ cartas(i)
    }
    cartas = cartasNuevas
  }

  def eliminarJoker(indices: List[Int]): Unit = {
    var jokersNuevos = List[Joker]()
    for (i <- jokers.indices) {
      if (!indices.contains(i))
        jokersNuevos = jokersNuevos :+ jokers(i)
    }
    jokers = jokersNuevos
  }

  def jugarCartas(indices: List[Int]): List[Carta] = {
    val cartasJugadas = indices.map(i => cartas(i))
    eliminarCartas(indices)
    cartasJugadas
  }
}
