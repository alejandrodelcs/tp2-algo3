package edu.fiuba.algo3.entrega_2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.fiuba.algo3.modelo.Carta.CartaCaballero;
import edu.fiuba.algo3.modelo.Carta.CartaPuntoVictoria;
import edu.fiuba.algo3.modelo.Jugador.Inventario;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Turno.Turno;

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

    @Test
    public void test02CartaPuntoVictoriaNoEsDeUnSoloUso() {
        assertFalse(carta.esDeUnSoloUso());
    }

    @Test
    public void test04ManejoDeDisponibilidadHeredado() {
        assertFalse(carta.estaDisponible());

        carta.habilitar();
        assertTrue(carta.estaDisponible());

        carta.deshabilitar();
        assertFalse(carta.estaDisponible());
    }

    @Test
    public void test05EsDelMismoTipoQueFuncionaCorrectamente() {
        CartaPuntoVictoria otraCartaPV = new CartaPuntoVictoria();
        CartaCaballero cartaDiferente = new CartaCaballero();

        assertTrue(carta.esDelMismoTipoQue(otraCartaPV));
        assertFalse(carta.esDelMismoTipoQue(cartaDiferente));
    }
}
