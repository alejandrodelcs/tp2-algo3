package edu.fiuba.algo3.entrega_2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import edu.fiuba.algo3.modelo.Carta.MazoDesarrollo;
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

        MazoDesarrollo mazo = new MazoDesarrollo();


        mazo.comprarCarta(jugador);

        assertEquals(0, jugador.cantidadCartas());
    }

    @Test
    public void test02NoSePuedeComprarCartaSiNoHayRecursosSuficientes() {
        Inventario inventario = new Inventario(new Lana()); // Solo 1 recurso
        Jugador jugador = new Jugador("Ale", inventario);

        MazoDesarrollo mazo = new MazoDesarrollo();

        assertThrows(NoHayRecursoDisponibleError.class, () -> mazo.comprarCarta(jugador));
    }


}
