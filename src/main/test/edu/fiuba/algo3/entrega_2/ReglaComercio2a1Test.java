package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Comercio.ReglaComercio;
import edu.fiuba.algo3.modelo.Comercio.ReglaComercio2a1;
import edu.fiuba.algo3.modelo.Excepciones.ComercioInvalido2a1;
import edu.fiuba.algo3.modelo.Recurso.Grano;
import edu.fiuba.algo3.modelo.Recurso.Ladrillo;
import edu.fiuba.algo3.modelo.Recurso.Lana;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReglaComercio2a1Test {


    @Test
    public void test02SiEntregaNoEsDelTipoCorrectoDeberiaLanzarUnaExcepcion() {
        ReglaComercio regla = new ReglaComercio2a1(Ladrillo.class);

        assertThrows(
                ComercioInvalido2a1.class,
                () -> regla.validar(
                        List.of(Ladrillo.class, Grano.class),
                        List.of(Lana.class)
                )
        );
    }

}
