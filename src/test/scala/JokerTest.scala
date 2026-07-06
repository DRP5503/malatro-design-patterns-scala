package cl.uchile.dcc
import cl.uchile.dcc.model.*
import cl.uchile.dcc.model.cards.{Devious, EvenSteven, Greedy, ScaryFace}
import munit.FunSuite

class JokerTest extends FunSuite {

  test("los jokers son diferenciables entre si") {
    val greedy = new Greedy()
    val devious = new Devious()
    val evenSteven = new EvenSteven()
    val scaryFace = new ScaryFace()

    // Verificar que no son iguales
    assert(greedy != devious)
    assert(greedy != evenSteven)
    assert(greedy != scaryFace)
    assert(devious != evenSteven)
    assert(devious != scaryFace)
    assert(evenSteven != scaryFace)


  }
}