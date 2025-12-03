package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Recurso.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

    private Jugador jugadorPoblado;
    private Jugador jugadorCiudad;
    private Inventario inventarioPoblado;
    private Inventario inventarioCiudad;

    @BeforeEach
    public void setUp() {
        inventarioPoblado = new Inventario(
                new Madera(), new Ladrillo(), new Grano(), new Lana());
        jugadorPoblado = new Jugador("Poblado", inventarioPoblado);

        inventarioCiudad = new Inventario(
                new Mineral(), new Mineral(), new Mineral(), new Grano(), new Grano());
        jugadorCiudad = new Jugador("Ciudad", inventarioCiudad);
    }

    @Test
    public void test01CreoUnJugadorSinCartasNiConstrucciones() {
        Jugador jugador1 = new Jugador("Vacio", new Inventario());

        assertEquals(0, jugador1.cantidadCartas());
        assertEquals(0, jugador1.cantidadConstrucciones());

    }

    @Test
    public void test02UnaConstruccionGeneraUnCartaRecursoParaJugador() {

        Hexagono hexMadera = new Hexagono(Terreno.BOSQUE, 6);
        Vertice vertice = new Vertice();
        vertice.asignarHexagonos(hexMadera);

        jugadorPoblado.construir(vertice, new Poblado());

        assertEquals(1, jugadorPoblado.cantidadConstrucciones());

    }

    @Test
    public void test03UnJugadorRecibeRecursosDeSuConstruccion() {

        int dado = 6;
        Hexagono hexMadera = new Hexagono(Terreno.BOSQUE, 6);
        Vertice vertice = new Vertice();
        vertice.asignarHexagonos(hexMadera);

        jugadorPoblado.construir(vertice, new Poblado());

        for (int i = 0; i < 10; i++) {
            jugadorPoblado.generarSegunDado(dado);
        }
        assertEquals(10, jugadorPoblado.cantidadCartas());

    }

    @Test
    public void test04UnJugadorRecibeDosRecursosDeUnaCuidad() {

        int dado = 6;
        Hexagono hexaPiedra = new Hexagono(Terreno.MONTANA, 6);
        Hexagono hexaLana = new Hexagono(Terreno.PASTIZAL, 6);

        Vertice vertice = new Vertice();
        vertice.asignarHexagonos(hexaPiedra);
        vertice.asignarHexagonos(hexaLana);

        jugadorCiudad.construir(vertice, new Ciudad(jugadorCiudad));

        jugadorCiudad.generarSegunDado(dado);

        assertEquals(4, jugadorCiudad.cantidadCartas());

    }

    @Test
    public void test05UnJugadorRecibeTresRecursosDistintos() {
        Jugador jugador = new Jugador("Julia", inventarioPoblado);

        int dado1 = 1;
        int dado2 = 2;
        int dado3 = 3;

        Hexagono hexMadera = new Hexagono(Terreno.BOSQUE, 1);
        Hexagono hexaPiedra = new Hexagono(Terreno.MONTANA, 2);
        Hexagono hexaLana = new Hexagono(Terreno.PASTIZAL, 3);

        Vertice vertice = new Vertice();
        vertice.asignarHexagonos(hexMadera);
        vertice.asignarHexagonos(hexaPiedra);
        vertice.asignarHexagonos(hexaLana);

        jugador.construir(vertice, new Poblado());

        for (int i = 0; i < 3; i++) {
            jugador.generarSegunDado(dado1);
        }
        for (int i = 0; i < 3; i++) {
            jugador.generarSegunDado(dado2);
        }
        for (int i = 0; i < 3; i++) {
            jugador.generarSegunDado(dado3);
        }

        assertEquals(9, jugador.cantidadCartas());

    }

    @Test
    public void test06UnJugadorDescartaLaMitadDeSusCartasSiSale7yTieneMasDe7Cartas() {

        Jugador jugador1 = new Jugador("Julia", inventarioPoblado);

        int dado1 = 1;
        int dado2 = 2;
        int dado3 = 3;
        int dado7 = 7;

        Hexagono hexMadera = new Hexagono(Terreno.BOSQUE, 1);
        Hexagono hexaPiedra = new Hexagono(Terreno.MONTANA, 2);
        Hexagono hexaLana = new Hexagono(Terreno.PASTIZAL, 3);

        Vertice vertice = new Vertice();
        vertice.asignarHexagonos(hexMadera);
        vertice.asignarHexagonos(hexaPiedra);
        vertice.asignarHexagonos(hexaLana);

        jugador1.construir(vertice, new Poblado());

        for (int i = 0; i < 3; i++) {
            jugador1.generarSegunDado(dado1);
        }
        for (int i = 0; i < 3; i++) {
            jugador1.generarSegunDado(dado2);
        }
        for (int i = 0; i < 3; i++) {
            jugador1.generarSegunDado(dado3);
        }

        jugador1.generarSegunDado(dado7);

        assertEquals(5, jugador1.cantidadCartas());
    }

}
