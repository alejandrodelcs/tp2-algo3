package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Juego.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Costo;
import edu.fiuba.algo3.modelo.Recurso.TipoRecurso;

public class Carretera extends Construccion {

    private static final Costo COSTO_CARRETERA = Costo.CARRETERA();


    public Carretera(Jugador dueño) {
        super(COSTO_CARRETERA, dueño);
    }

    @Override
    public void reclamarProduccion(TipoRecurso recurso) {
        // No hace nada.
    }

    @Override
    public int getPuntosDeVictoria() {
        return 0;
    }
}