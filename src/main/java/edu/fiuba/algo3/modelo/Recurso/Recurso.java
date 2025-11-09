package edu.fiuba.algo3.modelo.Recurso;

import edu.fiuba.algo3.modelo.Carta;

public abstract class Recurso extends Carta {
    protected int cantidad;

    public Recurso() {
        this.cantidad = 0;
    }

    public abstract int obtenerRecurso();


}
