package edu.fiuba.algo3.modelo.Recurso;

public class Lana extends Recurso {


    public int obtenerRecurso() {
        this.cantidad++;
        return cantidad;
    }
}
