package edu.fiuba.algo3.entrega_2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import edu.fiuba.algo3.modelo.Dado.Dado;
import edu.fiuba.algo3.modelo.Jugador.Inventario;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
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
                new Mineral(), new Grano(), new Madera()));
        tablero = new Tablero();

    }

    @Test
    public void test01LadronRobaUnRecursoSeEsperaLaMitadDeL() {

        Turno turno = new Turno(jugador, tablero);

        assertEquals(jugador, turno.jugador());
    }

    @Test
    public void test02TirarDadosSiSale7MueveLadronYRoba() {
        Turno turno = new Turno(jugador, tablero);
        turno.tirarDado(new Dado(7));

    }


  /*  @Test
    public void test03NoSePuedeTirarElDadoDosVeces() {
        Turno turno = new Turno(jugador, tablero);
        Dado dado = new Dado();
        assertThrows(IllegalStateException.class, ()->turno.tirarDado(dado));
    }

    @Test
    public void test03ComerciarFallaSiJugadorActivoNoTieneLoQueOfrece() {
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
   */
}