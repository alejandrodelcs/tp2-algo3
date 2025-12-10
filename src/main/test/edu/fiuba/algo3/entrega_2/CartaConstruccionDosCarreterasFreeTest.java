package edu.fiuba.algo3.entrega_2;

import static org.junit.jupiter.api.Assertions.assertTrue;
import edu.fiuba.algo3.modelo.Carta.CartaConstruccionCarreteras;
import edu.fiuba.algo3.modelo.Construccion.Poblado;
import edu.fiuba.algo3.modelo.Jugador.Inventario;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Arista;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Vertice;
import org.junit.jupiter.api.Test;

public class CartaConstruccionDosCarreterasFreeTest {
    @Test
    public void test01test01ElJugadorUsaCartaConstruccionCarreterasYColocaDosCarreterasGratis(){
        Jugador jugador = new Jugador("Axel", new Inventario());
        Vertice v1 = new Vertice();
        Vertice v2 = new Vertice();
        Vertice v3 = new Vertice();

        Arista a1 = new Arista(v1, v2);
        Arista a2 = new Arista(v2, v3);

        Tablero tablero = new Tablero();

        Poblado p = new Poblado();
        p.asignarJugador(jugador);

        v1.construir(p);

        CartaConstruccionCarreteras carta = new CartaConstruccionCarreteras();

        carta.jugar(jugador, tablero, a1, a2);

        assertTrue(a1.tieneCarreteraDel(jugador));
        assertTrue(a2.tieneCarreteraDel(jugador));

    }
}
