package edu.fiuba.algo3.TestJugador;

import java.util.Set;

import edu.fiuba.algo3.modelo.Excepciones.NoHayRecursoDisponibleError;
import edu.fiuba.algo3.modelo.Jugador.Inventario;
import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.Recurso.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * InventarioTest
 */
public class InventarioTest {

    @Test
    public void test01UnInventarioCon5Elementos() {
        Inventario inventario = new Inventario(new Madera());

        assertEquals(1, inventario.cantidadDeTipo(Madera.class));
    }

    @Test
    public void test02AgregarVariosRecursosSumaCorrectamente() {
        Inventario inventario = new Inventario(new Madera(), new Madera(), new Lana());


        assertEquals(2, inventario.cantidadDeTipo(Madera.class));
        assertEquals(1, inventario.cantidadDeTipo(Lana.class));

    }

    @Test
    public void test03AgregarRecursosYelTotalEsCorrecto() {
        Inventario inventario = new Inventario(new Madera(), new Madera(), new Lana());

        assertEquals(3, inventario.total());

    }

    @Test
    public void test04AgregoUnaListaDeRecursosAlInventario() {

        Inventario inventario = new Inventario(new Madera(), new Madera(), new Lana(),  new Lana());

        assertEquals(4, inventario.total());
    }

    @Test
    public void test05ConsumirReduceLaCantidadDelTipo() {
        Inventario inventario = new Inventario(new Madera(), new Madera());
        inventario.consumir(Madera.class);

        assertEquals(1, inventario.cantidadDeTipo(Madera.class));
    }

    @Test
    public void test06ConsumirDeUnTipoNoExisteDevuelveUnaExcepcion() {
        Inventario inventario = new Inventario(new Madera());

        assertThrows(NoHayRecursoDisponibleError.class, ()->inventario.consumir(Ladrillo.class));
    }

    @Test
    public void test07RoBarUnoDevuelveAlgunaCartaYReduceElTotal() {
        Inventario inventario = new Inventario(new Madera(), new Lana(),  new Mineral());

        int antes = inventario.total();

        Recurso r = inventario.robarUno();

        assertNotNull(r);
        assertEquals(antes - 1, inventario.total());
    }

    @Test
    public void test08TiposDisponiblesDevuelveLasClasesCorrectas() {
        Inventario inventario = new Inventario(new Madera(), new Lana());

        Set<Class<? extends Recurso>> tipos = inventario.tiposDisponibles();

        assertTrue(tipos.contains(Madera.class));
        assertTrue(tipos.contains(Lana.class));
        assertEquals(2, tipos.size());
    }

    @Test
    public void test09ReduceirALaMitadEliminaLaCantidadCorrecta() {
        Inventario inventario = new Inventario(new Madera(),new Madera(), new Madera(), new Lana(),
                                                new Lana(), new Lana(), new Mineral(), new Mineral());

        inventario.descartarMitad();

        // Se queda con floor(9/2)=4
        assertEquals(4, inventario.total());
    }
}
