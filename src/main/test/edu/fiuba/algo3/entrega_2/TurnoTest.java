package edu.fiuba.algo3.entrega_2;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
import edu.fiuba.algo3.modelo.Turno.EstadoSegundaVuelta;
import edu.fiuba.algo3.modelo.Turno.Turno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.tngtech.archunit.thirdparty.com.google.common.collect.Table;

/**
 * TurnoTest
 */
public class TurnoTest {

    private Jugador jugadorMock;
    private Tablero tableroMock;
    private Turno turno;
    private Dado dadoMock;
    private Juego juegoMock;

    @BeforeEach
    public void setUp() {
        jugadorMock = mock(Jugador.class);
        tableroMock = mock(Tablero.class);
        dadoMock = mock(Dado.class);
        juegoMock = mock(Juego.class);

        turno = new Turno(jugadorMock, tableroMock, dadoMock);
    }

    @Test
    public void test01EstadoInicialNoPermiteConstruirNiPasarSinTirarDados() {
        assertThrows(AccionNoPermitidaException.class, () -> 
            turno.construir(mock(Poblado.class), mock(Vertice.class))
        );

        assertThrows(AccionNoPermitidaException.class, () -> 
            turno.pasarTurno(juegoMock)
        );

        assertThrows(AccionNoPermitidaException.class, () -> 
            turno.moverLadronA(mock(Hexagono.class))
        );
    }

    @Test
    public void test02TirarDadosTransicionaAEstadoAccionesSiNoEsSiete() {
        when(dadoMock.lanzar()).thenReturn(5);

        turno.tirarDado(juegoMock);

        assertDoesNotThrow(() -> turno.pasarTurno(juegoMock));
        
        verify(juegoMock).resolverTirada(5);
    }

    @Test
    public void test03SacarSieteActivaSecuenciaDelLadron() {
        when(dadoMock.lanzar()).thenReturn(7);
        turno.tirarDado(juegoMock);

        verify(juegoMock).numeroDado(7);

        assertThrows(AccionNoPermitidaException.class, () -> turno.pasarTurno(juegoMock));
        
        Hexagono destino = mock(Hexagono.class);
        turno.moverLadronA(destino);
        verify(tableroMock).moverLadronA(destino);

        assertThrows(AccionNoPermitidaException.class, () -> turno.construir(mock(Poblado.class)));

        Jugador victima = mock(Jugador.class);
        turno.robar(victima);
        verify(tableroMock).robarConLadronA(jugadorMock, victima);

        turno.pasarTurno(juegoMock);
        verify(juegoMock).finalizarTurnoActual();
    }

    @Test
    public void test04EstadoAccionesNoPermiteTirarDadosDeNuevo() {
        when(dadoMock.lanzar()).thenReturn(4);
        turno.tirarDado(juegoMock);

        assertThrows(AccionNoPermitidaException.class, () -> 
            turno.tirarDado(juegoMock)
        );
    }

    @Test
    public void test05PasarTurnoHabilitaCartasYFinalizaEnJuego() {
        when(dadoMock.lanzar()).thenReturn(4);
        turno.tirarDado(juegoMock);

        turno.pasarTurno(juegoMock);

        verify(jugadorMock).habilitarCartasDesarrollo();
        verify(juegoMock).finalizarTurnoActual();
    }

    @Test
    public void test06GanarElJuegoBloqueaTodo() {
        when(dadoMock.lanzar()).thenReturn(4);
        turno.tirarDado(juegoMock);

        when(jugadorMock.getPuntosVictoria()).thenReturn(10);

        turno.construir(mock(Poblado.class), mock(Vertice.class));

        assertThrows(AccionNoPermitidaException.class, () -> turno.tirarDado(juegoMock));
        assertThrows(AccionNoPermitidaException.class, () -> turno.pasarTurno(juegoMock));
        assertThrows(AccionNoPermitidaException.class, () -> turno.construir(mock(Poblado.class)));
        
        try {
            turno.pasarTurno(juegoMock);
        } catch (AccionNoPermitidaException e) {
            assertEquals("El juego ha terminado.", e.getMessage());
        }
    }

    @Test
    public void test07SegundaVueltaEntregaRecursosDelPoblado() {
        turno.cambiarEstado(new EstadoSegundaVuelta());

        Poblado poblado = new Poblado();
        Vertice verticeMock = mock(Vertice.class);

        turno.construir(poblado, verticeMock);

        verify(jugadorMock).construir(eq(poblado), eq(verticeMock));
        verify(jugadorMock).entregaInicial(verticeMock);
    }

    @Test
    public void test08PrimeraVueltaConstruyeGratisPeroNoDaRecursos() {
        turno.cambiarEstado(new EstadoPrimeraVuelta());
        
        Poblado poblado = new Poblado();
        Vertice verticeMock = mock(Vertice.class);
        
        turno.construir(poblado, verticeMock);
        
        verify(jugadorMock).construir(eq(poblado), eq(verticeMock));
        verify(jugadorMock, never()).entregaInicial(any());
    }


    @Test
    public void test09PrimeraVueltaPermiteConstruirAUnJugadorGratisUnPobladoYUnaCarretera() {
        Construccion poblado = new Poblado();
        Vertice vertice = new Vertice();
        Construccion carretera = new Carretera(new ReglaCostoConstruccion());
        Arista arista = new Arista(vertice, vertice);

        turno.cambiarEstado(new EstadoPrimeraVuelta());

        turno.construir(poblado, vertice);
        turno.construir(carretera, arista);
        
        verify(jugadorMock, times(1)).construir(eq(poblado), eq(vertice));
        verify(jugadorMock, times(1)).construir(eq(carretera), eq(arista));
    }

    @Test
    public void test10AlTirarElDadoSeNotificaALaClaseJuego() {
        Dado dadoMock = mock(Dado.class);
        when(dadoMock.lanzar()).thenReturn(2);

        Turno turno = new Turno(jugadorMock, new Tablero(), dadoMock);

        turno.tirarDado(juegoMock);

        verify(juegoMock).resolverTirada(2);
    }

    @Test
    public void test11NoSePuedeConstruirAntesDeTirarElDado() {
        Turno turno = new Turno(jugadorMock, tableroMock, mock(Dado.class));
        Vertice v = new Vertice();

        assertThrows(AccionNoPermitidaException.class, () -> turno.construir(new Poblado(), v));
    }
}
