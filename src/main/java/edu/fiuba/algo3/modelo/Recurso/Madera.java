package edu.fiuba.algo3.modelo.Recurso;

public class Madera extends Recurso {

    public int obtenerRecurso() {
        return 1;
    }

    @Override
    public Recurso clonar() {
        return new Madera();
    }

    @Override
    public String getImagen() {
        return "/images/madera.png";

    }

}
