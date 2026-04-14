package cl.uchile.dcc
import cl.uchile.dcc.modelo.*
import munit.FunSuite

class PintaTest extends FunSuite {

  test("Corazon tiene el nombre correcto") {
    val corazon = new Corazon()

    assert(corazon.tipo == "Corazon")
  }

  test("Pica tiene el nombre correcto") {
    val pica = new Pica()

    assert(pica.tipo == "Pica")
  }

  test("Diamante tiene el nombre correcto") {
    val diamante = new Diamante()

    assert(diamante.tipo == "Diamante")
  }

  test("Trebol tiene el nombre correcto") {
    val trebol = new Trebol()

    assert(trebol.tipo == "Trebol")
  }
  test("pintas distintas tienen nombres distintos") {
    val corazon = new Corazon()
    val pica = new Pica()

    assert(corazon.tipo != pica.tipo)
  }
}
