package edu.fiuba.algo3.modelo.Comercio;

import java.util.List;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

/**
 * ModoComercioBanca
 */
public class ModoConBanca implements ModoDeComercio {

    @Override
    public void ejecutar(Juego juego,
            Jugador jugadorDestino,
            List<Class<? extends Recurso>> oferta,
            List<Class<? extends Recurso>> demanda) {

        ComercioBanca comercio = new ComercioBanca(
                oferta,
                demanda,
                new ReglaComercio4a1(),
                new Banca());

        comercio.aplicarSobre(juego.getJugadorActivo());
    }

}
