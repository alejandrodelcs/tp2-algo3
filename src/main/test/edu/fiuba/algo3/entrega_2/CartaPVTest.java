package edu.fiuba.algo3.entrega_2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import edu.fiuba.algo3.modelo.Carta.CartaPuntoVictoria;
import edu.fiuba.algo3.modelo.Dado.Dado;
import edu.fiuba.algo3.modelo.Jugador.Inventario;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Turno.EstadoAcciones;
import edu.fiuba.algo3.modelo.Turno.Turno;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CartaPVTest {
    private CartaPuntoVictoria carta;
    private Turno turno;
    private Jugador jugador;

    @BeforeEach
    public void setUp() {
        carta = new CartaPuntoVictoria();
        jugador = new Jugador("A", new Inventario());
        turno = new Turno(jugador, new Tablero(), new Dado());
    }

    @Test
    public void test01CartaSumaUnPuntoDeVictoria() {
        turno.cambiarEstado(new EstadoAcciones());

        turno.jugarCarta(carta);

        assertEquals(1, jugador.getPuntosVictoria());

    }

}
