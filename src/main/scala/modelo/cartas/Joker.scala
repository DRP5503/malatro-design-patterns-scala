package cl.uchile.dcc
package model.cards


import cl.uchile.dcc.model.{Straight, PokerHand, Score,StraightFlush}
import cl.uchile.dcc.model.cards.Even

abstract class Joker(private val _nombre: String) {
  def name: String = _nombre
  def applyScore(score: Score, PokerHand: PokerHand): Score = score
  def applyScore(score: Score, Suit: Suit): Score = score
  def applyScore(score: Score, Rank: Rank): Score = score
  def applyScore(score: Score, classification: RankClassification): Score = score
  
  def applyScore(score: Score, Suit: Diamond): Score = applyScore(score, Suit: Suit)
  def applyScore(score: Score, PokerHand: Straight): Score = applyScore(score, PokerHand: PokerHand)
  def applyScore(score: Score, classification: Even): Score = applyScore(score, classification: RankClassification)
  def applyScore(score: Score, classification: Face): Score = applyScore(score, classification: RankClassification)
  def applyScore(score: Score, PokerHand: StraightFlush): Score = applyScore(score, PokerHand: PokerHand)
}

class Greedy extends Joker("Greedy Joker") {
  override def applyScore(score: Score, Suit: Diamond): Score = score.addMult(3)
}
class Devious extends Joker("Devious Joker") {
  override def applyScore(score: Score, PokerHand: Straight): Score = score.addChips(100)
  override def applyScore(score: Score, PokerHand: StraightFlush): Score = score.addChips(100)
}
class EvenSteven extends Joker("EvenSteven Joker") {
  override def applyScore(score: Score, classification: Even): Score = score.addMult(4)
}
class ScaryFace extends Joker("ScaryFace Joker") {
  override def applyScore(score: Score, classification: Face): Score = score.addChips(30)
}