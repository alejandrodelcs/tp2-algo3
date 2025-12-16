package edu.fiuba.algo3.modelo.Comercio;

import java.util.List;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

/**
 * ComercioEntreJugadores
 */
public class ModoEntreJugadores implements ModoDeComercio {
    @Override
    public void ejecutar(Juego juego,
            Jugador jugadorDestino,
            List<Class<? extends Recurso>> oferta,
            List<Class<? extends Recurso>> demanda) {

        Comercio comercio = new ComercioInterior(
                oferta,
                demanda,
                juego.getJugadorActivo());

        comercio.aplicarSobre(jugadorDestino);
    }

}
