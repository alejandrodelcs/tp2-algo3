package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Construccion.*;
import edu.fiuba.algo3.modelo.Tablero.Arista;
import edu.fiuba.algo3.modelo.Tablero.Vertice;
import edu.fiuba.algo3.modelo.Excepciones.ReglaDistanciaException;
import edu.fiuba.algo3.modelo.Excepciones.CarreteraNoConectadaError;
import edu.fiuba.algo3.modelo.Jugador.Inventario;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Carta.MazoDesarrollo;
import edu.fiuba.algo3.modelo.Recurso.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TestEntrega2 {

    @Test
    public void test01VerificarElConsumoDeRecursosYLaCorrectaColocacionDeUnaCarretera(){

        Jugador jugador = new Jugador("Ale", new Inventario(new Madera(), new Ladrillo()));

        jugador.construir(new ConstruirAsentamiento(), new Carretera(), new Vertice());

        assertEquals(0, jugador.consultarRecursos());

    }

    @Test
    public void test02ConstruirPobladoConsumeRecursosYValidaDistancia() {
        Jugador jugador = new Jugador("Builder", new Inventario(new Madera(), new Ladrillo(),
                            new Grano(), new Lana(), new Madera(), new Ladrillo(),new Grano(), new Lana()));

        Vertice v1 = new Vertice();
        Vertice v2 = new Vertice();
        Arista a = new Arista(v1,v2);

        jugador.construir(new ConstruirAsentamiento(), new Poblado(), v1);

        assertThrows(ReglaDistanciaException.class, () -> {
            jugador.construir(new ConstruirAsentamiento(),new Poblado(), v2);
        });
    }

    @Test
    public void test03MejorarPobladoACiudadConsumeRecursosYAumentaPV() {
        Jugador jugador = new Jugador("Alcalde", new Inventario(new Mineral(), new Mineral(), new Mineral(),
                                                                        new Grano(), new Grano(), new Grano(),
                                                                        new Ladrillo(),new Madera(), new Lana()));
        Vertice vertice = new Vertice();


        jugador.construir(new ConstruirAsentamiento(), new Poblado(), vertice);

        assertEquals(1, vertice.puntoVictoria());

        jugador.mejorarConstruccion(vertice, new Ciudad());

        assertEquals(0, jugador.cantidadCartas());

        assertEquals(2, vertice.puntoVictoria());
    }

   /* @Test
    public void test04ComprarCartaDesarrolloConsumeRecursosYVaAManoOculta() {
        Jugador jugador = new Jugador("Estratega", new Inventario(new Lana(), new Grano(), new Mineral()));
        MazoDesarrollo mazo = new MazoDesarrollo();

        jugador.comprarCartaDesarrollo(mazo);

        assertEquals(0, jugador.cantidadCartas());

        assertEquals(1, jugador.cantidadCartasDesarrollo());
    }*/

    @Test
    public void test05CartaCompradaNoSePuedeJugarEnElMismoTurno() {
        Jugador jugador = new Jugador("Impaciente", new Inventario(new Lana(), new Grano(), new Mineral()));
        MazoDesarrollo mazo = new MazoDesarrollo();

        jugador.comprarCartaDesarrollo(mazo);

    }

    @Test
    public void noSePuedeConstruirCarreteraQueNoSeaAdyacenteALaRed() {
        Jugador jugador = new Jugador("Ale", new Inventario(new Madera(), new Madera(), new Ladrillo(), new Ladrillo()));

        Vertice v1 = new Vertice();
        Vertice v2 = new Vertice();
        Vertice v3 = new Vertice();
        Vertice v4 = new Vertice();

        Arista aristaDondeSePuede = new Arista(v1, v2);
        Arista aristaLejana = new Arista(v3, v4);

        jugador.construir(new ConstruirCarretera(), new Carretera(), aristaDondeSePuede);

        assertThrows(CarreteraNoConectadaError.class,
                () -> jugador.construir(new ConstruirCarretera(), new Carretera(), aristaLejana));
    }
}
