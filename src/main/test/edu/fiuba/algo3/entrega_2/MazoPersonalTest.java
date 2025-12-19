package edu.fiuba.algo3.entrega_2;

import org.junit.Test;
import edu.fiuba.algo3.modelo.Carta.*;
import edu.fiuba.algo3.modelo.Excepciones.NoTieneCartaError;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class MazoPersonalTest {
    @Test
    public void test01MazoPersonalAgregaYCuentaCartasCorrectamente(){
        MazoPersonal mazo = new MazoPersonal();
        CartaCaballero caballero = new CartaCaballero();
        CartaPuntoVictoria pv = new CartaPuntoVictoria();

        mazo.agregar(caballero);
        mazo.agregar(pv);

        assertEquals(2, mazo.total());
        assertEquals(1, mazo.cantidadDeTipo(pv));
        assertEquals(1, mazo.cantidadDeTipo(caballero));
    }

    @Test
    public void test02ConsumirCartaLaEliminaDelMazoSiEstaDisponible() {
        MazoPersonal mazo = new MazoPersonal();
        CartaCaballero caballero = new CartaCaballero();
        
        caballero.habilitar(); 
        mazo.agregar(caballero);

        assertDoesNotThrow(() -> mazo.consumir(new CartaCaballero()));
        assertEquals(0, mazo.total());
    }

    @Test
    public void test03NoSePuedeConsumirUnaCartaQueNoSeTiene() {
        MazoPersonal mazo = new MazoPersonal();

        assertThrows(NoTieneCartaError.class, () -> mazo.consumir(new CartaCaballero()));
    }

    @Test
    public void test04NoSePuedeConsumirUnaCartaQueEstaDeshabilitada() {
        MazoPersonal mano = new MazoPersonal();
        CartaCaballero caballero = new CartaCaballero();
        
        caballero.deshabilitar();
        mano.agregar(caballero);

        assertThrows(NoTieneCartaError.class, () -> mano.consumir(new CartaCaballero()));
        assertEquals(1, mano.total());
    }
}
