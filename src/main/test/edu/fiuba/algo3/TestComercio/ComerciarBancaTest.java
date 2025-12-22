package edu.fiuba.algo3.TestComercio;

import edu.fiuba.algo3.modelo.Comercio.*;
import edu.fiuba.algo3.modelo.Excepciones.ComercioInvalido4a1;
import edu.fiuba.algo3.modelo.Jugador.Inventario;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Ladrillo;
import edu.fiuba.algo3.modelo.Recurso.Lana;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;



public class ComerciarBancaTest {
    @Test
    public void test01ElJugadorComercia4a1ConLaBanca() {

        Jugador jugador = new Jugador("Romina",
                new Inventario(
                        new Ladrillo(), new Ladrillo(),
                        new Ladrillo(), new Ladrillo()
                ));

        Banca banca = new Banca();

        Comercio comercio = new ComercioBanca(
                List.of(
                        Ladrillo.class, Ladrillo.class,
                        Ladrillo.class, Ladrillo.class
                ),
                List.of(Lana.class),
                new ReglaComercio4a1(),
                banca
        );

        comercio.aplicarSobre(jugador);

        assertEquals(1, jugador.cantidadCartas());
    }


    @Test
    public void test02regla4a1RechazaCantidadIncorrecta() {
        ReglaComercio regla = new ReglaComercio4a1();

        assertThrows(
                ComercioInvalido4a1.class,
                () -> regla.validar(
                        List.of(Ladrillo.class, Ladrillo.class, Ladrillo.class),
                        List.of(Lana.class)
                )
        );
    }

    @Test
    public void test03LaBancaFallaAlIntentarInstanciarUnaClaseAbstracta() {
        Banca banca = new Banca();
        Jugador jugador = new Jugador("Tester", new Inventario());

        assertThrows(RuntimeException.class, () -> {
            banca.entregarTipos(jugador, List.of(Recurso.class));
        });
    }

}
