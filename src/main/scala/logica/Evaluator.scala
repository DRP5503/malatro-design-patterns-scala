package cl.uchile.dcc
package logic
import model.cards.Card

import cl.uchile.dcc.model.PokerHand
import cl.uchile.dcc.model.*

//el proposito principal de Evaluator es indentificar la PokerHand
//y calcular el Score correspondiente
object Evaluator {
    def identifyHand(cards: List[Card]): PokerHand= {
        if (isStraightFlush(cards)) StraightFlush()
        else if (isFlush(cards)) Flush()
        else if (isStraight(cards)) Straight()
        else if (isThreeOfAKind(cards)) ThreeOfAKind()
        else if (isPair(cards)) PairHand()
        else HighCard()
    }

    private def sortRanks(cards: List[Card]): List[Card]=
        cards.sortBy(_.Rank.order)

    private def rankFrequency(cards: List[Card]): Map[Int, Int]=
        cards.groupBy(_.Rank.order).map {case (order, lista) => (order, lista.length)}

    private def isStraightFlush(cards: List[Card]): Boolean={
        isFlush(cards) && isStraight(cards)
    }

    private def isFlush(cards: List[Card]): Boolean={
        val firstSuit = cards.head.Suit
        cards.forall(_.Suit == firstSuit)
    }

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

    private def isStraightAux(orders: List[Int]): Boolean = {
        val ordered = orders.sorted
        for (i <- 0 until ordered.length - 1) {
            if (ordered(i + 1) - ordered(i) != 1)
                return false
        }
        true
    }

    private def isThreeOfAKind(cards: List[Card]): Boolean={
        val frequencies = rankFrequency(cards)
        frequencies.exists {case (_, frequency) => frequency == 3}
    }

    private def isPair(cards: List[Card]): Boolean=
        val frequencies = rankFrequency(cards)
        frequencies.exists { case (_, frequency) => frequency == 2 }
}
