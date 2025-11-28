package edu.fiuba.algo3.modelo.Recurso;

public abstract class Recurso {
    public int cantidad;

    public Recurso() {
        this.cantidad = 1;
    }

    public abstract String tipo();

    public abstract int obtenerRecurso();

    // Getter necesario para el assert
    public int getCantidad() {
        return this.cantidad;
    }

    public int acumular(int acumulador) {
        return acumulador + this.cantidad;
    }

    public abstract Recurso clonar();

    public void aumentar(int valor) {
        this.cantidad += valor;
    }

}
