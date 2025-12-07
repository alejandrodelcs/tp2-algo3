package edu.fiuba.algo3.modelo.Recurso;

public class Mineral extends Recurso {

    @Override
    public int obtenerRecurso() {
        return 1;
    }

    @Override
    public Recurso clonar() {
        return new Mineral();
    }

    @Override
    public String getImagen() {
        return "/images/mineral.png";

    }

}
