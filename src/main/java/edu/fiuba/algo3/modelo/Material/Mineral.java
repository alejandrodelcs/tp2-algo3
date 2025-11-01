package edu.fiuba.algo3.modelo.Material;

public class Mineral extends Material {

    @Override
    public int recibir() {
        this.cantidad++;
        return cantidad;
    }
}
