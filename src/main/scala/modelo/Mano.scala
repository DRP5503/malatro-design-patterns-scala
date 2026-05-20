package cl.uchile.dcc
package modelo

import modelo.cartas.{Carta, Joker}

class Mano(private var _cartas: List[Carta], private var _jokers: List[Joker]) {
  def cartas: List[Carta] = _cartas
  def jokers : List[Joker] = _jokers
  
  def añadirCartas(carta:Carta): Unit={
    _cartas = _cartas :+ carta
  }
  def añadirJoker(joker: Joker): Unit={
    _jokers = _jokers :+ joker
  }
  def eliminarCartas(indices: List[Int]): Unit={
    var cartasNuevas = List[Carta]()
    for (i <- cartas.indices) {
      if (!indices.contains(i))
        cartasNuevas = cartasNuevas :+ cartas(i)
    }
    _cartas = cartasNuevas
  }

  def eliminarJoker(indices: List[Int]): Unit = {
    var jokersNuevos = List[Joker]()
    for (i <- jokers.indices) {
      if (!indices.contains(i))
        jokersNuevos = jokersNuevos :+ jokers(i)
    }
    _jokers = jokersNuevos
  }

  def jugarCartas(indices: List[Int]): List[Carta] = {
    val cartasJugadas = indices.map(i => cartas(i))
    eliminarCartas(indices)
    cartasJugadas
  }
}
