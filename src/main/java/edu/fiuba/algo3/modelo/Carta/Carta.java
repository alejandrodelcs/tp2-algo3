package edu.fiuba.algo3.modelo.Carta;

public abstract class Carta implements CartaDesarrollo{
    protected boolean disponible = false;

    public boolean estaDisponible() {
        return disponible;
    }

    public void habilitar() {
        this.disponible = true;
    }

    public boolean esDeUnSoloUso() {
        return true;
    }
}
