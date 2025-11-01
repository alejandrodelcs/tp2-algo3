package edu.fiuba.algo3.modelo.Material;

public class Grano extends Material{

    public int recibir() {
        this.cantidad++;
        return cantidad;
    }
}
