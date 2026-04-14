package cl.uchile.dcc
package modelo

abstract class ClasificacionRango(val nombre: String)

object Figura extends ClasificacionRango("Figura")
object Par extends  ClasificacionRango("Par")
object Impar extends  ClasificacionRango("Impar")

abstract class Rango(
             val orden: Int,
             val clasificacion: ClasificacionRango,
             val valor: Int
           ){
  def this(orden: Int, clasificacion: ClasificacionRango)=
    this(orden, clasificacion, orden)

  def esIgual(otro: Rango): Boolean = {
    this.orden == otro.orden &&
    this.valor == otro.valor &&
    this.clasificacion.nombre == otro.clasificacion.nombre
  }
}
class As extends Rango(1, Impar)
class Dos extends Rango(2, Par)
class Tres extends Rango(3, Impar)
class Cuatro extends Rango(4, Par)
class Cinco extends Rango(5, Impar)
class Seis extends Rango(6, Par)
class Siete extends Rango(7, Impar)
class Ocho extends Rango(8, Par)
class Nueve extends Rango(9, Impar)
class Diez extends Rango(10, Par)
class Jota extends Rango(11, Figura, 10)
class Quina extends Rango(12, Figura, 10)
class Kaiser extends Rango(13, Figura, 10)