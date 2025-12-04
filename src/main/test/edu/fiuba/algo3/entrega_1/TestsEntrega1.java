package edu.fiuba.algo3.entrega_1;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Excepciones.ReglaDistanciaException;
import edu.fiuba.algo3.modelo.Juego.Jugador;
import edu.fiuba.algo3.modelo.Tablero.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Construccion.*;
import edu.fiuba.algo3.modelo.Recurso.*;

public class TestsEntrega1 {

    private Jugador jugador1;
    private Jugador jugador2;
    private Inventario inventario1;
    private Inventario inventario2;
    private Inventario inventario3;

    @BeforeEach
    public void setUp() {
        inventario1 = new Inventario((Recurso) null);
        inventario2 = new Inventario((Recurso) null);
        inventario3 = new Inventario(new Madera(),
                    new Ladrillo(), new Lana(), new Grano(),new Madera(),
                    new Ladrillo(), new Lana(), new Grano());

        jugador1 = new Jugador("Jugador 1", inventario1);
        jugador2 = new Jugador("Jugador 2", inventario2);
    }

    @Test
    public void test01deberiaAsignarseAleatoriamenteLosHexagonosDeTerrenosConSusFichas() {
        Tablero t = new Tablero();
        t.construir();

        int recursosObtenidos = t.obtenerRecursosDe(2);

        Assertions.assertEquals(1, recursosObtenidos);

    }

    @Test
    public void test02NoSePuedeConstruirEnUnVerticeConVecinosConstruidos() {
        Vertice primerVertice = new Vertice();
        Vertice segundoVertice = new Vertice();
        ConstruirAsentamiento c = new ConstruirAsentamiento();
        Construccion pueblo = new Poblado();
        Construccion pueblo2 = new Poblado();
        Arista arista = new Arista(primerVertice, segundoVertice);

        Jugador j = new Jugador("Ale", inventario3);
        c.construir(j,pueblo, primerVertice);

        assertThrows(ReglaDistanciaException.class, () -> {
            c.construir(j,pueblo2, segundoVertice);;
        });
    }

    @Test
    public void test03SegundoPobladoEntregaRecursosDeTodosLosHexagonosAdyacentes() {
        Hexagono hexMadera = new Hexagono(Terreno.BOSQUE, 6);
        Hexagono hexPiedra = new Hexagono(Terreno.MONTANA, 8);

        Vertice vertice = new Vertice();

        vertice.agregarHexagono(hexMadera);
        vertice.agregarHexagono(hexPiedra);

        vertice.construir(new Poblado());

        Inventario i = vertice.entregarRecursosIniciales();

        assertEquals(2, i.total());

    }

    @Test
    public void test04VerificarQueElLanzamientoDeDadosGenereNumeroValido2_12() {
        LanzamientoDados lanzamiento = new LanzamientoDados();

        for (int i=0; i < 100; i++){
            int resultado = lanzamiento.lanzar();
            assertTrue(resultado >= 2 && resultado <= 12);
        }
    }

    @Test
    public void test05VerificarLaProduccionCorrectaDeUnRecursoPorPobladoYDosRecursosPorCiudad() {
        Hexagono hexBosque = new Hexagono(Terreno.BOSQUE, 6);

        Vertice verticePoblado = new Vertice();
        verticePoblado.agregarHexagono(hexBosque);
        verticePoblado.construir(new Poblado());

        Vertice verticeCiudad = new Vertice();
        verticeCiudad.agregarHexagono(hexBosque);
        verticeCiudad.construir(new Ciudad(new Jugador("Test", new Inventario())));

        ArrayList<Recurso> produccionPoblado = verticePoblado.generarRecurso(6,1);
        assertEquals(1, produccionPoblado.size());

        ArrayList<Recurso> produccionCiudad = verticeCiudad.generarRecurso(6,2);
        assertEquals(2, produccionCiudad.size());

        ArrayList<Recurso> produccionFallida = verticeCiudad.generarRecurso(5,2);
        assertTrue(produccionFallida.isEmpty());
    }

    @Test
    public void test06TerrenoConLadronNoProduceRecursosAunqueSalgaElNumero() {
        int numeroSuerte = 6;
        Hexagono hexTrigo = new Hexagono(Terreno.CAMPO, numeroSuerte);

        Vertice vertice = new Vertice();
        vertice.agregarHexagono(hexTrigo);
        vertice.construir(new Poblado());

        hexTrigo.colocarLadron();

        ArrayList<Recurso> recursos = vertice.generarRecurso(numeroSuerte,1);

        Assertions.assertEquals(0, recursos.size());
    }


}
