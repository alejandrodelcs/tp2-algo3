package edu.fiuba.algo3.modelo.Comercio;

import java.util.List;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

/**
 * ModoDeComercio
 */
public interface ModoDeComercio {
    void ejecutar(Juego juego,
            Jugador jugadorDestino,
            List<Class<? extends Recurso>> oferta,
            List<Class<? extends Recurso>> demanda);
}
