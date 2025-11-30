package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Hexagono;
import edu.fiuba.algo3.modelo.Inventario;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Ladron;
import edu.fiuba.algo3.modelo.Terreno;
import edu.fiuba.algo3.modelo.Construcciones.Poblado;
import edu.fiuba.algo3.modelo.ElementosTablero.Vertice;
import edu.fiuba.algo3.modelo.Recurso.Madera;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

public class LadronTest {
    @Test
    public void test01LadronSeCreaYBLoqueaHexagonoInicial(){
        Hexagono desierto = new Hexagono(Terreno.DESIERTO, 0);

        Ladron ladron = new Ladron(desierto);

        assertTrue(desierto.tieneLadron());
    }

    @Test
    public void test02LadronSeMueveYLiberaElHexagonoAnterior(){
        Hexagono origen = new Hexagono(Terreno.COLINA, 8);
        Hexagono destino = new Hexagono(Terreno.BOSQUE, 5);
        Ladron ladron = new Ladron(origen);

        ladron.moverA(destino);

        assertFalse(origen.tieneLadron());
        assertTrue(destino.tieneLadron());
    }

    @Test
    public void test03LadronRobaAOtroJugadorUnRecurso(){
        Inventario inventario1 = new Inventario();
        Inventario inventario2 = new Inventario();

        Jugador jugador = new Jugador("Jeronimo", inventario1);
        Jugador victima = new Jugador("Natanael", inventario2);

        victima.recibirRecurso(new Madera());

        Hexagono hexDestino = new Hexagono(Terreno.BOSQUE, 5);

        Vertice verticeAdyacente = new Vertice();
        verticeAdyacente.asignarHexagonos(hexDestino);

        victima.construir(verticeAdyacente, new Poblado());
        // falta linkear el vertice a victima
        Ladron ladron = new Ladron(hexDestino);

        ladron.robar(jugador);

        assertEquals(1, jugador.cantidadCartas());
        assertEquals(0, victima.cantidadCartas());
    }
}
