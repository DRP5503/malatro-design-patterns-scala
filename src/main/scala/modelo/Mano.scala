package cl.uchile.dcc
package modelo

import modelo.cartas.{Carta, Joker}

class Mano(private var _cartas: List[Carta], private var _jokers: List[Joker]) {
  def cartas: List[Carta] = _cartas
  def jokers : List[Joker] = _jokers
  
  def añadirCartas(carta:Carta): Unit={
    if (_cartas.length == 8)
      throw new ManoLlenaException("Mano llena")
    _cartas = _cartas :+ carta
  }
  def añadirJoker(joker: Joker): Unit={
    if (_jokers.length == 2)
      throw new JokersLlenosException("La mano ya tiene 2 Jokers")
    _jokers = _jokers :+ joker
  }
  def eliminarCartas(indices: List[Int]): Unit={
    for (i <- indices) {
      if (i < 0 || i >= _cartas.length)
        throw  new IndiceCartaInvalidoException(s"El indice $i no es valido")
    }
    var cartasNuevas = List[Carta]()
    for (i <- cartas.indices) {
      if (!indices.contains(i))
        cartasNuevas = cartasNuevas :+ cartas(i)
    }
    _cartas = cartasNuevas
  }

  def eliminarJoker(indices: List[Int]): Unit = {
    for (i <- indices) {
      if (i < 0 || i >= _jokers.length)
        throw new IndiceJokerInvalidoException(s"El indice $i no es valido")
    }
    var jokersNuevos = List[Joker]()
    for (i <- jokers.indices) {
      if (!indices.contains(i))
        jokersNuevos = jokersNuevos :+ jokers(i)
    }
    _jokers = jokersNuevos
  }

  def jugarCartas(indices: List[Int]): List[Carta] = {
    if (indices.length > 5)
      throw new CartasMaximasException("No se pueden jugar mas de 5 cartas")
    if (indices.length < 1)
      throw new CartasMinimasException("No se puede jugar menos de 1 carta")
    for (i <- indices) {
      if (i < 0 || i >= _cartas.length)
        throw new IndicesCartasInvalidosException(s"El indice $i no es valido")
    }
    val cartasJugadas = indices.map(i => cartas(i))
    eliminarCartas(indices)
    cartasJugadas
  }

  def descartarCartas(indices: List[Int]): Unit = {
    if (indices.length > 5)
      throw new DescartesMaximosException("No se pueden descartar mas de 5 cartas")
    if (indices.length < 1)
      throw new DescartesMinimosException("No se puede descartar menos de 1 carta")
    for (i <- indices) {
      if (i < 0 || i >= _cartas.length)
        throw new IndicesDescartesInvalidosException(s"El indice $i no es valido")
    }
    eliminarCartas(indices)
  }
}
