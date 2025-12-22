package edu.fiuba.algo3.TestCarta;

import edu.fiuba.algo3.modelo.Carta.CartaMonopolio;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Inventario;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Ladrillo;
import edu.fiuba.algo3.modelo.Recurso.Madera;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartaMonopolioTest {
    private CartaMonopolio carta;
    private Jugador jugadorA;
    private Jugador jugadorB;
    private Jugador jugadorC;
    private Juego juego;
    private Tablero tablero;

    @BeforeEach
    public void setUp() {
        carta = new CartaMonopolio();

        jugadorA = new Jugador("A", new Inventario());
        jugadorB = new Jugador("B",
                new Inventario(new Madera(), new Madera(), new Ladrillo()));
        jugadorC = new Jugador("C",
                new Inventario(new Madera(), new Ladrillo()));

        juego = new Juego();


        juego.agregarJugador(jugadorA);
        juego.agregarJugador(jugadorB);
        juego.agregarJugador(jugadorC);
        tablero = new Tablero();
    }

    @Test
    public void test01MonopolioRobaMaderasDeTodosMenosJugadorActivo() {

        assertEquals(0, jugadorA.cantidadCartas());

        carta.jugar(jugadorA, tablero, new Madera(), juego);

        // B tenía 2 maderas → ahora 0
        // C tenía 1 madera → ahora 0
        assertEquals(0, jugadorB.cantidadDe(Madera.class));
        assertEquals(0, jugadorC.cantidadDe(Madera.class));

        // A recibe 3
        assertEquals(3, jugadorA.cantidadDe(Madera.class));
    }
}
