package edu.fiuba.algo3.modelo.Recurso;

public class Lana extends Recurso {

    public int obtenerRecurso() {
        return 1;
    }

    @Override
    public Recurso clonar() {
        return new Lana();
    }

    @Override
    public String getImagen() {
        return "/images/lana.png";

    }

}
