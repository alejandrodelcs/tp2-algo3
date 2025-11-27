package edu.fiuba.algo3.entrega_1;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Hexagono;
import edu.fiuba.algo3.modelo.Terreno;
import edu.fiuba.algo3.modelo.Construcciones.*;
import edu.fiuba.algo3.modelo.ElementosTablero.*;
import edu.fiuba.algo3.modelo.Recurso.*;

/**
 * ConstruccionesTest
 */
public class ConstruccionesTest {

    @Test
    public void test01UnPuebloGeneraUnRecurso() {

        int numDado = 1;
        Terreno terreno = Terreno.COLINA;

        Hexagono hexagono = new Hexagono(terreno, 1);
        Construccion poblado = new Poblado();

        Vertice vertice = new Vertice();

        vertice.construir(poblado);
        vertice.asignarHexagonos(hexagono);

        ArrayList<Recurso> recurso = vertice.generarRecurso(numDado);

        assertEquals(recurso.size(), 1);

    }

    @Test
    public void test03SegundoPobladoEntregaRecursosDeTodosLosHexagonosAdyacentes() {
        Hexagono hexMadera = new Hexagono(Terreno.BOSQUE, 6);
        Hexagono hexPiedra = new Hexagono(Terreno.MONTANA, 8);

        Vertice vertice = new Vertice();

        vertice.asignarHexagonos(hexMadera);
        vertice.asignarHexagonos(hexPiedra);

        vertice.construir(new Poblado());

        ArrayList<Recurso> inventarioInicial = vertice.entregarRecursosIniciales();

        assertEquals(2, inventarioInicial.size());

    }
}
