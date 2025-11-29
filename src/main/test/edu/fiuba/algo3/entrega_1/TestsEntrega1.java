package edu.fiuba.algo3.entrega_1;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Errores.ReglaDistanciaExeption;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Construcciones.*;
import edu.fiuba.algo3.modelo.ElementosTablero.*;
import edu.fiuba.algo3.modelo.Recurso.*;

public class TestsEntrega1 {

    private Jugador jugador1;
    private Jugador jugador2;
    private Inventario inventario1;
    private Inventario inventario2;

    @BeforeEach
    public void setUp() {
        inventario1 = new Inventario((Recurso) null);
        inventario2 = new Inventario((Recurso) null);
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

        Construccion pueblo = mock(Poblado.class);
        when(pueblo.getPuntosDeVictoria()).thenReturn(1);

        Arista arista = new Arista(primerVertice, segundoVertice);

        primerVertice.construir(pueblo);

        assertThrows(ReglaDistanciaExeption.class, () -> {
            segundoVertice.construir(pueblo);
        });

    }

    @Test
    public void test03SegundoPobladoEntregaRecursosDeTodosLosHexagonosAdyacentes() {
        Hexagono hexMadera = new Hexagono(Terreno.BOSQUE, 6);
        Hexagono hexPiedra = new Hexagono(Terreno.MONTANA, 8);

        Vertice vertice = new Vertice();

        vertice.asignarHexagonos(hexMadera);
        vertice.asignarHexagonos(hexPiedra);

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
        verticePoblado.asignarHexagonos(hexBosque);
        verticePoblado.construir(new Poblado());

        Vertice verticeCiudad = new Vertice();
        verticeCiudad.asignarHexagonos(hexBosque);
        verticeCiudad.construir(new Ciudad());

        ArrayList<Recurso> produccionPoblado = verticePoblado.generarRecurso(6);
        assertEquals(1, produccionPoblado.size());
        assertEquals(1, produccionPoblado.get(0).cantidad);

        ArrayList<Recurso> produccionCiudad = verticeCiudad.generarRecurso(6);
        assertEquals(1, produccionCiudad.size());
        assertEquals(2, produccionCiudad.get(0).cantidad);

        ArrayList<Recurso> produccionFallida = verticeCiudad.generarRecurso(5);
        assertTrue(produccionFallida.isEmpty());
    }

    @Test
    public void test06TerrenoConLadronNoProduceRecursosAunqueSalgaElNumero() {
        int numeroSuerte = 6;
        Hexagono hexTrigo = new Hexagono(Terreno.CAMPO, numeroSuerte);

        Vertice vertice = new Vertice();
        vertice.asignarHexagonos(hexTrigo);
        vertice.construir(new Poblado());

        ArrayList<Recurso> produccionNormal = vertice.generarRecurso(numeroSuerte);
        assertFalse(produccionNormal.isEmpty());
        assertEquals(1, produccionNormal.get(0).cantidad);

        hexTrigo.colocarLadron();

        ArrayList<Recurso> produccionBloqueada = vertice.generarRecurso(numeroSuerte);

        assertTrue(produccionBloqueada.isEmpty());
    }

    @Test
    public void test08LadronSeMueveYRobaUnRecursoAJugadorAdyacente() {
        Jugador ladron = new Jugador("Matias",new Inventario());
        Jugador victima = new Jugador("Natan",new Inventario());

        victima.recibirRecurso(new Madera());

        Tablero tablero = new Tablero();
        Hexagono hexDestino = new Hexagono(Terreno.BOSQUE, 5);
        Vertice verticeAdyacente = new Vertice();

        verticeAdyacente.asignarHexagonos(hexDestino);

        verticeAdyacente.construir(new Poblado());

        tablero.moverLadron(hexDestino, ladron);

        assertEquals(1, ladron.cantidadCartas());

        assertEquals(0, victima.cantidadCartas());
    }

}
