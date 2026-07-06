package cl.uchile.dcc
package model

import model.cards.{Card, Joker}

class Hand(
    private var _cartas: List[Card],
    private var _jokers: List[Joker],
    private var _playsLeft: Int = 3,
    private var _discardsLeft: Int = 3
) {
  def cards: List[Card] = _cartas
  def jokers : List[Joker] = _jokers
  def playsLeft: Int = _playsLeft
  def discardsLeft: Int = _discardsLeft
  
  def addCard(Card:Card): Unit={
    if (_cartas.length == 8)
      throw new HandFullException("Hand llena")
    _cartas = _cartas :+ Card
  }
  def addJoker(joker: Joker): Unit={
    if (_jokers.length == 2)
      throw new JokersFullException("La Hand ya tiene 2 Jokers")
    _jokers = _jokers :+ joker
  }
  def removeCards(indices: List[Int]): Unit={
    for (i <- indices) {
      if (i < 0 || i >= _cartas.length)
        throw  new InvalidCardIndexException(s"El indice $i no es valido")
    }
    var newCards = List[Card]()
    for (i <- cards.indices) {
      if (!indices.contains(i))
        newCards = newCards :+ cards(i)
    }
    _cartas = newCards
  }

  def removeJoker(indices: List[Int]): Unit = {
    for (i <- indices) {
      if (i < 0 || i >= _jokers.length)
        throw new InvalidJokerIndexException(s"El indice $i no es valido")
    }
    var newJokers = List[Joker]()
    for (i <- jokers.indices) {
      if (!indices.contains(i))
        newJokers = newJokers :+ jokers(i)
    }
    _jokers = newJokers
  }

  def playCards(indices: List[Int]): List[Card] = {
    if (_playsLeft == 0)
      throw new NoPlaysLeftException("No quedan jugadas disponibles")
    if (indices.length > 5)
      throw new MaxCardsException("No se pueden jugar mas de 5 cards")
    if (indices.length < 1)
      throw new MinCardsException("No se puede jugar menos de 1 Card")
    for (i <- indices) {
      if (i < 0 || i >= _cartas.length)
        throw new InvalidCardIndicesException(s"El indice $i no es valido")
    }
    val playedCards = indices.map(i => cards(i))
    removeCards(indices)
    _playsLeft -= 1
    playedCards
  }

  def discardCards(indices: List[Int]): Unit = {
    if (_discardsLeft == 0)
      throw new NoDiscardsLeftException("No quedan descartes disponibles")
    if (indices.length > 5)
      throw new MaxDiscardsException("No se pueden descartar mas de 5 cards")
    if (indices.length < 1)
      throw new MinDiscardsException("No se puede descartar menos de 1 Card")
    for (i <- indices) {
      if (i < 0 || i >= _cartas.length)
        throw new InvalidDiscardIndicesException(s"El indice $i no es valido")
    }
    removeCards(indices)
    _discardsLeft -= 1
  }
}
