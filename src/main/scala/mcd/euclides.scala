package cl.uchile.dcc
package mcd

/*
El siguiente import es necesario sí quieren leer números enteros desde la consola.
Pueden leer un entero de la siguiente forma:

  val a = readInt()
  
y luego usarlo como deseen

  val res = a + 5
  println(s"El resultado del número ingresado más 5 es $res")

*/
import scala.io.StdIn.readInt

@main def euclidesInteractivo(): Unit = {
  // Inicio de la zona donde deben editar el código
  def calculo_mcd(a: Int, b: Int): Int =
    if b == 0 then a
    else calculo_mcd(b, a % b)

  var seguir: Boolean = true
  while seguir do
    println("Ingrese el primer numero (escriba 0 para salir)")
    val n1: Int = readInt()
    if n1 == 0 then
      seguir = false
    else
      println("Ingrese el segundo numero")
      val n2: Int = readInt()
      val res: Int = calculo_mcd(n1, n2)
      println(s"El mcd entre  $n1  y  $n2  es  $res")
  // Fin de la zona donde deben editar el código
}
