package edu.fiuba.algo3.entrega_1;
import java.util.ArrayList;
import edu.fiuba.algo3.modelo.Jugador.Inventario;
import edu.fiuba.algo3.modelo.Tablero.Vertice;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.Tablero.Hexagono;
import edu.fiuba.algo3.modelo.Tablero.Terreno;
import edu.fiuba.algo3.modelo.Construccion.*;
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
        vertice.agregarHexagono(hexagono);

        ArrayList<Recurso> recurso = vertice.generarRecurso(numDado,1);

        assertEquals(1, recurso.size());

    }

    @Test
    public void test03SegundoPobladoEntregaRecursosDeTodosLosHexagonosAdyacentes() {
        Hexagono hexMadera = new Hexagono(Terreno.BOSQUE, 6);
        Hexagono hexPiedra = new Hexagono(Terreno.MONTANA, 8);

        Vertice vertice = new Vertice();

        vertice.agregarHexagono(hexMadera);
        vertice.agregarHexagono(hexPiedra);

        vertice.construir(new Poblado());

        Inventario p = vertice.entregarRecursosIniciales();

        assertEquals(2, p.total());

    }
}
