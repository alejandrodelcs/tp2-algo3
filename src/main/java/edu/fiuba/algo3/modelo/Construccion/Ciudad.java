package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Juego.Jugador;
import edu.fiuba.algo3.modelo.Material.Costo;
import edu.fiuba.algo3.modelo.Material.TipoRecurso;

public class Ciudad extends Construccion {

    private static final Costo COSTO_CIUDAD = Costo.CIUDAD();


    public Ciudad(Jugador dueño) {
        super(COSTO_CIUDAD, dueño);
    }


    @Override
    public void reclamarProduccion(TipoRecurso recurso) {
        this.dueño.recibirRecurso(recurso, 2);
    }

    @Override
    public int getPuntosDeVictoria() {
        return 2;
    }
}

