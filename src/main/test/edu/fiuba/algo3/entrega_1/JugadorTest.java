package edu.fiuba.algo3.entrega_1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Construcciones.*;
import edu.fiuba.algo3.modelo.ElementosTablero.*;

/**
 * JugadorTest
 */
public class JugadorTest {
    @Test
    public void test01CreoUnJugadorSinCartasNiConstrucciones() {
        Jugador jugador = new Jugador("Adrian");

        assertEquals(0, jugador.cantidadCartas());
        assertEquals(0, jugador.cantidadConstrucciones());

    }

    @Test
    public void test02UnaConstruccionGeneraUnCartaRecursoParaJugador() {
        Jugador jugador1 = new Jugador("Alberto");

        Hexagono hexMadera = new Hexagono(Terreno.BOSQUE, 6);
        Vertice vertice = new Vertice();
        vertice.asignarHexagonos(hexMadera);

        jugador1.construir(vertice, new Poblado());

        assertEquals(1, jugador1.cantidadConstrucciones());

    }

    @Test
    public void test03UnJugadorRecibeRecursosDeSuConstruccion() {

        Jugador jugador = new Jugador("Julia");

        int dado = 6;
        Hexagono hexMadera = new Hexagono(Terreno.BOSQUE, 6);
        Vertice vertice = new Vertice();
        vertice.asignarHexagonos(hexMadera);

        jugador.construir(vertice, new Poblado());

        for (int i = 0; i > 10; i++) {
            jugador.generarSegunDado(dado);
        }
        assertEquals(10, jugador.cantidadCartas());

    }

}
