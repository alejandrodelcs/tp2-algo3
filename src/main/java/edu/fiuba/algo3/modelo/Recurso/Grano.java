package edu.fiuba.algo3.modelo.Recurso;

public class Grano extends Recurso {

    public int obtenerRecurso() {
        return 1;
    }

    @Override
    public Recurso clonar() {
        return new Grano();
    }

    @Override
    public String toString() {
        return "GRANO";
    }
}
