package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Tablero.Hexagono;
import edu.fiuba.algo3.modelo.Excepciones.MovimientoLadronError;
import edu.fiuba.algo3.modelo.Jugador.Inventario;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Ladron.Ladron;
import edu.fiuba.algo3.modelo.Recurso.Desierto;
import edu.fiuba.algo3.modelo.Recurso.Ladrillo;
import edu.fiuba.algo3.modelo.Recurso.Madera;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LadronTest {
    @Test
    public void test01LadronSeCreaYBLoqueaHexagonoInicial() {
        Hexagono desierto = new Hexagono(new Desierto(), 0);

        Ladron ladron = new Ladron(desierto);

        assertTrue(desierto.tieneLadron());
        assertEquals(desierto, ladron.ubicacion());
    }

    @Test
    public void test02LadronSeMueveYLiberaElHexagonoAnterior() {
        Hexagono origen = new Hexagono(new Ladrillo(), 8);
        Hexagono destino = new Hexagono(new Madera(), 5);
        Ladron ladron = new Ladron(origen);

        ladron.moverA(destino);

        assertFalse(origen.tieneLadron());
        assertTrue(destino.tieneLadron());
    }

    @Test
    public void test03NoSePuedeMoverElLadronAlMismoHexagonoDondeYaEsta() {
        Hexagono origen = new Hexagono(new Ladrillo(), 8);
        Ladron ladron = new Ladron(origen);

        assertThrows(MovimientoLadronError.class, () -> {
            ladron.moverA(origen);
        });
    }

    @Test
    public void test04LadronEjecutaElRoboEntreJugadores() {
        Hexagono lugar = new Hexagono(new Desierto(), -1);
        Ladron ladron = new Ladron(lugar);

        Jugador victima = new Jugador("Victima", new Inventario(new Madera()));
        Jugador jugadorLadron = new Jugador("Ladron", new Inventario());

        ladron.robar(jugadorLadron, victima);

        assertEquals(0, victima.cantidadCartas());
        assertEquals(1, jugadorLadron.cantidadCartas());
    }

}
