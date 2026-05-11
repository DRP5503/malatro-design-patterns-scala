package cl.uchile.dcc

import cl.uchile.dcc.modelo.cartas.*
import cl.uchile.dcc.logica.Evaluador
import cl.uchile.dcc.modelo.Jugada
import munit.FunSuite

class EvaluadorTest extends FunSuite {
  // Tests de jugadas básicas
  test("Escalera de Color es identificada correctamente") {
    val cartas = List(
      new Carta(new Dos, new Diamante),
      new Carta(new Tres, new Diamante),
      new Carta(new Cuatro, new Diamante),
      new Carta(new Cinco, new Diamante),
      new Carta(new Seis, new Diamante)
    )
    assertEquals(Evaluador.identificarJugada(cartas), Jugada.EscaleraColor)
  }

  test("Color es identificado correctamente") {
    val cartas = List(
      new Carta(new Dos, new Diamante),
      new Carta(new Cinco, new Diamante),
      new Carta(new Siete, new Diamante),
      new Carta(new Nueve, new Diamante),
      new Carta(new Kaiser, new Diamante)
    )
    assertEquals(Evaluador.identificarJugada(cartas), Jugada.Color)
  }

  test("Escalera es identificada correctamente") {
    val cartas = List(
      new Carta(new Dos, new Corazon),
      new Carta(new Tres, new Diamante),
      new Carta(new Cuatro, new Trebol),
      new Carta(new Cinco, new Pica),
      new Carta(new Seis, new Corazon)
    )
    assertEquals(Evaluador.identificarJugada(cartas), Jugada.Escalera)
  }

  test("Trio es identificado correctamente") {
    val cartas = List(
      new Carta(new Dos, new Corazon),
      new Carta(new Dos, new Diamante),
      new Carta(new Dos, new Trebol)
    )
    assertEquals(Evaluador.identificarJugada(cartas), Jugada.Trio)
  }

  test("Par es identificado correctamente") {
    val cartas = List(
      new Carta(new Dos, new Corazon),
      new Carta(new Dos, new Diamante)
    )
    assertEquals(Evaluador.identificarJugada(cartas), Jugada.Par)
  }

  test("Carta Alta es identificada correctamente") {
    val cartas = List(
      new Carta(new Dos, new Corazon),
      new Carta(new Cinco, new Diamante),
      new Carta(new Kaiser, new Trebol)
    )
    assertEquals(Evaluador.identificarJugada(cartas), Jugada.CartaAlta)
  }

  // Tests de prioridad
  test("Escalera de Color tiene prioridad sobre Color") {
    val cartas = List(
      new Carta(new Dos, new Diamante),
      new Carta(new Tres, new Diamante),
      new Carta(new Cuatro, new Diamante),
      new Carta(new Cinco, new Diamante),
      new Carta(new Seis, new Diamante)
    )
    assertEquals(Evaluador.identificarJugada(cartas), Jugada.EscaleraColor)
  }

  test("Escalera de Color tiene prioridad sobre Escalera") {
    val cartas = List(
      new Carta(new Dos, new Diamante),
      new Carta(new Tres, new Diamante),
      new Carta(new Cuatro, new Diamante),
      new Carta(new Cinco, new Diamante),
      new Carta(new Seis, new Diamante)
    )
    assertEquals(Evaluador.identificarJugada(cartas), Jugada.EscaleraColor)
  }

  // Tests de As en escaleras
  test("As actua como carta baja en escalera A,2,3,4,5") {
    val cartas = List(
      new Carta(new As, new Corazon),
      new Carta(new Dos, new Diamante),
      new Carta(new Tres, new Trebol),
      new Carta(new Cuatro, new Pica),
      new Carta(new Cinco, new Corazon)
    )
    assertEquals(Evaluador.identificarJugada(cartas), Jugada.Escalera)
  }

  test("As actua como carta alta en escalera 10,J,Q,K,A") {
    val cartas = List(
      new Carta(new Diez, new Corazon),
      new Carta(new Jota, new Diamante),
      new Carta(new Quina, new Trebol),
      new Carta(new Kaiser, new Pica),
      new Carta(new As, new Corazon)
    )
    assertEquals(Evaluador.identificarJugada(cartas), Jugada.Escalera)
  }

  test("As actua como carta alta en escalera de color 10,J,Q,K,A") {
    val cartas = List(
      new Carta(new Diez, new Corazon),
      new Carta(new Jota, new Corazon),
      new Carta(new Quina, new Corazon),
      new Carta(new Kaiser, new Corazon),
      new Carta(new As, new Corazon)
    )
    assertEquals(Evaluador.identificarJugada(cartas), Jugada.EscaleraColor)
  }
}