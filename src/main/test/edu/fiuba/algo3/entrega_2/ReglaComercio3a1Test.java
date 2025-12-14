package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Comercio.ReglaComercio;
import edu.fiuba.algo3.modelo.Comercio.ReglaComercio3a1;
import edu.fiuba.algo3.modelo.Excepciones.ComercioInvalido3a1;
import edu.fiuba.algo3.modelo.Recurso.Ladrillo;
import edu.fiuba.algo3.modelo.Recurso.Lana;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;

public class ReglaComercio3a1Test {

    @Test
    public void fallaSiCantidadNoEs3a1() {
        ReglaComercio regla = new ReglaComercio3a1();

        assertThrows(
                ComercioInvalido3a1.class,
                () -> regla.validar(
                        List.of(Ladrillo.class, Ladrillo.class),
                        List.of(Lana.class)
                )
        );
    }
}
