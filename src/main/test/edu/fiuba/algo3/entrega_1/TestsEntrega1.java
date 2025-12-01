package edu.fiuba.algo3.entrega_1;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
        verticeCiudad.construir(new Ciudad(new Jugador("Test", new Inventario())));

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
    public void test07UnJugadorDescartaLaMitadDeSusCartasSiSale7yTieneMasDe7Cartas() {

        Jugador jugador = new Jugador("Julia", new Inventario());

        int dado1 = 1;
        int dado2 = 2;
        int dado3 = 3;
        int dado7 = 7;

        Hexagono hexMadera = new Hexagono(Terreno.BOSQUE, 1);
        Hexagono hexaPiedra = new Hexagono(Terreno.MONTANA, 2);
        Hexagono hexaLana = new Hexagono(Terreno.PASTIZAL, 3);

        Vertice vertice = new Vertice();
        vertice.asignarHexagonos(hexMadera);
        vertice.asignarHexagonos(hexaPiedra);
        vertice.asignarHexagonos(hexaLana);

        jugador.recibirRecurso(new Madera());
        jugador.recibirRecurso(new Lana());
        jugador.recibirRecurso(new Ladrillo());
        jugador.recibirRecurso(new Grano());

        jugador.construir(vertice, new Poblado());

        for (int i = 0; i < 3; i++) {
            jugador.generarSegunDado(dado1);
        }
        for (int i = 0; i < 3; i++) {
            jugador.generarSegunDado(dado2);
        }
        for (int i = 0; i < 3; i++) {
            jugador.generarSegunDado(dado3);
        }



        jugador.generarSegunDado(dado7);

        assertEquals(5, jugador.cantidadCartas());
    }

    @Test
    public void test08LadronSeMueveYRobaUnRecursoAJugadorAdyacente() {
        Inventario inventario1 = new Inventario();
        Inventario inventario2 = new Inventario();

        Jugador jugador = new Jugador("Jeronimo", inventario1);
        Jugador victima = new Jugador("Natanael", inventario2);

        victima.recibirRecurso(new Madera());

        Hexagono hexDestino = new Hexagono(Terreno.BOSQUE, 5);

        Vertice verticeAdyacente = new Vertice();
        verticeAdyacente.asignarHexagonos(hexDestino);

        Poblado pobado = new Poblado(victima);

        pobado.agregarDuenio(List.of(victima));
        verticeAdyacente.construir(pobado);

        Ladron ladron = new Ladron(hexDestino);

        ladron.robar(jugador);

        assertEquals(1, jugador.cantidadCartas());
        assertEquals(0, victima.cantidadCartas());
    }

}
