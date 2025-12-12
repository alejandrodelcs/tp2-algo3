package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Tablero.Hexagono;
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

        assertFalse(desierto.tieneLadron());
    }

    @Test
    public void test02LadronSeMueveYLiberaElHexagonoAnterior() {
        Hexagono origen = new Hexagono(new Ladrillo(), 8);
        Hexagono destino = new Hexagono(new Madera(), 5);
        Ladron ladron = new Ladron(origen);

        ladron.moverA(destino);

        assertTrue(origen.tieneLadron());
        assertFalse(destino.tieneLadron());
    }

}
