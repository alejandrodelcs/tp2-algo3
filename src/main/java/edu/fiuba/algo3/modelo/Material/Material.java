package edu.fiuba.algo3.modelo.Material;

public abstract class Material {
    protected int cantidad;

    public Material() {
        this.cantidad = 0;
    }

    public abstract int recibir();
}
