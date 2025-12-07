package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Tablero.Hexagono;
import edu.fiuba.algo3.modelo.Jugador.Inventario;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Ladron.Ladron;
import edu.fiuba.algo3.modelo.Tablero.Terreno;
import edu.fiuba.algo3.modelo.Construccion.Poblado;
import edu.fiuba.algo3.modelo.Tablero.Vertice;
import edu.fiuba.algo3.modelo.Recurso.Madera;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LadronTest {
    @Test
    public void test01LadronSeCreaYBLoqueaHexagonoInicial() {
        Hexagono desierto = new Hexagono(Terreno.DESIERTO, 0);

        Ladron ladron = new Ladron(desierto);

        assertFalse(desierto.tieneLadron());
    }

    @Test
    public void test02LadronSeMueveYLiberaElHexagonoAnterior() {
        Hexagono origen = new Hexagono(Terreno.COLINA, 8);
        Hexagono destino = new Hexagono(Terreno.BOSQUE, 5);
        Ladron ladron = new Ladron(origen);

        ladron.moverA(destino);

        assertTrue(origen.tieneLadron());
        assertFalse(destino.tieneLadron());
    }

}
