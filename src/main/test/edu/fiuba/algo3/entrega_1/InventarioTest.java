package edu.fiuba.algo3.entrega_1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Set;

import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Recurso.*;

/**
 * InventarioTest
 */
public class InventarioTest {

    @Test
    public void test01UnInventarioCon5Elementos() {
        Inventario inventario = new Inventario();

        inventario.agregar(new Madera());
        assertEquals(1, inventario.cantidadDeTipo(Madera.class));
    }

    @Test
    public void test02AgregarVariosRecursosSumaCorrectamente() {
        Inventario inventario = new Inventario();

        inventario.agregar(new Madera());
        inventario.agregar(new Madera());
        inventario.agregar(new Lana());

        assertEquals(2, inventario.cantidadDeTipo(Madera.class));
        assertEquals(1, inventario.cantidadDeTipo(Lana.class));

    }

    @Test
    public void test03AgregarRecursosYelTotalEsCorrecto() {
        Inventario inventario = new Inventario();

        inventario.agregar(new Madera());
        inventario.agregar(new Madera());
        inventario.agregar(new Lana());

        assertEquals(3, inventario.total());

    }

    @Test
    public void test04AgregoUnaListaDeRecursosAlInventario() {

        Inventario inventario = new Inventario();
        ArrayList<Recurso> recursos = new ArrayList<>();
        recursos.add(new Madera());
        recursos.add(new Madera());
        recursos.add(new Lana());
        recursos.add(new Lana());

        inventario.agregarTodos(recursos);

        assertEquals(4, inventario.total());
    }

    @Test
    public void test05QuitarUnoReduceLaCantidadDelTipo() {
        Inventario inventario = new Inventario();
        inventario.agregar((new Madera()));
        inventario.agregar((new Madera()));

        Recurso recurso = inventario.quitarUno(Madera.class);

        assertEquals(1, inventario.cantidadDeTipo(Madera.class));
    }

    @Test
    public void test06QuitarUnoDeUnTipoNoExisteDevuelveNull() {
        Inventario inventario = new Inventario();
        Recurso recurso = inventario.quitarUno(Ladrillo.class);

        assertNull(recurso);
    }

    @Test
    public void test07RoBarUnoDevuelveAlgunaCartaYReduceElTotal() {
        Inventario inventario = new Inventario();

        inventario.agregar(new Madera());
        inventario.agregar(new Lana());
        inventario.agregar(new Mineral());

        int antes = inventario.total();

        Recurso r = inventario.robarUno();

        assertNotNull(r);
        assertEquals(antes - 1, inventario.total());
    }

    @Test
    public void test08TiposDisponiblesDevuelveLasClasesCorrectas() {
        Inventario inventario = new Inventario();

        inventario.agregar(new Madera());
        inventario.agregar(new Lana());

        Set<Class<? extends Recurso>> tipos = inventario.tiposDisponibles();

        assertTrue(tipos.contains(Madera.class));
        assertTrue(tipos.contains(Lana.class));
        assertEquals(2, tipos.size());
    }

    @Test
    public void test09ReduceirALaMitadEliminaLaCantidadCorrecta() {
        Inventario inventario = new Inventario();

        // Total: 9
        inventario.agregar(new Madera());
        inventario.agregar(new Madera());
        inventario.agregar(new Madera());
        inventario.agregar(new Lana());
        inventario.agregar(new Lana());
        inventario.agregar(new Lana());
        inventario.agregar(new Mineral());
        inventario.agregar(new Mineral());
        inventario.agregar(new Mineral());

        inventario.reducirALaMitad();

        // Se queda con floor(9/2)=4
        assertEquals(4, inventario.total());
    }
}
