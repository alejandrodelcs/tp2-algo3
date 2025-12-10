package edu.fiuba.algo3.entrega_2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import edu.fiuba.algo3.modelo.Carta.CartaDescubrimiento;
import edu.fiuba.algo3.modelo.Jugador.Inventario;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Grano;
import edu.fiuba.algo3.modelo.Recurso.Madera;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class CartaDescubrimientoTest {
    private CartaDescubrimiento cartaDescubrimiento;
    private Jugador jugador;
    private Tablero tablero;


    @BeforeEach
    public void setUp() {
        cartaDescubrimiento = new CartaDescubrimiento();
        jugador = new Jugador("A", new Inventario());
        tablero = new Tablero();
    }

    @Test
    public void test01JugadorRecibeDosRecursos() {

        assertEquals(0, jugador.consultarRecursos());

        cartaDescubrimiento.jugar(jugador, tablero, new Madera(), new Grano());

        assertEquals(1, jugador.cantidadDe(Madera.class));
        assertEquals(1, jugador.cantidadDe(Grano.class));
    }
}
