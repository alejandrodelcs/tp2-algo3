package edu.fiuba.algo3.entrega_1;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Construcciones.*;
import edu.fiuba.algo3.modelo.ElementosTablero.*;
import edu.fiuba.algo3.modelo.Errores.*;

/**
 * ReglaDistanciaTest
 */
public class ReglaDistanciaTest {

    @Test
    public void test01SePuedeConstruirEnUnVerticeSinVecinos() {
        Vertice vertice1 = new Vertice();

        Construccion pueblo = mock(Poblado.class);
        when(pueblo.getPuntosDeVictoria()).thenReturn(1);

        vertice1.construir(pueblo);

        assertEquals(1, vertice1.puntoVictoria());
    }

    @Test
    public void test02NoSePuedeConstruirEnUnVerticeConVecinosConstruidos() {

        Vertice primerVertice = new Vertice();
        Vertice segundoVertice = new Vertice();

        Construccion pueblo = mock(Poblado.class);
        when(pueblo.getPuntosDeVictoria()).thenReturn(1);

        Arista arista = new Arista(primerVertice, segundoVertice);

        primerVertice.construir(pueblo);

        assertThrows(ReglaDistanciaException.class, () -> {
            segundoVertice.construir(pueblo);
        });

    }
}
