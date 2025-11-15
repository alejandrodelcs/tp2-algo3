package edu.fiuba.algo3.jugador;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Juego.Inventario;
import edu.fiuba.algo3.modelo.Juego.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Costo;
import edu.fiuba.algo3.modelo.Recurso.TipoRecurso;

/**
 * JugadorTest
 */
public class JugadorTest {

    @Test
    public void test01CreoUnJugadorConUnNombre() {
        Jugador jugador1 = new Jugador("Esteban");

        String nombre = jugador1.getNombre();
        String nombreEsperado = "Esteban";

        assertEquals(nombreEsperado, nombre);

    }

    @Test
    public void test02CrearJugadorYagregarUnInventarioConUnRecursoYPidoSuInventario() {

        Jugador jugador1 = new Jugador("Esteban");
        TipoRecurso recursoMock = mock(TipoRecurso.class);

        jugador1.recibirRecurso(recursoMock, 2);

        Inventario inventario1 = jugador1.getInventario();

        assertEquals(inventario1.contar(recursoMock), 2);
    }

    @Test
    public void test03JugadorNoPuedePagarUnConstruccion() {
        Jugador jugador1 = new Jugador("Esteban");
        jugador1.recibirRecurso(TipoRecurso.MADERA, 2);

        Costo costoMOck = mock(Costo.class);
        Map<TipoRecurso, Integer> requisito = Map.of(
                TipoRecurso.MADERA, 3);

        when(costoMOck.getRequisitos()).thenReturn(requisito);

        assertFalse(jugador1.puedePagar(costoMOck));

    }

    @Test
    public void test03JugadorPuedePagarUnConstruccion() {
        Jugador jugador1 = new Jugador("Esteban");
        jugador1.recibirRecurso(TipoRecurso.MADERA, 2);

        Costo costoMOck = mock(Costo.class);
        Map<TipoRecurso, Integer> requisito = Map.of(
                TipoRecurso.MADERA, 2);

        when(costoMOck.getRequisitos()).thenReturn(requisito);

        assertTrue(jugador1.puedePagar(costoMOck));

    }

}
