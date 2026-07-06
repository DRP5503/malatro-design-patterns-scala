package cl.uchile.dcc
package model.cards

import model.Score


abstract class Suit(private val _tipo: String) {
  def suitType: String = _tipo

  def applyScore(score: Score, joker: Joker): Score = {
    joker.applyScore(score, this)
  }
}

case class Club() extends Suit("Club")
case class Spade() extends Suit("Spade")
case class Heart() extends Suit("Heart")
case class Diamond() extends Suit("Diamond") {
  override def applyScore(score: Score, joker: Joker): Score = {
    joker.applyScore(score, this)
  }
}