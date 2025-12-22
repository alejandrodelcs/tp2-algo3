package edu.fiuba.algo3.TestConstruccion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import edu.fiuba.algo3.modelo.Costo.ReglaCostoConstruccion;
import edu.fiuba.algo3.modelo.Jugador.Inventario;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Grano;
import edu.fiuba.algo3.modelo.Recurso.Ladrillo;
import edu.fiuba.algo3.modelo.Recurso.Lana;
import edu.fiuba.algo3.modelo.Recurso.Madera;
import edu.fiuba.algo3.modelo.Tablero.Arista;
import edu.fiuba.algo3.modelo.Tablero.Vertice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Construccion.*;
import edu.fiuba.algo3.modelo.Excepciones.*;

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
        ConstruirAsentamiento c = new ConstruirAsentamiento(new ReglaDistancia());
        Construccion pueblo = new Poblado();
        Construccion pueblo2 = new Poblado();
        Arista arista = new Arista(primerVertice, segundoVertice);

        Jugador j = new Jugador("Ale", inventario);
        c.construir(pueblo, j, primerVertice);

        assertThrows(ReglaDistanciaException.class, () -> {
            c.construir(pueblo2, j, segundoVertice);
        });

    }
}
