package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Dado.Dado;
import edu.fiuba.algo3.modelo.Juego.Jugador;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice;
import edu.fiuba.algo3.modelo.Tablero.Hexagono;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Construcciones.*;


import static org.junit.Assert.*;

/**
 * JugadorTest
 */
public class JugadorTest {

    private Jugador jugador;
    private Inventario inventario;
    private Inventario minimoCiudad;
    private Inventario minimoPoblado;

    @BeforeEach
    public void setUp() {
        inventario =  new Inventario(new Madera(), new Ladrillo(),
                new Lana(), new Grano(), new Grano() , new Madera() , new Ladrillo());
        minimoCiudad = new Inventario(new Grano(), new Grano(), new Mineral(), new Mineral(), new Mineral());
        minimoPoblado = new Inventario(new Madera(),
                new Ladrillo(), new Lana(), new Grano());
        jugador = new Jugador("Ale", inventario);

    }

    @Test
    public void test01CreoUnJugadorSinCartasNiConstrucciones() {
        Jugador jugador1 = new Jugador("Ale", new Inventario());
        assertEquals(0, jugador1.cantidadCartas());
        assertEquals(0, jugador1.cantidadConstrucciones());

    }

    @Test
    public void test02UnaConstruccionGeneraUnCartaRecursoParaJugador() {

        Hexagono hexMadera = new Hexagono(Terreno.BOSQUE, 6);
        Vertice vertice = new Vertice();
        vertice.agregarHexagono(hexMadera);

        Construible estrategia = new ConstruirAsentamiento();
        jugador.construir(estrategia, new Poblado(), vertice);

        assertEquals(1, jugador.cantidadConstrucciones());

    }

    @Test
    public void test03UnJugadorRecibeRecursosDeSuConstruccion() {

        Jugador jugador = new Jugador("Julia",  minimoPoblado);

        int dado = 6;
        Hexagono hexMadera = new Hexagono(Terreno.BOSQUE, 6);
        Vertice vertice = new Vertice();
        vertice.agregarHexagono(hexMadera);

        jugador.construir(new ConstruirAsentamiento(), new Poblado(), vertice);

        for (int i = 0; i < 10; i++) {
            jugador.accionSegunDado(new Dado(dado));
        }
        assertEquals(10, jugador.cantidadCartas());

    }

    @Test
    public void test04UnJugadorRecibeDosRecursosDeUnaCuidad() {

        int dado = 6;
        Hexagono hexaPiedra = new Hexagono(Terreno.MONTANA, 6);
        Hexagono hexaLana = new Hexagono(Terreno.PASTIZAL, 6);

        Vertice vertice = new Vertice();
        vertice.agregarHexagono(hexaPiedra);
        vertice.agregarHexagono(hexaLana);


        Jugador jugador = new Jugador("Alberto", minimoCiudad);

        Construible estrategia = new ConstruirAsentamiento();
        jugador.construir(estrategia, new Ciudad(), vertice);

        jugador.generarSegunDado(dado);

        assertEquals(4, jugador.cantidadCartas());

    }

    @Test
    public void test05UnJugadorRecibeTresRecursosDistintos() {
        Jugador jugador = new Jugador("Julia", minimoPoblado);

        int dado1 = 1;
        int dado2 = 2;
        int dado3 = 3;

        Hexagono hexMadera = new Hexagono(Terreno.BOSQUE, 1);
        Hexagono hexaPiedra = new Hexagono(Terreno.MONTANA, 2);
        Hexagono hexaLana = new Hexagono(Terreno.PASTIZAL, 3);

        Vertice vertice = new Vertice();
        vertice.agregarHexagono(hexMadera);
        vertice.agregarHexagono(hexaPiedra);
        vertice.agregarHexagono(hexaLana);

        jugador.construir(new ConstruirAsentamiento(), new Poblado(), vertice);

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

        Jugador jugador = new Jugador("Julia", minimoPoblado);

        int dado1 = 1;
        int dado2 = 2;
        int dado3 = 3;
        int dado7 = 7;

        Hexagono hexMadera = new Hexagono(Terreno.BOSQUE, 1);
        Hexagono hexaPiedra = new Hexagono(Terreno.MONTANA, 2);
        Hexagono hexaLana = new Hexagono(Terreno.PASTIZAL, 3);

        Vertice vertice = new Vertice();
        vertice.agregarHexagono(hexMadera);
        vertice.agregarHexagono(hexaPiedra);
        vertice.agregarHexagono(hexaLana);

        Construible estrategia = new ConstruirAsentamiento();
        jugador.construir(estrategia, new Poblado(), vertice);

        for (int i = 0; i < 3; i++) {
            jugador.generarSegunDado(dado1);
        }
        for (int i = 0; i < 3; i++) {
            jugador.generarSegunDado(dado2);
        }
        for (int i = 0; i < 3; i++) {
            jugador.generarSegunDado(dado3);
        }

        jugador.generarSegunDado(dado7);

        assertEquals(5, jugador.cantidadCartas());
    }

}
