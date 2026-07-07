package cl.uchile.dcc
package logic
import model.cards.Card

import cl.uchile.dcc.model.PokerHand
import cl.uchile.dcc.model.*

/**
 * Analyzes and determines the highest poker hand from a given set of cards.
 *
 * This singleton object provides the logic to evaluate a list of cards
 * and classify them into valid poker hands. It is implemented as an object
 * to avoid unnecessary instantiations during the evaluation process.
 */
object Evaluator {
    /**
     * Identifies the highest value poker hand from a provided list of cards.
     *
     * @param cards the list of cards to be evaluated
     * @return the corresponding `PokerHand` identified
     */
    def identifyHand(cards: List[Card]): PokerHand= {
        if (isStraightFlush(cards)) StraightFlush()
        else if (isFlush(cards)) Flush()
        else if (isStraight(cards)) Straight()
        else if (isThreeOfAKind(cards)) ThreeOfAKind()
        else if (isPair(cards)) PairHand()
        else HighCard()
    }

    /**
     * Sorts a list of cards based on their rank order.
     *
     * @param cards the list of cards to sort
     * @return a new list of cards sorted by rank order
     */
    private def sortRanks(cards: List[Card]): List[Card]=
        cards.sortBy(_.Rank.order)

    /**
     * Calculates the frequency of each rank order in a given list of cards.
     *
     * @param cards the list of cards to analyze
     * @return a map where the key is the rank order and the value is its frequency
     */
    private def rankFrequency(cards: List[Card]): Map[Int, Int]=
        cards.groupBy(_.Rank.order).map {case (order, lista) => (order, lista.length)}

    /**
     * Checks if the provided cards form a Straight Flush.
     *
     * @param cards the list of cards to evaluate
     * @return true if the cards form a Straight Flush, false otherwise
     */
    private def isStraightFlush(cards: List[Card]): Boolean={
        isFlush(cards) && isStraight(cards)
    }

    /**
     * Checks if the provided cards form a Flush (all cards have the same suit).
     *
     * @param cards the list of cards to evaluate
     * @return true if the cards form a Flush, false otherwise
     */
    private def isFlush(cards: List[Card]): Boolean={
        val firstSuit = cards.head.Suit
        cards.forall(_.Suit == firstSuit)
    }

    /**
     * Checks if the provided cards form a Straight (consecutive rank orders).
     *
     * This method accounts for standard straights as well as Ace-high straights.
     *
     * @param cards the list of cards to evaluate
     * @return true if the cards form a Straight, false otherwise
     */
    private def isStraight(cards: List[Card]): Boolean = {
        // Caso normal
        val orders = cards.map(_.Rank.order)
        if (isStraightAux(orders)) return true

        // Caso As alto
        val hasAce = cards.exists(c => c.Rank.orders.length > 1)
        if (hasAce) {
            val aceHighOrders = cards.map(c =>
                if (c.Rank.orders.length > 1) 14 else c.Rank.order
            )
            return isStraightAux(aceHighOrders)
        }

        false
    }

    /**
     * Auxiliary method to determine if a list of integer orders is strictly consecutive.
     *
     * @param orders the list of integer rank orders to check
     * @return true if the integers are consecutive, false otherwise
     */
    private def isStraightAux(orders: List[Int]): Boolean = {
        val ordered = orders.sorted
        for (i <- 0 until ordered.length - 1) {
            if (ordered(i + 1) - ordered(i) != 1)
                return false
        }
        true
    }

    /**
     * Checks if the provided cards contain a Three of a Kind.
     *
     * @param cards the list of cards to evaluate
     * @return true if at least three cards share the same rank, false otherwise
     */
    private def isThreeOfAKind(cards: List[Card]): Boolean={
        val frequencies = rankFrequency(cards)
        frequencies.exists {case (_, frequency) => frequency == 3}
    }

    /**
     * Checks if the provided cards contain a Pair.
     *
     * @param cards the list of cards to evaluate
     * @return true if at least two cards share the same rank, false otherwise
     */
    private def isPair(cards: List[Card]): Boolean=
        val frequencies = rankFrequency(cards)
        frequencies.exists { case (_, frequency) => frequency == 2 }
}
