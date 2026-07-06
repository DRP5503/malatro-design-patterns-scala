package cl.uchile.dcc
package logic
import model.cards.{Card, Joker}

import cl.uchile.dcc.model.Score

object Calculator {
  def calculateScore(cards: List[Card], jokers: List[Joker]): Int = {

    val PokerHand = Evaluator.identifyHand(cards)

    var currentScore = new Score(PokerHand.chipsBase, PokerHand.multBase)
    println(s"Base: chips=${currentScore.chips} mult=${currentScore.mult}")

    for (Card <- cards) {
      currentScore = currentScore.addChips(Card.Rank.value)
    }
    for (joker <- jokers) {
      currentScore = PokerHand.applyScore(currentScore, joker)
    }

    println(s"Tras cards: chips=${currentScore.chips} mult=${currentScore.mult}")
    for (Card <- cards) {
      currentScore = Card.applyScore(currentScore, jokers)
    }
    println(s"Tras jokers: chips=${currentScore.chips} mult=${currentScore.mult}")
    (currentScore.chips * currentScore.mult).toInt
  }


}
