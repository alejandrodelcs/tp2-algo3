package edu.fiuba.algo3.modelo.Construcciones;

import edu.fiuba.algo3.modelo.Hexagono;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

/**
 * ConstruccionNula
 */
public class ConstruccionNula extends Construccion {
    public int getPuntosDeVictoria() {
        return 0;
    }

    @Override
    public boolean esNula() {
        return true;
    }

    @Override
    public Recurso generarSegunHexagono(Hexagono hexagono, int numDado) {
        return null;
    }

}
