package edu.fiuba.algo3.modelo.Material;

public class Mineral extends Recurso {

    @Override
    public int obtenerRecurso() {
        this.cantidad++;
        return cantidad;
    }
}
