package cl.uchile.dcc
package logic
import model.cards.{Card, Joker}

import cl.uchile.dcc.model.Score
/**
 * Handles the scoring logic for a played hand in the game.
 *
 * This singleton object processes the cards and active jokers to compute
 * the total score of a play, avoiding unnecessary instantiation.
 */
object Calculator {
  /**
   * Calculates the final score for a specific play.
   *
   * The calculation evaluates the base score of the identified poker hand,
   * adds the individual chip values of the played cards, applies the effects
   * of any active jokers (using double dispatch), and finally applies any
   * specific card effects.
   *
   * @param cards  list of cards played in the current hand
   * @param jokers list of active jokers affecting the game
   * @return the total calculated score (chips multiplied by the multiplier)
   */
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
