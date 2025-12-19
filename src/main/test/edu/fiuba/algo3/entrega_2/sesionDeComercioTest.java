package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Comercio.*;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Inventario;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class sesionDeComercioTest {
    @Test
    public void test01ModoConBancaRealizaComercio4a1ConJugador(){
        Jugador j = new Jugador("Pablo", new Inventario(new Madera(), new Madera(), new Madera(), new Madera()));
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(j);

        Juego juego = new Juego(jugadores);
        SesionDeComercio sesion = new SesionDeComercio(new ModoConBanca());

        Map<Class<? extends Recurso>, Integer> oferta = new HashMap<>();
        oferta.put(Madera.class, 4);
        sesion.setOferta(oferta);

        Map<Class<? extends Recurso>, Integer> demanda = new HashMap<>();
        demanda.put(Lana.class, 1);
        sesion.setDemanda(demanda);

        sesion.ejecutar(juego, null);

        assertEquals(0, j.cantidadDe(Madera.class));
        assertEquals(1, j.cantidadDe(Lana.class));
    }


    @Test
    public void test02ModoEntreJugadoresRealizaComercioEntreEllos(){
        Jugador j1 = new Jugador("Jugador1", new Inventario(new Madera()));
        Jugador j2 = new Jugador("Jugador2", new Inventario(new Ladrillo()));
        ArrayList<Jugador> jugadores = new ArrayList<>();
        jugadores.add(j1);
        jugadores.add(j2);

        Juego juego = new Juego(jugadores);
        SesionDeComercio sesion = new SesionDeComercio(new ModoEntreJugadores());

        Map<Class<? extends Recurso>, Integer> oferta = new HashMap<>();
        oferta.put(Madera.class, 1);
        sesion.setOferta(oferta);

        Map<Class<? extends Recurso>, Integer> demanda = new HashMap<>();
        demanda.put(Ladrillo.class, 1);
        sesion.setDemanda(demanda);

        sesion.ejecutar(juego, j2);

        assertEquals(0, j1.cantidadDe(Madera.class));
        assertEquals(1, j1.cantidadDe(Ladrillo.class));

        assertEquals(0, j2.cantidadDe(Ladrillo.class));
        assertEquals(1, j2.cantidadDe(Madera.class));
    }
}
