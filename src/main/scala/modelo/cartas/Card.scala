package cl.uchile.dcc
package model.cards

import model.Score

class Card(private val _rango: Rank, private val _pinta: Suit) {

  //getter y setter
  def Rank: Rank = _rango

  def Suit: Suit = _pinta

  def isEqual(other: Card): Boolean = {
    this.Rank.order == other.Rank.order &&
      this.Rank.value == other.Rank.value &&
      this.Rank.classification.name == other.Rank.classification.name &&
      this.Suit.suitType == other.Suit.suitType
  }

  def applyScore(score: Score, jokers: List[Joker]): Score = {
    var currentScore = score
    for (joker <- jokers) {
      currentScore = Rank.applyScore(currentScore, joker)
      currentScore = Suit.applyScore(currentScore, joker)
    }
    currentScore
  }
}
