package edu.fiuba.algo3.modelo.Recurso;

public class Grano extends Recurso {

    public int obtenerRecurso() {
        this.cantidad++;
        return cantidad;
    }
}
