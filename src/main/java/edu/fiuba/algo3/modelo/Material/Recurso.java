package edu.fiuba.algo3.modelo.Material;

public abstract class Recurso {
    protected int cantidad;

    public Recurso() {
        this.cantidad = 0;
    }

    public abstract int obtenerRecurso();
}
