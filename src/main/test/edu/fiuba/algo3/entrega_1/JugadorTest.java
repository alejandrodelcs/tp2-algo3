package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Jugador.Inventario;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Vertice;
import edu.fiuba.algo3.modelo.Tablero.Hexagono;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Construccion.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JugadorTest
 */
public class JugadorTest {

    private Jugador jugador;
    private Inventario minimoCiudad;
    private Inventario minimoPoblado;
    private Tablero tablero;

    @BeforeEach
    public void setUp() {
        Inventario inventario = new Inventario(new Madera(), new Ladrillo(),
                new Lana(), new Grano(), new Grano(), new Madera(), new Ladrillo());
        minimoCiudad = new Inventario(new Grano(), new Grano(), new Mineral(), new Mineral(), new Mineral());
        minimoPoblado = new Inventario(new Madera(),
                new Ladrillo(), new Lana(), new Grano());
        jugador = new Jugador("Ale", inventario);
        tablero = new Tablero();

    }

    @Test
    public void test01CreoUnJugadorSinCartasNiConstrucciones() {
        Jugador jugador1 = new Jugador("Ale", new Inventario());
        assertEquals(0, jugador1.cantidadCartas());
        assertEquals(0, jugador1.cantidadConstrucciones());

    }

    @Test
    public void test02UnaConstruccionGeneraUnCartaRecursoParaJugador() {

        Hexagono hexMadera = new Hexagono(new Madera(), 6);
        Vertice vertice = new Vertice();
        vertice.agregarHexagono(hexMadera);

        jugador.construir(new Poblado(), vertice);

        assertEquals(1, jugador.cantidadConstrucciones());

    }

    @Test
    public void test03UnJugadorRecibeRecursosDeSuConstruccion() {
        Jugador jugador = new Jugador("Julia", minimoPoblado);
        int dado = 6;
        Hexagono hexMadera = new Hexagono(new Madera(), 6);

        tablero.agregarHexagono(hexMadera);
        Vertice vertice = new Vertice();
        vertice.agregarHexagono(hexMadera);

        jugador.construir(new Poblado(), vertice);

        for (int i = 0; i < 10; i++) {
            tablero.producirRecursosSegun(6);
        }
        assertEquals(10, jugador.cantidadCartas());

    }

    @Test
    public void test04UnJugadorRecibeDosRecursosDeUnaCuidad() {

        int dado = 6;
        Hexagono hexaPiedra = new Hexagono(new Mineral(), 6);
        Hexagono hexaLana = new Hexagono(new Grano(), 6);

        tablero.agregarHexagono(hexaPiedra);
        tablero.agregarHexagono(hexaLana);


        Vertice vertice = new Vertice();
        vertice.agregarHexagono(hexaPiedra);
        vertice.agregarHexagono(hexaLana);

        Jugador jugador = new Jugador("Alberto", minimoCiudad);

        jugador.construir(new Ciudad(), vertice);

        tablero.producirRecursosSegun(dado);

        assertEquals(4, jugador.cantidadCartas());

    }

    @Test
    public void test05UnJugadorRecibeTresRecursosDistintos() {
        Jugador jugador = new Jugador("Julia", minimoPoblado);

        int dado1 = 1;
        int dado2 = 2;
        int dado3 = 3;

        Hexagono hexMadera = new Hexagono(new Madera(), 1);
        Hexagono hexaPiedra = new Hexagono(new Mineral(), 2);
        Hexagono hexaLana = new Hexagono(new Grano(), 3);

        tablero.agregarHexagono(hexMadera);
        tablero.agregarHexagono(hexaPiedra);
        tablero.agregarHexagono(hexaLana);

        Vertice vertice = new Vertice();
        vertice.agregarHexagono(hexMadera);
        vertice.agregarHexagono(hexaPiedra);
        vertice.agregarHexagono(hexaLana);

        jugador.construir(new Poblado(), vertice);

        for (int i = 0; i < 3; i++) {
            tablero.producirRecursosSegun(dado1);
        }
        for (int i = 0; i < 3; i++) {
            tablero.producirRecursosSegun(dado2);
        }
        for (int i = 0; i < 3; i++) {
            tablero.producirRecursosSegun(dado3);
        }

        assertEquals(9, jugador.cantidadCartas());

    }

    @Test
    public void test06UnJugadorDescartaLaMitadDeSusCartasSiSale7yTieneMasDe7Cartas() {

        Jugador jugador = new Jugador("Julia", new Inventario());
        jugador.recibirRecurso(new Madera());
        jugador.recibirRecurso(new Madera());
        jugador.recibirRecurso(new Madera());
        jugador.recibirRecurso(new Madera());
        jugador.recibirRecurso(new Madera());
        jugador.recibirRecurso(new Madera());
        jugador.recibirRecurso(new Madera());
        jugador.recibirRecurso(new Madera());

        jugador.descartarMitadSiCorresponde();

        assertEquals(4, jugador.cantidadCartas());
    }

}
