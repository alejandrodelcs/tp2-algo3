package edu.fiuba.algo3.modelo.Material;

public class Lana extends Recurso {


    public int obtenerRecurso() {
        this.cantidad++;
        return cantidad;
    }
}
