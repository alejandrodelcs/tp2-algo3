package edu.fiuba.algo3.entrega_2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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
import edu.fiuba.algo3.modelo.Turno.Turno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * TurnoTest
 */
public class TurnoTest {

    private Jugador jugador;
    private Tablero tablero;

    @BeforeEach
    public void setUp() {
        jugador = new Jugador("Hernesto", new Inventario(new Ladrillo(), new Madera(), new Lana(),
                new Grano(), new Mineral(), new Madera()));
        tablero = new Tablero();

    }

    @Test
    public void test02AlTirarElDadoSeNotificaALaClaseJuego() {
        Dado dadoMock = mock(Dado.class);
        when(dadoMock.lanzar()).thenReturn(2);

        Juego juegoMock = mock(Juego.class);

        Turno turno = new Turno(jugador, new Tablero(), dadoMock);

        turno.tirarDado(juegoMock);

        verify(juegoMock).resolverTirada(2);
    }

    @Test
    public void test03NoSePuedeConstruirAntesDeTirarElDado() {
        Turno turno = new Turno(jugador, tablero, mock(Dado.class));
        Vertice v = new Vertice();

        assertThrows(AccionNoPermitidaException.class, () ->
                turno.construir(new Poblado(), v)
        );
    }



}
