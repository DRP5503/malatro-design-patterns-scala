package cl.uchile.dcc
package model.cards

import model.*

abstract class RankClassification(val name: String) {
  def applyScore(score: Score, joker: Joker): Score = joker.applyScore(score, this)
}

case class Face() extends RankClassification("Face") {
  override def applyScore(score: Score, joker: Joker): Score = joker.applyScore(score, Face())
}

case class Even() extends  RankClassification("Even") {
  override def applyScore(score: Score, joker: Joker): Score = joker.applyScore(score, this)
}
case class Odd() extends  RankClassification("Odd")

abstract class Rank(
             private val _orden: Int,
             private val _clasificacion: RankClassification,
             private val _valor: Int
           ){
  def order: Int = _orden
  def classification: RankClassification = _clasificacion
  def value: Int = _valor

  def applyScore(score: Score, joker: Joker): Score = {
    var currentScore = joker.applyScore(score, this)
    currentScore = classification.applyScore(currentScore, joker)
    currentScore
  }
  
  def this(order: Int, classification: RankClassification)=
    this(order, classification, order)

  def isEqual(other: Rank): Boolean = {
    this.order == other.order &&
    this.value == other.value &&
    this.classification.name == other.classification.name
  }
  def orders: List[Int] = List(order)
}
class As extends Rank(1, Odd()) {
  override def orders: List[Int] = List(1, 14)
}
class Two extends Rank(2, Even())
class Three extends Rank(3, Odd())
class Four extends Rank(4, Even())
class Five extends Rank(5, Odd())
class Six extends Rank(6, Even())
class Seven extends Rank(7, Odd())
class Eight extends Rank(8, Even())
class Nine extends Rank(9, Odd())
class Ten extends Rank(10, Even())
class Jack extends Rank(11, Face(), 10)
class Queen extends Rank(12, Face(), 10)
class King extends Rank(13, Face(), 10)