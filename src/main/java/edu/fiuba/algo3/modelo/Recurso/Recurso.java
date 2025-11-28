package edu.fiuba.algo3.modelo.Recurso;

public abstract class Recurso {
    public int cantidad;

    public Recurso() {
        this.cantidad = 1;
    }

    public abstract int obtenerRecurso();

    // Getter necesario para el assert
    public int getCantidad() {
        return this.cantidad;
    }

    public int acumular(int cantidad) {
        return cantidad + 1;
    }

}
