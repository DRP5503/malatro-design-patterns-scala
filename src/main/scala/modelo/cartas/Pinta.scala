package cl.uchile.dcc
package modelo.cartas


abstract class Pinta(private val _tipo: String) {
  def tipo: String = _tipo
}

case class Trebol() extends Pinta("Trebol")
case class Pica() extends Pinta("Pica")
case class Corazon() extends Pinta("Corazon")
case class Diamante() extends Pinta("Diamante")