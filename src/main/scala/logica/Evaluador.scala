package cl.uchile.dcc
package logica
import modelo.cartas.Carta

import cl.uchile.dcc.modelo.Jugada
import cl.uchile.dcc.modelo.Jugada.{CartaAlta, Color, Escalera, EscaleraColor, Par, Trio}

//el proposito principal de evaluador es indentificar la jugada
//y calcular el puntaje correspondiente
object Evaluador {
    def identificarJugada(cartas: List[Carta]): Jugada= {
        if (esEscaleraColor(cartas)) EscaleraColor
        else if (esColor(cartas)) Color
        else if (esEscalera(cartas)) Escalera
        else if (esTrio(cartas)) Trio
        else if (esPar(cartas)) Par
        else CartaAlta
    }

    private def ordenarRangos(cartas: List[Carta]): List[Carta]=
        cartas.sortBy(_.rango.orden)

    private def frecuenciaRangos(cartas: List[Carta]): Map[Int, Int]=
        cartas.groupBy(_.rango.orden).map {case (orden, lista) => (orden, lista.length)}

    private def esEscaleraColor(cartas: List[Carta]): Boolean={
        esColor(cartas) && esEscalera(cartas)
    }

    private def esColor(cartas: List[Carta]): Boolean={
        val primeraPinta = cartas.head.pinta
        cartas.forall(_.pinta == primeraPinta)
    }

    private def esEscalera(cartas: List[Carta]): Boolean = {
        // Caso normal
        val ordenes = cartas.map(_.rango.orden)
        if (esEscaleraAux(ordenes)) return true

        // Caso As alto
        val tieneAs = cartas.exists(c => c.rango.ordenes.length > 1)
        if (tieneAs) {
            val ordenesAsAlto = cartas.map(c =>
                if (c.rango.ordenes.length > 1) 14 else c.rango.orden
            )
            return esEscaleraAux(ordenesAsAlto)
        }

        false
    }

    private def esEscaleraAux(ordenes: List[Int]): Boolean = {
        val ordenadas = ordenes.sorted
        for (i <- 0 until ordenadas.length - 1) {
            if (ordenadas(i + 1) - ordenadas(i) != 1)
                return false
        }
        true
    }

    private def esTrio(cartas: List[Carta]): Boolean={
        val frecuencias = frecuenciaRangos(cartas)
        frecuencias.exists {case (_, frecuencia) => frecuencia == 3}
    }

    private def esPar(cartas: List[Carta]): Boolean=
        val frecuencias = frecuenciaRangos(cartas)
        frecuencias.exists { case (_, frecuencia) => frecuencia == 2 }
}
