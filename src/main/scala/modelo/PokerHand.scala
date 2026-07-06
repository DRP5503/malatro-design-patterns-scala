package cl.uchile.dcc
package model

import model.cards.*

abstract class PokerHand(val chipsBase: Int, val multBase: Int) {
  def applyScore(score: Score, joker: Joker): Score = {
    joker.applyScore(score, this)
  }

}
case class StraightFlush() extends PokerHand(100, 8) {
  override def applyScore(score: Score, joker: Joker): Score = joker.applyScore(score, this)
}
case class Flush() extends PokerHand(35, 4)
case class Straight() extends PokerHand(30, 4) {
  override def applyScore(score: Score, joker: Joker): Score = joker.applyScore(score, this)
}
case class ThreeOfAKind() extends PokerHand(30, 4)
case class PairHand() extends PokerHand(10, 2)
case class HighCard() extends PokerHand(5, 1)