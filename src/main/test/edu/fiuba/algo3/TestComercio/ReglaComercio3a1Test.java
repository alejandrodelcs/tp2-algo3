package edu.fiuba.algo3.TestComercio;

import edu.fiuba.algo3.modelo.Comercio.ReglaComercio;
import edu.fiuba.algo3.modelo.Comercio.ReglaComercio3a1;
import edu.fiuba.algo3.modelo.Excepciones.ComercioInvalido3a1;
import edu.fiuba.algo3.modelo.Recurso.Ladrillo;
import edu.fiuba.algo3.modelo.Recurso.Lana;
import edu.fiuba.algo3.modelo.Recurso.Madera;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;

public class ReglaComercio3a1Test {

    @Test
    public void test01FallaSiCantidadNoEs3a1() {
        ReglaComercio regla = new ReglaComercio3a1();

        assertThrows(
                ComercioInvalido3a1.class,
                () -> regla.validar(
                        List.of(Ladrillo.class, Ladrillo.class),
                        List.of(Lana.class)
                )
        );
    }

    @Test
    public void test02ValidaCorrectamenteSiLaProporcionEs3a1(){
        ReglaComercio regla = new ReglaComercio3a1();

        assertDoesNotThrow(() -> regla.validar(
            List.of(Madera.class, Madera.class, Madera.class), 
            List.of(Lana.class))
        );
    }

    @Test
    public void test03ValidaCorrectamenteIntercambioCuandoLaProporcionAumenta(){
        ReglaComercio regla = new ReglaComercio3a1();

        assertDoesNotThrow(() -> regla.validar(
            List.of(Madera.class, Madera.class, Madera.class, Madera.class, Madera.class, Madera.class), 
            List.of(Lana.class, Lana.class))
        );
    }
}
