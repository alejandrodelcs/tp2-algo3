package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Construcciones.Carretera;
import edu.fiuba.algo3.modelo.Construcciones.Ciudad;
import edu.fiuba.algo3.modelo.Construcciones.Poblado;
import edu.fiuba.algo3.modelo.ElementosTablero.Arista;
import edu.fiuba.algo3.modelo.ElementosTablero.Vertice;
import edu.fiuba.algo3.modelo.Errores.CartaNoDisponibleException;
import edu.fiuba.algo3.modelo.Errores.ReglaDistanciaExeption;
import edu.fiuba.algo3.modelo.Errores.CarreteraNoConectadaError;
import edu.fiuba.algo3.modelo.Inventario;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.MazoDesarrollo;
import edu.fiuba.algo3.modelo.Recurso.*;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestEntrega2 {

    @Test
    public void test01VerificarElConsumoDeRecursosYLaCorrectaColocacionDeUnaCarretera(){

        Jugador jugador = new Jugador("Ale", new Inventario(new Madera(), new Ladrillo()));

        Construible estrategia = new ConstruirAsentamiento();
        jugador.construir(estrategia, new Carretera(), new Vertice());

        Assertions.assertEquals(0, jugador.consultarRecursos());

    }

    @Test
    public void test02ConstruirPobladoConsumeRecursosYValidaDistancia() {
        Jugador jugador = new Jugador("Builder", new Inventario());

        jugador.recibirRecurso(new Madera());
        jugador.recibirRecurso(new Ladrillo());
        jugador.recibirRecurso(new Grano());
        jugador.recibirRecurso(new Lana());

        Vertice v1 = new Vertice();
        Vertice v2 = new Vertice();
        Arista arista = new Arista(v1, v2);

        jugador.construir(v1, new Poblado(jugador));

        assertEquals(0, jugador.cantidadCartas());

        assertTrue(v1.tieneConstruccion());

        jugador.recibirRecurso(new Madera());
        jugador.recibirRecurso(new Ladrillo());
        jugador.recibirRecurso(new Grano());
        jugador.recibirRecurso(new Lana());

        assertThrows(ReglaDistanciaExeption.class, () -> {
            jugador.construir(v2, new Poblado(jugador));
        });
    }

    @Test
    public void test03MejorarPobladoACiudadConsumeRecursosYAumentaPV() {
        Jugador jugador = new Jugador("Alcalde", new Inventario());
        Vertice vertice = new Vertice();

        Vertice v = new Vertice();
        v.construir(new Poblado(jugador));

        assertEquals(1, v.puntoVictoria());

        jugador.recibirRecurso(new Mineral());
        jugador.recibirRecurso(new Mineral());
        jugador.recibirRecurso(new Mineral());
        jugador.recibirRecurso(new Grano());
        jugador.recibirRecurso(new Grano());

        jugador.mejorarConstruccion(v, new Ciudad(jugador));

        assertEquals(0, jugador.cantidadCartas());

        assertEquals(2, v.puntoVictoria());
    }

    @Test
    public void test04ComprarCartaDesarrolloConsumeRecursosYVaAManoOculta() {
        Jugador jugador = new Jugador("Estratega", new Inventario());
        MazoDesarrollo mazo = new MazoDesarrollo();

        jugador.recibirRecurso(new Lana());
        jugador.recibirRecurso(new Grano());
        jugador.recibirRecurso(new Mineral());

        jugador.comprarCartaDesarrollo(mazo);

        assertEquals(0, jugador.cantidadCartas());

        assertEquals(1, jugador.cantidadCartasDesarrollo());
    }

    @Test
    public void test05CartaCompradaNoSePuedeJugarEnElMismoTurno() {
        Jugador jugador = new Jugador("Impaciente", new Inventario());
        MazoDesarrollo mazo = new MazoDesarrollo();

        jugador.recibirRecurso(new Lana());
        jugador.recibirRecurso(new Grano());
        jugador.recibirRecurso(new Mineral());

        jugador.comprarCartaDesarrollo(mazo);

    @Test
    public void noSePuedeConstruirCarreteraQueNoSeaAdyacenteALaRed() {
        Inventario inv = new Inventario(new Madera(), new Madera(), new Ladrillo(), new Ladrillo());
        Jugador jugador = new Jugador("Ale", inv);

        Vertice v1 = new Vertice();
        Vertice v2 = new Vertice();
        Vertice v3 = new Vertice();
        Vertice v4 = new Vertice();


        Construible estrategia = new ConstruirCarretera();
        jugador.construir(estrategia, new Carretera(), v1,v2);

        assertThrows(CarreteraNoConectadaError.class,
                () -> jugador.construir(estrategia, new Carretera(), v3,v4));
    }


        assertThrows(CartaNoDisponibleException.class, () -> {
            jugador.usarCartaDesarrollo(0);
        });

        jugador.pasarTurno();

        assertDoesNotThrow(() -> {
            jugador.usarCartaDesarrollo(0);
        });
    }
}
