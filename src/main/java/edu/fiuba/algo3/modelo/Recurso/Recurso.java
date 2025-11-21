package edu.fiuba.algo3.modelo.Recurso;

public abstract class Recurso {
    public int cantidad;

    public Recurso() {
        this.cantidad = 0;
    }

    public abstract int obtenerRecurso();

}
