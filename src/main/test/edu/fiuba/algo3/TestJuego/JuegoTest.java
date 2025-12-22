package edu.fiuba.algo3.TestJuego;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Excepciones.AccionNoPermitidaException;
import edu.fiuba.algo3.modelo.Excepciones.JugadoresMinimosRegistradosError;
import edu.fiuba.algo3.modelo.Jugador.Inventario;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Hexagono;
import edu.fiuba.algo3.modelo.Turno.EstadoAcciones;
import edu.fiuba.algo3.modelo.Turno.Turno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * JuegoTest
 */
public class JuegoTest {

    private Juego juego;

    @BeforeEach
    public void setUp() {
        juego = new Juego();
    }

    @Test
    public void Test01DerbiaCrarJugadores() {

        juego.crearJugadores(List.of("Ana", "Beto", "Carlos"), List.of("//////"));

        assertEquals(3, juego.cantidadJugadores());
    }


    @Test
    public void tes02PasarTurnoCambiaElJugadorActivo() {

        Jugador j1 = new Jugador("Ana", new Inventario());
        Jugador j2 = new Jugador("Beto", new Inventario());
        ArrayList<Jugador> a = new ArrayList<>();
        a.add(j1);
        a.add(j2);
        Juego juego = new Juego(a);

        Turno turnoActual = juego.turnoActual();
        Jugador jugadorInicial = juego.jugadorActivo();
        turnoActual.cambiarEstado(new EstadoAcciones());
        turnoActual.pasarTurno(juego);

        Jugador jugadorNuevo = juego.jugadorActivo();

        assertNotEquals(jugadorInicial, jugadorNuevo);
    }


    @Test
    public void test03ValidarJugadoresLanzaExcepcionConMenosDe3Jugadores(){
        juego.agregarJugador(new Jugador("Pepito", new Inventario()));

        assertThrows(JugadoresMinimosRegistradosError.class, () -> {
            juego.validarJugadores();
        });
    }


    @Test
    public void test04SecuenciaDeTurnosInicialIdaYVuelta() {
        ArrayList<Jugador> listaJugadores = new ArrayList<>();
        listaJugadores.add(new Jugador("j1", new Inventario()));
        listaJugadores.add(new Jugador("j2", new Inventario()));
        listaJugadores.add(new Jugador("j3", new Inventario()));
        
        Juego juegoConJugadores = new Juego(listaJugadores);

        assertEquals("j1", juegoConJugadores.jugadorActivo().getNombre());

        juegoConJugadores.avanzarJugador(null); 
        assertEquals("j2", juegoConJugadores.jugadorActivo().getNombre());

        juegoConJugadores.avanzarJugador(null);
        assertEquals("j3", juegoConJugadores.jugadorActivo().getNombre());

        juegoConJugadores.avanzarJugador(null);
        assertEquals("j3", juegoConJugadores.jugadorActivo().getNombre());

        juegoConJugadores.avanzarJugador(null);
        assertEquals("j2", juegoConJugadores.jugadorActivo().getNombre());

        juegoConJugadores.avanzarJugador(null);
        assertEquals("j1", juegoConJugadores.jugadorActivo().getNombre());
        
        juegoConJugadores.avanzarJugador(null);
        assertFalse(juegoConJugadores.getTablero() == null);
    }


    @Test
    public void test05MoverLadronSinActivarLanzaExcepcion() {
        Hexagono hexagonoMock = mock(Hexagono.class);
        
        assertThrows(AccionNoPermitidaException.class, () -> {
            juego.moverLadronA(hexagonoMock);
        });
    }


    @Test
    public void test06MoverLadronHabilitadoFunciona() {
        juego.activarLadron(); 
        
        Hexagono hexagonoMock = mock(Hexagono.class);
        
        try {
            juego.moverLadronA(hexagonoMock);
        } catch (Exception e) {
            assertFalse(e instanceof AccionNoPermitidaException);
        }
    }

    
    @Test
    public void testGranCaballeriaCambiaDeDueño() {
        Jugador j1 = mock(Jugador.class);
        Jugador j2 = mock(Jugador.class);

        when(j1.puedeReclamarGranCaballeria()).thenReturn(true);
        when(j1.superaEnCaballerosA(any())).thenReturn(true); 

        juego.actualizarGranCaballeria(j1);
        verify(j1, times(1)).sumarPuntoVictoria(2);

        when(j2.puedeReclamarGranCaballeria()).thenReturn(true);
        when(j2.superaEnCaballerosA(j1)).thenReturn(true);

        juego.actualizarGranCaballeria(j2);

        verify(j1, times(1)).restarPuntosVictoria(2);
        verify(j2, times(1)).sumarPuntoVictoria(2);
    }


    @Test
    public void test08ResolverTiradaConSieteActivaLadron() {
         juego.resolverTirada(7);
         assertEquals(7, juego.getDadoActual());
         
         juego.activarLadron();
         Hexagono mockHex = mock(Hexagono.class);
         assertDoesNotThrow(() -> juego.moverLadronA(mockHex));
    }

}
