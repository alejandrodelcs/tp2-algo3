package edu.fiuba.algo3.TestComercio;

import java.util.List;

import edu.fiuba.algo3.modelo.Comercio.*;
import edu.fiuba.algo3.modelo.Excepciones.ComercioInvalido1a1;

import org.junit.jupiter.api.Test;
import edu.fiuba.algo3.modelo.Jugador.*;
import edu.fiuba.algo3.modelo.Recurso.*;
import static org.junit.jupiter.api.Assertions.*;

public class ComercioInteriorTest {

    @Test
    public void test01UnJugadorIntercambiaRecursosConOtro() {

        Jugador oferente = new Jugador("Ana",
                new Inventario(new Ladrillo(), new Grano()));

        Jugador receptor = new Jugador("Romina",
                new Inventario(new Mineral()));

        Comercio comercio = new ComercioInterior(
                List.of(Ladrillo.class, Grano.class),
                List.of(Mineral.class),
                oferente);

        comercio.aplicarSobre(receptor);

        assertEquals(1, oferente.cantidadCartas());
        assertEquals(2, receptor.cantidadCartas());

    }

    @Test
    public void test02UnJugadorNoPuedeIntercambiarConOtroJugadorPorFaltaDeRecursos() {
        Jugador oferente = new Jugador("Ana",
                new Inventario(new Ladrillo()));

        Jugador receptor = new Jugador("Romina",
                new Inventario(new Mineral()));

        Comercio comercio = new ComercioInterior(
                List.of(Ladrillo.class, Grano.class),
                List.of(Mineral.class),
                oferente);

        assertThrows(ComercioInvalido1a1.class,
                () -> comercio.aplicarSobre(receptor));
    }
    /*
     * @Test
     * public void test02UnJugadorComerciaConBanca() {
     * ComercioBanca comercioBanca = new ComercioBanca(entregaParaBanca,
     * recibeDeBanca,
     * new ReglaComercio4a1(), banca);
     * 
     * turno.comerciar(comercioBanca);
     * 
     * assertEquals(2, jugadorActivo.cantidadCartas());
     * }
     * 
     * @Test
     * public void test03UnJugadorNoTieneLosRecursosParaBanca() {
     * ComercioBanca comercioBanca = new ComercioBanca(entregaParaBanca2,
     * recibeDeBanca,
     * new ReglaComercio4a1(), banca);
     * 
     * assertThrows(NoHayRecursoDisponibleError.class, () ->
     * turno.comerciar(comercioBanca));
     * 
     * }
     * 
     * @Test
     * public void test04UnIntercambioLanzaErrorSiElPedidoTieneMenosDe4Recursos() {
     * 
     * List<Class<? extends Recurso>> pedido = List.of(Ladrillo.class,
     * Ladrillo.class, Ladrillo.class);
     * 
     * assertThrows(ComercioInvalido4a1.class,
     * () -> new ComercioBanca(pedido, recibeDeBanca, new ReglaComercio4a1(),
     * banca));
     * }
     * 
     * @Test
     * public void test05UnPedidoDe8Devuevle2RecursosABanca() {
     * 
     * Jugador jug = new Jugador("ahfae", new Inventario(new Ladrillo(), new
     * Ladrillo(), new Ladrillo(),
     * new Ladrillo(), new Ladrillo(), new Ladrillo(), new Ladrillo(),
     * new Ladrillo()));
     * 
     * Turno turn = new Turno(jug, new Tablero(), mockDado);
     * turn.tirarDado(mock(Juego.class));
     * 
     * List<Class<? extends Recurso>> entrega8 = List.of(Ladrillo.class,
     * Ladrillo.class, Ladrillo.class,
     * Ladrillo.class, Ladrillo.class, Ladrillo.class, Ladrillo.class,
     * Ladrillo.class);
     * 
     * List<Class<? extends Recurso>> recibe2 = List.of(Lana.class, Lana.class);
     * 
     * ComercioBanca comercioBanca = new ComercioBanca(entrega8, recibe2,
     * new ReglaComercio4a1(), banca);
     * 
     * turn.comerciar(comercioBanca);
     * 
     * assertEquals(2, jug.cantidadCartas());
     * }
     */

}
