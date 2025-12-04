package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Excepciones.CartaNoDisponibleException;
import edu.fiuba.algo3.modelo.Juego.Jugador;

public abstract class CartaDesarrollo {

    private boolean disponible;

    public CartaDesarrollo() {
        this.disponible = false;
    }

    public void pasarTurno() {
        this.disponible = true;
    }

    public void activar(Jugador jugador) {
        if (!this.disponible) {
            throw new CartaNoDisponibleException("");
        }

        this.ejecutarEfecto(jugador);

        // this.disponible = false;
    }

    public boolean esDeUnSoloUso() {
        return true;
    }

    protected abstract void ejecutarEfecto(Jugador jugador);
}
