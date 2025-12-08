package edu.fiuba.algo3.entrega_2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import edu.fiuba.algo3.modelo.Construccion.*;
import edu.fiuba.algo3.modelo.Dado.Dado;
import edu.fiuba.algo3.modelo.Jugador.Inventario;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.*;
import edu.fiuba.algo3.modelo.Turno.EstadoAcciones;
import edu.fiuba.algo3.modelo.Turno.Turno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * TurnoTest
 */
public class TurnoTest {

    private Jugador jugador;
    private Jugador victima;
    private Tablero tablero;

    @BeforeEach
    public void setUp() {
        jugador = new Jugador("Hernesto", new Inventario(new Ladrillo(), new Madera(), new Lana(),
                new Mineral(), new Grano(), new Madera()));
        victima = new Jugador("Pedro", new Inventario(new Ladrillo(), new Madera(), new Lana(),
                new Mineral(), new Grano(), new Madera(), new Ladrillo()));

        tablero = new Tablero();

    }

    @Test
    public void test01LadronRobaUnRecursoSeEsperaLaMitadDeL() {

        Turno turno = new Turno(jugador, tablero);

        assertEquals(jugador, turno.jugador());
    }

    @Test
    public void test02JugadorConstruyePobladoSiTieneRecursos() {
        Turno turno = new Turno(jugador, new Tablero());
        Vertice v = new Vertice();
        turno.tirarDado(new Dado());
        turno.contruir(new ConstruirAsentamiento(), new Poblado(), v);
        assertTrue(v.tieneConstruccion());
    }

    @Test
    public void test03JugadorContruyeCarreteaSiTieneRecursos() {
        Turno turno = new Turno(jugador, new Tablero());
        Vertice v1 = new Vertice();
        Vertice v2 = new Vertice();
        Arista a = new Arista(v1, v2);
        turno.cambiarEstado(new EstadoAcciones());
        turno.contruir(new ConstruirCarretera(), new Carretera(), a);
        assertEquals(4, jugador.consultarRecursos());
    }

    @Test
    public void test04MoverLadronLuegoDeTirarSiete() {

        Dado dadoMock = mock(Dado.class);
        when(dadoMock.lanzar()).thenReturn(7);

        Tablero tablero = new Tablero();

        Hexagono origen = new Hexagono(Terreno.DESIERTO, -1);
        tablero.agregarHexagono(origen);
        tablero.colocarLadronEn(origen);

        Hexagono destino = new Hexagono(Terreno.BOSQUE, 6);
        tablero.agregarHexagono(destino);

        Vertice v = new Vertice();
        v.construir(new Poblado(victima));

        destino.agregarVertice(v);

        Turno turno = new Turno(jugador, tablero);

        turno.tirarDado(dadoMock);

        turno.moverLadronA(destino);

        turno.robar();

        assertEquals(7, jugador.cantidadCartas());
        assertEquals(6, victima.cantidadCartas());

    }

}
