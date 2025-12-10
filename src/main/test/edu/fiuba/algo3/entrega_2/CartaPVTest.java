package edu.fiuba.algo3.entrega_2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import edu.fiuba.algo3.modelo.Carta.CartaPuntoVictoria;
import edu.fiuba.algo3.modelo.Jugador.Inventario;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CartaPVTest {
    private CartaPuntoVictoria carta;
    private Jugador jugador;

    @BeforeEach
    public void setUp() {
        carta = new CartaPuntoVictoria();
        jugador = new Jugador("A", new Inventario());
    }

    @Test
    public void test01CartaSumaUnPuntoDeVictoria() {

        assertEquals(0, jugador.getPuntosVictoria());

        carta.jugar(jugador, null);

        assertEquals(1, jugador.getPuntosVictoria());
    }
}
