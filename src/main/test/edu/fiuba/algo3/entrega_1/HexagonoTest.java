package edu.fiuba.algo3.entrega_1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
import edu.fiuba.algo3.modelo.Tablero.Hexagono;
import edu.fiuba.algo3.modelo.Tablero.Terreno;
import edu.fiuba.algo3.modelo.Recurso.*;

import org.junit.jupiter.api.Test;

/**
 * HexagonoTest
 */
public class HexagonoTest {

    @Test
    public void test01HexgonoCreaUnRecursoSiTieneElNumeroDeDado() {

        int numeroFicha = 1;
        int numeroDado = 1;
        Terreno terrenoPastisal = mock(Terreno.class);

        Hexagono hexa1 = new Hexagono(terrenoPastisal, numeroFicha);
        when(terrenoPastisal.retornarRecurso()).thenReturn(new Lana());

        Recurso recGenerad = hexa1.obtenerRecurso(numeroDado);

        assertEquals(1, recGenerad.cantidad);
    }

    @Test
    public void test02HexagonoNoCreaUnRecursoSiNoSaleElDado() {
        int numeroFicha = 2;
        int numeroDado = 1;
        Terreno terrenoPastisal = mock();

        Hexagono hexa1 = new Hexagono(terrenoPastisal, numeroFicha);
        // when(terrenoPastisal.obtenerRecurso()).thenReturn(1);
        when(terrenoPastisal.retornarRecurso()).thenReturn(null);

        // int recGenerad = hexa1.obtenerRecurso(numeroDado);
        Recurso recGenerad = hexa1.obtenerRecurso(numeroDado);

        // assertEquals(0, recGenerad);
        assertNull(recGenerad);
    }
}
