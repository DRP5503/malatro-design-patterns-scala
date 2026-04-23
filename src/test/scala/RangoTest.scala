package cl.uchile.dcc
import cl.uchile.dcc.modelo.*
import cl.uchile.dcc.modelo.cartas.{As, Dos, Jota, Kaiser, Quina, Tres}
import munit.FunSuite

class RangoTest extends FunSuite {

  test("dos As deben ser iguales") {
    val r1 = new As()
    val r2 = new As()

    assert(r1.esIgual(r2))
  }

  test("As y Dos no deben ser iguales") {
    val r1 = new As()
    val r2 = new Dos()

    assert(!r1.esIgual(r2))
  }

  test("Dos tiene orden, valor y clasificacion correctos") {
    val dos = new Dos()

    assert(dos.orden == 2)
    assert(dos.valor == 2)
    assert(dos.clasificacion.nombre == "Par")
  }

  test("Tres es impar") {
    val tres = new Tres()

    assert(tres.clasificacion.nombre == "Impar")
  }

  test("Jota es figura y vale 10") {
    val jota = new Jota()

    assert(jota.orden == 11)
    assert(jota.valor == 10)
    assert(jota.clasificacion.nombre == "Figura")
  }

  test("Quina es figura y vale 10") {
    val quina = new Quina()

    assert(quina.orden == 12)
    assert(quina.valor == 10)
    assert(quina.clasificacion.nombre == "Figura")
  }

  test("Kaiser es figura y vale 10") {
    val kaiser = new Kaiser()

    assert(kaiser.orden == 13)
    assert(kaiser.valor == 10)
    assert(kaiser.clasificacion.nombre == "Figura")
  }

  test("Quina y Kaiser no son iguales") {
    val quina = new Quina()
    val kaiser = new Kaiser()

    assert(!quina.esIgual(kaiser))
  }
}