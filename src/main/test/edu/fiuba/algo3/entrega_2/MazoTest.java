package edu.fiuba.algo3.entrega_2;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.fiuba.algo3.modelo.Carta.CartaDesarrollo;
import edu.fiuba.algo3.modelo.Carta.CartaPuntoVictoria;
import edu.fiuba.algo3.modelo.Carta.Mazo;
import edu.fiuba.algo3.modelo.Excepciones.MazoVacioException;
import edu.fiuba.algo3.modelo.Excepciones.NoHayRecursoDisponibleError;
import edu.fiuba.algo3.modelo.Jugador.Inventario;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Grano;
import edu.fiuba.algo3.modelo.Recurso.Lana;
import edu.fiuba.algo3.modelo.Recurso.Mineral;
import org.junit.jupiter.api.Test;

public class MazoTest {

    @Test
    public void test01ComprarCartaDesarrolloDescuentaRecursos() {
        Inventario inventario = new Inventario(new Lana(), new Grano(), new Mineral());
        Jugador jugador = new Jugador("Ale", inventario);

        Mazo mazo = new Mazo();

        mazo.comprarCarta(jugador);

        assertEquals(1, jugador.cantidadCartasDesarrollo());
        assertEquals(0, jugador.cantidadDe(Lana.class));
        assertEquals(0, jugador.cantidadDe(Grano.class));
        assertEquals(0, jugador.cantidadDe(Mineral.class));
    }

    @Test
    public void test02NoSePuedeComprarCartaSiNoHayRecursosSuficientes() {
        Inventario inventario = new Inventario(new Lana()); // Solo 1 recurso
        Jugador jugador = new Jugador("Ale", inventario);

        Mazo mazo = new Mazo();

        assertThrows(NoHayRecursoDisponibleError.class, () -> mazo.comprarCarta(jugador));
    }

    @Test
    public void test03ElMazoTiene25CartasYLanzaExcepcionAlQuedarVacio() {
        Jugador jugador = new Jugador("Ricardo", new Inventario());
        Mazo mazo = new Mazo();

        for (int i = 0; i < 25; i++) {
            jugador.recibirRecurso(new Lana());
            jugador.recibirRecurso(new Grano());
            jugador.recibirRecurso(new Mineral());

            assertDoesNotThrow(() -> mazo.comprarCarta(jugador));
        }

        jugador.recibirRecurso(new Lana());
        jugador.recibirRecurso(new Grano());
        jugador.recibirRecurso(new Mineral());

        assertThrows(MazoVacioException.class, () -> mazo.comprarCarta(jugador));
    }

}
