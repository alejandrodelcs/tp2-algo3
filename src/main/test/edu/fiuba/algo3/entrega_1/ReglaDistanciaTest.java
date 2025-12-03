package edu.fiuba.algo3.entrega_1;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import edu.fiuba.algo3.modelo.Inventario;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Grano;
import edu.fiuba.algo3.modelo.Recurso.Ladrillo;
import edu.fiuba.algo3.modelo.Recurso.Lana;
import edu.fiuba.algo3.modelo.Recurso.Madera;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Construcciones.*;
import edu.fiuba.algo3.modelo.ElementosTablero.*;
import edu.fiuba.algo3.modelo.Errores.*;

/**
 * ReglaDistanciaTest
 */
public class ReglaDistanciaTest {
    private Inventario inventario;

    @BeforeEach
    public void setUp() {
        inventario = new Inventario(new Madera(),
                new Ladrillo(), new Lana(), new Grano(),new Madera(),
                new Ladrillo(), new Lana(), new Grano());
    }

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
        ConstruirAsentamiento c = new ConstruirAsentamiento();
        Construccion pueblo = new Poblado();
        Construccion pueblo2 = new Poblado();
        Arista arista = new Arista(primerVertice, segundoVertice);

        Jugador j = new Jugador("Ale", inventario);
        c.construir(j,pueblo, primerVertice);

        assertThrows(ReglaDistanciaException.class, () -> {
            c.construir(j,pueblo2, segundoVertice);;
        });

    }
}
