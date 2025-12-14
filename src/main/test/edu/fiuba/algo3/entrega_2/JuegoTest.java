package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Inventario;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Turno.EstadoAcciones;
import edu.fiuba.algo3.modelo.Turno.Turno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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



}
