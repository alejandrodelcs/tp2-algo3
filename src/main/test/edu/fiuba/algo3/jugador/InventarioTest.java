package edu.fiuba.algo3.jugador;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Map;

import edu.fiuba.algo3.modelo.Juego.Inventario;
import edu.fiuba.algo3.modelo.Material.Costo;
import edu.fiuba.algo3.modelo.Material.TipoRecurso;

/**
 * InventarioTest
 */
public class InventarioTest {

    @Test
    public void test01CrearUnInventarioYAgregoUnRecurso() {

        Inventario inventario1 = new Inventario();
        TipoRecurso recursoMock = mock(TipoRecurso.class);

        int cantEsperada = 1;
        inventario1.agregarRecurso(recursoMock, 1);

        assertEquals(cantEsperada, inventario1.contar(recursoMock));

    }

    @Test
    public void test02UnInventarioPuedeConsumirUnRecurso() {
        Inventario inventario1 = new Inventario();
        Costo costoMock = mock(Costo.class);
        Map<TipoRecurso, Integer> requisistos = Map.of(TipoRecurso.MADERA, 1);

        when(costoMock.getRequisitos()).thenReturn(requisistos);

        inventario1.agregarRecurso(TipoRecurso.MADERA, 3);

        inventario1.consumirRecursos(costoMock);

        int cantEsperada = 2;

        assertEquals(cantEsperada, inventario1.contar(TipoRecurso.MADERA));
    }

}
