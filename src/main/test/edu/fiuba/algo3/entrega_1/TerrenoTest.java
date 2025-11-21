package edu.fiuba.algo3.entrega_1;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.NoExisteFichaError;
import edu.fiuba.algo3.modelo.Tablero;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TerrenoTest {

    /*
    Verificar la correcta asignación aleatoria de hexágonos de Terreno y Fichas de
        Número.
     */
    @Test
    public void test01deberiaAsignarseAleatoriamenteLosHexagonosDeTerrenosConSusFichas(){
        Tablero t = new Tablero();
        t.construir();

        int recursosObtenidos = t.obtenerRecursosDe(2);

        assertEquals(1, recursosObtenidos);

    }

    @Test
    public void test02deberiaAsignarseAleatoriamenteLosHexagonosDeTerrenosConSusFichasYSiNoExisteLaFichaRetornarUnaExcepcion(){
        Tablero t = new Tablero();
        t.construir();


        assertThrows(NoExisteFichaError.class, ()->t.obtenerRecursosDe(7));

    }

    @Test
    public void test03DeberiaCrear19Hexagonos(){
        Tablero t = new Tablero();
        t.construir();

        assertEquals(19, t.cantidadHexagonos());
    }


}
