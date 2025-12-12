package edu.fiuba.algo3.modelo.Recurso;

/**
 * Desierto
 */
public class Desierto extends Recurso {

    public int obtenerRecurso() {
        return 0;
    }

    @Override
    public Recurso clonar() {
        return null;
    }

    @Override
    public String toString() {
        return "DESIERTO";
    }
}
