package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Juego.*;
import edu.fiuba.algo3.modelo.Recurso.Costo;
import edu.fiuba.algo3.modelo.Recurso.TipoRecurso;

public class Poblado extends Construccion {


    private static final Costo COSTO_POBLADO = Costo.POBLADO();

    public Poblado(Jugador dueño) {
        super(COSTO_POBLADO, dueño);
    }


    @Override
    public void reclamarProduccion(TipoRecurso recurso) {
        this.dueño.recibirRecurso(recurso, 1);
    }

    @Override
    public int getPuntosDeVictoria() {
        return 1;
    }
}
