package edu.fiuba.algo3.ElementosTablero;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import edu.fiuba.algo3.modelo.Tablero.Arista;
import edu.fiuba.algo3.modelo.Tablero.Vertice;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Construccion.*;

/**
 * ElementosTableroTest
 */
public class ElementosTableroTest {

    @Test
    public void test01CreoUnVerticeVacio() {
        Vertice vertice1 = new Vertice();

        assertFalse(vertice1.tieneConstruccion());

    }

    @Test
    public void test02CreUnVerticeYConstruyoUnPoblado() {
        Vertice vertice = new Vertice();
        Construccion poblado = mock(Poblado.class);
        vertice.construir(poblado);

        assertTrue(vertice.tieneConstruccion());
    }

   /* @Test
    public void test03UnVerticeVacioNoGeneraPV() {
        Vertice vertice1 = new Vertice();

        assertEquals(0, vertice1.puntoVictoria());

    }*/

    @Test
    public void test04UnVerticeConPobladoGeneraUnPV() {
        Vertice vertice = new Vertice();

        Construccion poblado = mock(Poblado.class);
        when(poblado.getPuntosDeVictoria()).thenReturn(1);
        vertice.construir(poblado);

        assertEquals(1, vertice.puntoVictoria());

    }

    @Test
    public void test05DosVerticesSonConectadosConUnArista() {
        Vertice primerVertice = new Vertice();
        Vertice segundoVertice = new Vertice();

        Arista arista = new Arista(primerVertice, segundoVertice);

        assertEquals(segundoVertice, arista.getOtroVertice(primerVertice));

    }



}
