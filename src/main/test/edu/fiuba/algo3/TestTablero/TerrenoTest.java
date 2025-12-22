package edu.fiuba.algo3.TestTablero;

import edu.fiuba.algo3.modelo.Excepciones.NoExisteFichaError;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TerrenoTest {

    /*
     * Verificar la correcta asignación aleatoria de hexágonos de Terreno y Fichas
     * de
     * Número.
     */
    @Test
    public void test01deberiaAsignarseAleatoriamenteLosHexagonosDeTerrenosConSusFichasYProducirAlmenosUnRecurso() {
        Tablero t = new Tablero();
        t.construir();

        int recursosObtenidos = t.obtenerRecursosDe(4);

        assertTrue(recursosObtenidos > 0);

    }

    @Test
    public void test02deberiaAsignarseAleatoriamenteLosHexagonosDeTerrenosConSusFichasYSiNoExisteLaFichaRetornarUnaExcepcion() {
        Tablero t = new Tablero();
        t.construir();

        assertThrows(NoExisteFichaError.class, () -> t.obtenerRecursosDe(7));

    }

    @Test
    public void test03DeberiaCrear19Hexagonos() {
        Tablero t = new Tablero();
        t.construir();

        assertEquals(19, t.cantidadHexagonos());
    }

}
