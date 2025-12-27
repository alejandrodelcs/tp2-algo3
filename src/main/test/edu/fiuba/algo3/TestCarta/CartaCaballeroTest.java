package edu.fiuba.algo3.TestCarta;

import edu.fiuba.algo3.modelo.Carta.CartaCaballero;
import edu.fiuba.algo3.modelo.Construccion.Poblado;
import edu.fiuba.algo3.modelo.Construccion.ReglaAdyacencia;
import edu.fiuba.algo3.modelo.Construccion.ReglaDistancia;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Inventario;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Ladron.Ladron;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Hexagono;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Vertice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartaCaballeroTest {
    private CartaCaballero carta;
    private Jugador jugadorActivo;
    private Jugador jugadorVictima;
    private Tablero tablero;
    private Ladron ladron;
    private Juego juego;

    @BeforeEach
    public void setUp() {
        carta = new CartaCaballero();

        jugadorActivo = new Jugador("A",
                new Inventario(new Madera(), new Grano()));

        jugadorVictima = new Jugador("B",
                new Inventario(new Ladrillo(), new Lana(), new Madera(), new Grano(), new Madera(), new Grano(),
                        new Ladrillo()));
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(jugadorActivo);
        jugadores.add(jugadorVictima);

        tablero = new Tablero();
        juego = new Juego(jugadores);

    }

    @Test
    public void test01ElJugadorUsarCartaCaballeroDeberiaPoderMoverLadronYRobarRecurso() {
        Hexagono origen = new Hexagono(new Desierto(), -1);
        Hexagono destino = new Hexagono(new Desierto(), 6);
        Poblado poblado = new Poblado(new ReglaDistancia());

        tablero.agregarHexagono(destino);
        tablero.agregarHexagono(origen);
        tablero.colocarLadronEn(origen);

        Vertice v = new Vertice();
        destino.agregarVertice(v);
        jugadorVictima.construir(poblado, v);

        carta.habilitar();
        carta.jugar(jugadorActivo, tablero, juego);

        juego.turnoActual().moverLadronA(destino);
        juego.turnoActual().robar(jugadorVictima);

        assertEquals(3, jugadorActivo.cantidadCartas());
        assertEquals(2, jugadorVictima.cantidadCartas());

    }

}
