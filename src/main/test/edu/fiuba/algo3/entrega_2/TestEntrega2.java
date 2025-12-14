package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Construccion.*;
import edu.fiuba.algo3.modelo.Costo.ReglaCostoConstruccion;
import edu.fiuba.algo3.modelo.Excepciones.NoSePuedeConstruirCarreteraError;
import edu.fiuba.algo3.modelo.Tablero.Arista;
import edu.fiuba.algo3.modelo.Tablero.Hexagono;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Vertice;
import edu.fiuba.algo3.modelo.Excepciones.ReglaDistanciaException;
import edu.fiuba.algo3.modelo.Jugador.Inventario;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Carta.Mazo;
import edu.fiuba.algo3.modelo.Recurso.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TestEntrega2 {

    @Test
    public void test01VerificarElConsumoDeRecursosYLaCorrectaColocacionDeUnaCarretera(){

        Jugador jugador = new Jugador("Ale", new Inventario(new Madera(), new Ladrillo(), new Lana(), new Grano(),
                        new Madera(), new Ladrillo()));

        Vertice v1 = new Vertice();
        Vertice v2 = new Vertice();

        Arista a = new Arista(v1,v2);

        jugador.construir(new Poblado(), v1);

        jugador.construir(new Carretera(new ReglaCostoConstruccion()), a);

        assertEquals(0, jugador.cantidadCartas());

    }

    @Test
    public void test02ConstruirPobladoConsumeRecursosYValidaDistancia() {
        Jugador jugador = new Jugador("Builder", new Inventario(new Madera(), new Ladrillo(),
                            new Grano(), new Lana(), new Madera(), new Ladrillo(),new Grano(), new Lana()));

        Vertice v1 = new Vertice();
        Vertice v2 = new Vertice();
        Arista a = new Arista(v1,v2);

        jugador.construir(new Poblado(), v1);

        assertThrows(ReglaDistanciaException.class, () -> {
            jugador.construir(new Poblado(), v2);
        });
    }

    @Test
    public void test03MejorarPobladoACiudadConsumeRecursosYAumentaPV() {
        Jugador jugador = new Jugador("Alcalde", new Inventario(new Mineral(), new Mineral(), new Mineral(),
                                                                        new Grano(), new Grano(), new Grano(),
                                                                        new Ladrillo(),new Madera(), new Lana()));
        Vertice vertice = new Vertice();


        jugador.construir(new Poblado(), vertice);

        assertEquals(1, vertice.puntoVictoria());

        jugador.mejorarConstruccionUbicadoEn(vertice);

        assertEquals(0, jugador.cantidadCartas());

        assertEquals(2, vertice.puntoVictoria());
    }

   @Test
    public void test04ComprarCartaDesarrolloConsumeRecursosYVaAManoOculta() {
        Jugador jugador = new Jugador("Estratega", new Inventario(new Lana(), new Grano(), new Mineral()));
        Mazo mazo = new Mazo();

        jugador.compraCartaDesarrollo(mazo.entregarCarta());

        assertEquals(3, jugador.cantidadCartas());

        assertEquals(1, jugador.cantidadCartasDesarrollo());
    }

    @Test
    public void test05CartaCompradaNoSePuedeJugarEnElMismoTurno() {
        Jugador jugador = new Jugador("Impaciente", new Inventario(new Lana(), new Grano(), new Mineral()));
        Mazo mazo = new Mazo();

        jugador.compraCartaDesarrollo(mazo.entregarCarta());

    }

    @Test
    public void noSePuedeConstruirCarreteraQueNoSeaAdyacenteALaRed() {
        Jugador jugador = new Jugador("Ale", new Inventario(new Madera(), new Madera(), new Ladrillo(), new Ladrillo(),
                                        new Madera(), new Ladrillo(),new Lana(), new Grano()));

        Vertice v1 = new Vertice();
        Vertice v2 = new Vertice();
        Vertice v3 = new Vertice();
        Vertice v4 = new Vertice();

        Arista aristaDondeSePuede = new Arista(v1, v2);
        Arista aristaLejana = new Arista(v3, v4);

        jugador.construir( new Poblado(), v1);

        jugador.construir(new Carretera(new ReglaCostoConstruccion()),aristaDondeSePuede, v1,v2);

        assertThrows(NoSePuedeConstruirCarreteraError.class,
                () -> jugador.construir(new Carretera(new ReglaCostoConstruccion()),
                        aristaLejana,v3,v4));
    }


    @Test
    void test07cuandoSaleNumeroJugadorRecibeUnRecursoPorPoblado() {
        Tablero tablero = new Tablero();
        Jugador jugador = new Jugador("Juan", new Inventario());

        Poblado p = new Poblado();
        p.asignarJugador(jugador);

        Hexagono hexagono = new Hexagono(new Madera(), 6);
        tablero.agregarHexagono(hexagono);
        Vertice vertice = new Vertice();

        vertice.agregarHexagono(hexagono);

        vertice.construir(p);

        tablero.producirRecursosSegun(6);

        assertEquals(1, jugador.cantidadCartas());
    }

}
