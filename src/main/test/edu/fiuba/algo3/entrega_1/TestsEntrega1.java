package edu.fiuba.algo3.entrega_1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;

import edu.fiuba.algo3.modelo.Dado.Dado;
import edu.fiuba.algo3.modelo.Excepciones.ReglaDistanciaException;
import edu.fiuba.algo3.modelo.Jugador.Inventario;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Ladron.Ladron;
import edu.fiuba.algo3.modelo.Tablero.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Construccion.*;
import edu.fiuba.algo3.modelo.Recurso.*;

public class TestsEntrega1 {

    private Inventario inventario3;

    @BeforeEach
    public void setUp() {
        inventario3 = new Inventario(new Madera(),
                new Ladrillo(), new Lana(), new Grano(), new Madera(),
                new Ladrillo(), new Lana(), new Grano());
    }

    /*
     * @Test
     * public void
     * test01deberiaAsignarseAleatoriamenteLosHexagonosDeTerrenosConSusFichas() {
     * Tablero t = new Tablero();
     * t.construir();
     * 
     * int recursosObtenidos = t.obtenerRecursosDe(2);
     * 
     * Assertions.assertEquals(1, recursosObtenidos);
     * 
     * }
     */
    @Test
    public void test02NoSePuedeConstruirEnUnVerticeConVecinosConstruidos() {
        Vertice primerVertice = new Vertice();
        Vertice segundoVertice = new Vertice();
        ConstruirAsentamiento c = new ConstruirAsentamiento();
        Construccion pueblo = new Poblado();
        Construccion pueblo2 = new Poblado();
        Arista arista = new Arista(primerVertice, segundoVertice);

        Jugador j = new Jugador("Ale", inventario3);
        c.construir(j, pueblo, primerVertice);

        assertThrows(ReglaDistanciaException.class, () -> {
            c.construir(j, pueblo2, segundoVertice);
        });
    }

    @Test
    public void test03SegundoPobladoEntregaRecursosDeTodosLosHexagonosAdyacentes() {
        Hexagono hexMadera = new Hexagono(new Madera(), 6);
        Hexagono hexPiedra = new Hexagono(new Mineral(), 8);

        Vertice vertice = new Vertice();

        vertice.agregarHexagono(hexMadera);
        vertice.agregarHexagono(hexPiedra);

        vertice.construir(new Poblado());

        Inventario i = vertice.entregarRecursosIniciales();

        assertEquals(2, i.total());

    }

    @Test
    public void test04VerificarQueElLanzamientoDeDadosGenereNumeroValido2_12() {
        Dado dado = new Dado();

        for (int i = 0; i < 100; i++) {
            int resultado = dado.lanzar();
            assertTrue(resultado >= 2 && resultado <= 12);
        }
    }

    @Test
    public void test05VerificarLaProduccionCorrectaDeUnRecursoPorPobladoYDosRecursosPorCiudad() {
        Hexagono hexBosque = new Hexagono(new Madera(), 6);

        Vertice verticePoblado = new Vertice();
        verticePoblado.agregarHexagono(hexBosque);
        verticePoblado.construir(new Poblado());

        Vertice verticeCiudad = new Vertice();
        verticeCiudad.agregarHexagono(hexBosque);
        verticeCiudad.construir(new Ciudad());

        ArrayList<Recurso> produccionPoblado = verticePoblado.generarRecurso(6, 1);
        assertEquals(1, produccionPoblado.size());

        ArrayList<Recurso> produccionCiudad = verticeCiudad.generarRecurso(6, 2);
        assertEquals(2, produccionCiudad.size());

        ArrayList<Recurso> produccionFallida = verticeCiudad.generarRecurso(5, 2);
        assertTrue(produccionFallida.isEmpty());
    }

    @Test
    public void test06TerrenoConLadronNoProduceRecursosAunqueSalgaElNumero() {
        int numeroSuerte = 6;
        Hexagono hexTrigo = new Hexagono(new Lana(), numeroSuerte);

        Vertice vertice = new Vertice();
        vertice.agregarHexagono(hexTrigo);
        vertice.construir(new Poblado());

        hexTrigo.colocarLadron(new Ladron(new Hexagono(new Desierto(), -1)));

        ArrayList<Recurso> recursos = vertice.generarRecurso(numeroSuerte, 1);

        Assertions.assertEquals(0, recursos.size());
    }

}
