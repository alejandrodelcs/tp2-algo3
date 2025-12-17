package edu.fiuba.algo3.modelo.Carta;

public abstract class Carta implements CartaDesarrollo {
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

    public boolean esDelMismoTipoQue(Carta tipo) {
        return this.getClass().equals(tipo.getClass());
    }

    @Override
    public abstract String toString();
}
