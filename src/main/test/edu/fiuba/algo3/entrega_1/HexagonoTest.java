package edu.fiuba.algo3.entrega_1;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
import edu.fiuba.algo3.modelo.Tablero.Hexagono;
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
        Recurso terrenoPastisal = mock(Mineral.class);
        when(terrenoPastisal.esDelMismoTipoQue(Mineral.class)).thenReturn(true);

        Hexagono hexa1 = new Hexagono(terrenoPastisal, numeroFicha);

        Recurso recGenerad = hexa1.obtenerRecurso(numeroDado);

        assertTrue(recGenerad.esDelMismoTipoQue(Mineral.class));
    }

    @Test
    public void test02HexagonoNoCreaUnRecursoSiNoSaleElDado() {
        int numeroFicha = 2;
        int numeroDado = 1;
        Recurso terrenoPastisal = mock();

        Hexagono hexa1 = new Hexagono(terrenoPastisal, numeroFicha);

        Recurso recGenerad = hexa1.obtenerRecurso(numeroDado);

        assertNull(recGenerad);
    }
}
