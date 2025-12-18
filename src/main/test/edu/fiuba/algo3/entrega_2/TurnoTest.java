package edu.fiuba.algo3.entrega_2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.Comercio.Comercio;
import edu.fiuba.algo3.modelo.Construccion.*;
import edu.fiuba.algo3.modelo.Costo.ReglaCostoConstruccion;
import edu.fiuba.algo3.modelo.Dado.Dado;
import edu.fiuba.algo3.modelo.Excepciones.AccionNoPermitidaException;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Inventario;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.*;
import edu.fiuba.algo3.modelo.Turno.EstadoAcciones;
import edu.fiuba.algo3.modelo.Turno.EstadoPrimeraVuelta;
import edu.fiuba.algo3.modelo.Turno.Turno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * TurnoTest
 */
public class TurnoTest {

    private Jugador jugador1, jugador2;
    private Tablero tablero;
    private Turno turno;
    private Dado dadoMock;

    @BeforeEach
    public void setUp() {
        jugador1 = new Jugador("6-recursos", new Inventario(new Ladrillo(), new Madera(), new Lana(),
                new Grano(), new Mineral(), new Madera()));

        jugador2 = new Jugador("3-recursos", new Inventario(new Ladrillo(), new Madera(), new Lana(),
                new Grano(), new Mineral(), new Madera()));

        tablero = new Tablero();

        dadoMock = mock(Dado.class);
        when(dadoMock.lanzar()).thenReturn(2);

        turno = new Turno(jugador1, tablero, dadoMock);
    }

    @Test
    public void test01PrimeraVueltaPermiteConstruirAUnJugadorGratisUnPobladoYUnaCarretera() {
        Construccion poblado = new Poblado();
        Vertice vertice = new Vertice();
        Construccion carretera = new Carretera(new ReglaCostoConstruccion());
        Arista arista = new Arista(vertice, vertice);

        turno.cambiarEstado(new EstadoPrimeraVuelta());

        turno.construir(poblado, vertice);
        turno.construir(carretera, arista);
        assertEquals(6, jugador1.cantidadCartas());
        assertEquals(2, jugador1.cantidadConstrucciones());
    }

    @Test
    public void test02AlTirarElDadoSeNotificaALaClaseJuego() {
        Dado dadoMock = mock(Dado.class);
        when(dadoMock.lanzar()).thenReturn(2);

        Juego juegoMock = mock(Juego.class);

        Turno turno = new Turno(jugador1, new Tablero(), dadoMock);

        turno.tirarDado(juegoMock);

        verify(juegoMock).resolverTirada(2);
    }

    @Test
    public void test03NoSePuedeConstruirAntesDeTirarElDado() {
        Turno turno = new Turno(jugador1, tablero, mock(Dado.class));
        Vertice v = new Vertice();

        assertThrows(AccionNoPermitidaException.class, () -> turno.construir(new Poblado(), v));
    }

    @Test
    public void test04UnaPrimeraVueltaPermiteConstruirAdosJugadoresAntesDePasarDeTurno() {

    }

}
