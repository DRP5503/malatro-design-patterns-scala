package cl.uchile.dcc
import cl.uchile.dcc.model.*
import munit.FunSuite

class PuntajeTest extends FunSuite {

  test("Two puntajes iguales deben ser iguales") {
    val p1 = new Score(100, 2.0f)
    val p2 = new Score(100, 2.0f)

    assert(p1.isEqual(p2))
  }

  test("puntajes con distinto chips no son iguales") {
    val p1 = new Score(100, 2.0f)
    val p2 = new Score(200, 2.0f)

    assert(!p1.isEqual(p2))
  }

  test("puntajes con distinto multiplicador no son iguales") {
    val p1 = new Score(100, 2.0f)
    val p2 = new Score(100, 3.0f)

    assert(!p1.isEqual(p2))
  }

  test("puntajes completamente distintos no son iguales") {
    val p1 = new Score(100, 2.0f)
    val p2 = new Score(50, 1.5f)

    assert(!p1.isEqual(p2))
  }
}
