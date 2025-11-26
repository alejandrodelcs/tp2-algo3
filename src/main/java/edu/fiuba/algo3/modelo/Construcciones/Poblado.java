package edu.fiuba.algo3.modelo.Construcciones;

import edu.fiuba.algo3.modelo.Hexagono;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

/**
 * Poblado
 */
public class Poblado extends Construccion {
    public int getPuntosDeVictoria() {
        return 1;
    }

    @Override
    public Recurso generarSegunHexagono(Hexagono hexagono, int numDado) {
        Recurso recurso = hexagono.obtenerRecurso(numDado);
        return recurso;
    }
}
