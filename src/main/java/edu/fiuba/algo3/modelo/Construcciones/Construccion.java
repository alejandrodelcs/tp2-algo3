package edu.fiuba.algo3.modelo.Construcciones;

import edu.fiuba.algo3.modelo.Hexagono;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

/**
 * Construccion
 */
public abstract class Construccion {

    public abstract int getPuntosDeVictoria();

    public boolean esNula() {
        return false;
    }

    public abstract Recurso generarSegunHexagono(Hexagono hexagono, int numDado);
}
