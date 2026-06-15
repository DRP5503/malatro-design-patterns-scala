package cl.uchile.dcc
package modelo.cartas

import modelo.*

abstract class ClasificacionRango(val nombre: String) {
  def applyScore(score: Puntaje, joker: Joker): Puntaje = joker.applyScore(score, this)
}

case class Figura() extends ClasificacionRango("Figura") {
  override def applyScore(score: Puntaje, joker: Joker): Puntaje = joker.applyScore(score, Figura())
}

case class Par() extends  ClasificacionRango("Par") {
  override def applyScore(score: Puntaje, joker: Joker): Puntaje = joker.applyScore(score, this)
}
case class Impar() extends  ClasificacionRango("Impar")

abstract class Rango(
             private val _orden: Int,
             private val _clasificacion: ClasificacionRango,
             private val _valor: Int
           ){
  def orden: Int = _orden
  def clasificacion: ClasificacionRango = _clasificacion
  def valor: Int = _valor

  def applyScore(score: Puntaje, joker: Joker): Puntaje = {
    var puntajeActual = joker.applyScore(score, this)
    puntajeActual = clasificacion.applyScore(puntajeActual, joker)
    puntajeActual
  }
  
  def this(orden: Int, clasificacion: ClasificacionRango)=
    this(orden, clasificacion, orden)

  def esIgual(otro: Rango): Boolean = {
    this.orden == otro.orden &&
    this.valor == otro.valor &&
    this.clasificacion.nombre == otro.clasificacion.nombre
  }
  def ordenes: List[Int] = List(orden)
}
class As extends Rango(1, Impar()) {
  override def ordenes: List[Int] = List(1, 14)
}
class Dos extends Rango(2, Par())
class Tres extends Rango(3, Impar())
class Cuatro extends Rango(4, Par())
class Cinco extends Rango(5, Impar())
class Seis extends Rango(6, Par())
class Siete extends Rango(7, Impar())
class Ocho extends Rango(8, Par())
class Nueve extends Rango(9, Impar())
class Diez extends Rango(10, Par())
class Jota extends Rango(11, Figura(), 10)
class Quina extends Rango(12, Figura(), 10)
class Kaiser extends Rango(13, Figura(), 10)