package edu.fiuba.algo3.modelo.Material;

public class Madera extends  Material{

    public int recibir() {
        this.cantidad++;
        return cantidad;
    }
}
