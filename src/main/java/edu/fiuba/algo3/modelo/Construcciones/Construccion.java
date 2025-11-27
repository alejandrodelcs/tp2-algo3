package edu.fiuba.algo3.modelo.Construcciones;

import java.util.ArrayList;

import edu.fiuba.algo3.modelo.Hexagono;
import edu.fiuba.algo3.modelo.ElementosTablero.Vertice;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

/**
 * Construccion
 */
public abstract class Construccion {

    protected Vertice verticeAsignado;

    public abstract int getPuntosDeVictoria();

    public boolean esNula() {
        return false;
    }

    public void asignarVertice(Vertice vertice) {
        this.verticeAsignado = vertice;
    }

    public abstract Recurso generarSegunHexagono(Hexagono hexagono, int numDado);

    public abstract ArrayList<Recurso> generarSegunVertice(int dado);
}
