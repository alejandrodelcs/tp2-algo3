package edu.fiuba.algo3.entrega_2;

import java.util.List;

import edu.fiuba.algo3.modelo.Comercio.ComercioBanca;
import edu.fiuba.algo3.modelo.Comercio.ComercioInterior;
import edu.fiuba.algo3.modelo.Comercio.ReglaComercio4a1;
import edu.fiuba.algo3.modelo.Excepciones.ComercioInvalido4a1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Comercio.Banca;
import edu.fiuba.algo3.modelo.Dado.Dado;
import edu.fiuba.algo3.modelo.Excepciones.NoHayRecursoDisponibleError;
import edu.fiuba.algo3.modelo.Jugador.*;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Turno.Turno;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccionComercioTest {

    private Jugador jugadorActivo, otroJugadorRecibe;
    private Banca banca;
    private Turno turno;
    private Dado mockDado;
    private List<Class<? extends Recurso>> entrega, recibe, entregaParaBanca, recibeDeBanca, entregaParaBanca2;

    @BeforeEach
    public void setUp() {
        this.entrega = List.of(Ladrillo.class, Grano.class);
        this.recibe = List.of(Mineral.class);

        this.entregaParaBanca = List.of(Ladrillo.class, Ladrillo.class, Ladrillo.class, Ladrillo.class);
        this.entregaParaBanca2 = List.of(Grano.class, Grano.class, Grano.class, Grano.class);
        this.recibeDeBanca = List.of(Lana.class);

        Tablero tablero = new Tablero();
        this.jugadorActivo = new Jugador("Romina",
                new Inventario(new Ladrillo(), new Ladrillo(), new Ladrillo(), new Ladrillo(), new Grano()));
        this.otroJugadorRecibe = new Jugador("Ana", new Inventario(new Lana(), new Mineral()));

        this.turno = new Turno(jugadorActivo, tablero);

        this.mockDado = mock(Dado.class);
        when(mockDado.generar()).thenReturn(2);
        turno.tirarDado(mockDado);

        this.banca = new Banca();
    }

    @Test
    public void test01UnJugadorComerciaConOtroJugador() {

        ComercioInterior comercioInterior = new ComercioInterior(entrega, recibe, otroJugadorRecibe);

        this.turno.comerciar(comercioInterior);

        assertEquals(5, jugadorActivo.cantidadCartas());
        assertEquals(2, otroJugadorRecibe.cantidadCartas());

    }

    @Test
    public void test02UnJugadorComerciaConBanca() {
        ComercioBanca comercioBanca = new ComercioBanca(entregaParaBanca, recibeDeBanca,
                                                                                new ReglaComercio4a1(), banca);

        turno.comerciar(comercioBanca);

        assertEquals(2, jugadorActivo.cantidadCartas());
    }

    @Test
    public void test03UnJugadorNoTieneLosRecursosParaBanca() {
        ComercioBanca comercioBanca = new ComercioBanca(entregaParaBanca2, recibeDeBanca,
                                                                                    new ReglaComercio4a1(), banca);

        assertThrows(NoHayRecursoDisponibleError.class, () -> turno.comerciar(comercioBanca));

    }

    @Test
    public void test04UnIntercambioLanzaErrorSiElPedidoTieneMenosDe4Recursos() {

        List<Class<? extends Recurso>> pedido = List.of(Ladrillo.class, Ladrillo.class, Ladrillo.class);

        assertThrows(ComercioInvalido4a1.class,
                () -> new ComercioBanca(pedido, recibeDeBanca, new ReglaComercio4a1(), banca));
    }

    @Test
    public void test05UnPedidoDe8Devuevle2RecursosABanca() {

        Jugador jug = new Jugador("ahfae", new Inventario(new Ladrillo(), new Ladrillo(), new Ladrillo(),
                new Ladrillo(), new Ladrillo(), new Ladrillo(), new Ladrillo(),
                new Ladrillo()));

        Turno turn = new Turno(jug, new Tablero());
        turn.tirarDado(mockDado);

        List<Class<? extends Recurso>> entrega8 = List.of(Ladrillo.class, Ladrillo.class, Ladrillo.class,
                Ladrillo.class, Ladrillo.class, Ladrillo.class, Ladrillo.class,
                Ladrillo.class);

        List<Class<? extends Recurso>> recibe2 = List.of(Lana.class, Lana.class);

        ComercioBanca comercioBanca = new ComercioBanca(entrega8, recibe2,
                                                    new ReglaComercio4a1(), banca);

        turn.comerciar(comercioBanca);

        assertEquals(2, jug.cantidadCartas());
    }

}
