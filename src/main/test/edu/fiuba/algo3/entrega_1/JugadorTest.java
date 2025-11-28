package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Recurso.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Construcciones.*;
import edu.fiuba.algo3.modelo.ElementosTablero.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

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

        jugador1.construir(vertice, new Poblado(new Jugador("Test")));

        assertEquals(1, jugador1.cantidadConstrucciones());

    }

    @Test
    public void test03UnJugadorRecibeRecursosDeSuConstruccion() {

        Jugador jugador = new Jugador("Julia");

        int dado = 6;
        Hexagono hexMadera = new Hexagono(Terreno.BOSQUE, 6);
        Vertice vertice = new Vertice();
        vertice.asignarHexagonos(hexMadera);

        jugador.construir(vertice, new Poblado(new Jugador("Test")));

        for (int i = 0; i < 10; i++) {
            jugador.generarSegunDado(dado);
        }
        assertEquals(10, jugador.cantidadCartas());

    }

    @Test
    public void test04UnJugadorRecibeDosRecursosDeUnCuidad() {
        Jugador jugador = new Jugador("Alberto");
        int dado = 6;
        Hexagono hexaPiedra = new Hexagono(Terreno.MONTANA, 6);
        Hexagono hexaLana = new Hexagono(Terreno.PASTIZAL, 6);

        Vertice vertice = new Vertice();
        vertice.asignarHexagonos(hexaPiedra);
        vertice.asignarHexagonos(hexaLana);

        jugador.construir(vertice, new Ciudad(new Jugador("Test")));

        jugador.generarSegunDado(dado);

        assertEquals(4, jugador.cantidadCartas());

    }
}
