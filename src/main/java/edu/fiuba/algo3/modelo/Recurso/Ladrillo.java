package edu.fiuba.algo3.modelo.Recurso;

public class Ladrillo extends Recurso {

    public int obtenerRecurso() {
        return 1;
    }

    @Override
    public Recurso clonar() {
        return new Ladrillo();
    }

}
