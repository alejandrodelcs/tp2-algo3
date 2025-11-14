package edu.fiuba.algo3.jugador;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.elementos.Ficha;

/**
 * FichaTest
 */
public class FichaTest {

    @Test
    public void testo01CreoUnaFichaConNumeroAleatorioDentroDelRango2a12() {

        Ficha ficha = new Ficha();
        int numeroDeFicha = ficha.getNumero();

        int minimo = 1;
        int maximo = 13;

        assertTrue((numeroDeFicha > minimo && numeroDeFicha < maximo));
    }

    @Test
    public void unaFichaNoPuedeSer7en50Intentos() {
        for (int i = 0; i < 100; i++) {
            Ficha ficha = new Ficha();
            assertNotEquals(7, ficha.getNumero());
        }

    }
}
