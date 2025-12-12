package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Carta.CartaCaballero;
import edu.fiuba.algo3.modelo.Construccion.Poblado;
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
                new Inventario(new Ladrillo(), new Ladrillo(), new Madera()));

        juego = new Juego();
        List<String> nombres = Arrays.asList("ale", "mary");
        List<String> avates = Arrays.asList(".../ruta1", ".../ruta2");
        juego.crearJugadores(nombres, avates);

        tablero = new Tablero();

    }

    @Test
    public void test01ElJugadorUsarCartaCaballeroDeberiaPoderMoverLadronYRobarRecurso() {
        Hexagono origen = new Hexagono(new Desierto(), -1);
        Hexagono destino = new Hexagono(new Desierto(), 6);
        Poblado poblado = new Poblado();

        tablero.agregarHexagono(destino);
        tablero.agregarHexagono(origen);
        tablero.colocarLadronEn(origen);

        Vertice v = new Vertice();
        destino.agregarVertice(v);
        poblado.asignarJugador(jugadorVictima);
        v.construir(poblado);

        carta.jugar(jugadorActivo, tablero, destino);

        assertEquals(3, jugadorActivo.cantidadCartas());
        assertEquals(2, jugadorVictima.cantidadCartas());

    }

}
