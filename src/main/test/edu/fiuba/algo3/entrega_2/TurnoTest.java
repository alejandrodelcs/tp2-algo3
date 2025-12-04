package edu.fiuba.algo3.entrega_2;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import java.util.List;

import edu.fiuba.algo3.modelo.Juego.Juego;
import edu.fiuba.algo3.modelo.Juego.Jugador;
import edu.fiuba.algo3.modelo.Juego.Turno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Recurso.Ladrillo;
import edu.fiuba.algo3.modelo.Recurso.Madera;
import edu.fiuba.algo3.modelo.Recurso.Mineral;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

/**
 * TurnoTest
 */
public class TurnoTest {

    private Jugador jugador;
    private Juego juegoMock;

    @BeforeEach
    public void setUp() {
        jugador = new Jugador("Hernesto", new Inventario());
        juegoMock = mock(Juego.class);

    }

    @Test
    public void test01TurnoTieneJugadorActivo() {

        Turno turno = new Turno(jugador, juegoMock);

        assertEquals(jugador, turno.jugadorActivo());
    }

    @Test
    public void test02NoSePuedeTirarElDadoDosVeces() {
        Turno turno = new Turno(jugador, juegoMock);
        int dado = turno.tirarDados();

        assertThrows(IllegalStateException.class, () -> {
            turno.tirarDados();
        });
    }

    @Test
    public void test03ComerciaoFallaSiJugadorActivoNoTieneLoQueOfrece() {
        Inventario prmerInventario = new Inventario();
        Inventario segundoInventario = new Inventario(new Madera());

        Jugador primerJugador = new Jugador("Alan", prmerInventario);
        Jugador segundoJugador = new Jugador("Alan", segundoInventario);

        Turno turno = new Turno(primerJugador, juegoMock);

        List<Class<? extends Recurso>> ofrece = List.of(Madera.class);
        List<Class<? extends Recurso>> pide = List.of(Madera.class);

        assertThrows(IllegalArgumentException.class, () -> {
            turno.comerciarCon(segundoJugador, ofrece, pide);
        });

    }

    @Test
    public void testo04ComercioFallaSiOtroJugadorNoTieneLoQuePide() {
        Inventario prmerInventario = new Inventario(new Mineral());
        Inventario segundoInventario = new Inventario(new Madera());

        Jugador primerJugador = new Jugador("Alan", prmerInventario);
        Jugador segundoJugador = new Jugador("Alan", segundoInventario);

        Turno turno = new Turno(primerJugador, juegoMock);

        List<Class<? extends Recurso>> ofrece = List.of(Madera.class);
        List<Class<? extends Recurso>> pide = List.of(Ladrillo.class);

        assertThrows(IllegalArgumentException.class, () -> {
            turno.comerciarCon(segundoJugador, ofrece, pide);
        });

    }

    @Test
    public void test05ComercioFuncionaCorrectamenteSiAmbosTieneLosRecursos() {

        Inventario prmerInventario = new Inventario(new Mineral());
        Inventario segundoInventario = new Inventario(new Madera());

        Jugador primerJugador = new Jugador("Alan", prmerInventario);
        Jugador segundoJugador = new Jugador("Alan", segundoInventario);

        Turno turno = new Turno(primerJugador, juegoMock);

        List<Class<? extends Recurso>> ofrece = List.of(Mineral.class);
        List<Class<? extends Recurso>> pide = List.of(Madera.class);

        turno.comerciarCon(segundoJugador, ofrece, pide);

        assertEquals(1, primerJugador.cantidadDeRecursoTipo(Madera.class));
        assertEquals(1, segundoJugador.cantidadDeRecursoTipo(Mineral.class));

    }
}
