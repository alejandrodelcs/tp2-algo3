package edu.fiuba.algo3.modelo.Carta;

import edu.fiuba.algo3.modelo.Costo.Costo;
import edu.fiuba.algo3.modelo.Excepciones.CartaNoDisponibleException;
import edu.fiuba.algo3.modelo.Jugador.Inventario;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Grano;
import edu.fiuba.algo3.modelo.Recurso.Lana;
import edu.fiuba.algo3.modelo.Recurso.Mineral;

public abstract class CartaDesarrollo {

    protected Costo costo;
    private boolean disponible;

    public CartaDesarrollo() {
        this.disponible = false;
        this.costo = new Costo(Lana.class, Grano.class, Mineral.class);
    }

    public void pasarTurno() {
        this.disponible = true;
    }

    public void activar(Jugador jugador) {
        if (!this.disponible) {
            throw new CartaNoDisponibleException("");
        }
        this.ejecutarEfecto(jugador);

    }

    public void pagarCon(Inventario inventario) {
        costo.aplicar(inventario);
    }

    public boolean esDeUnSoloUso() {
        return true;
    }

    protected abstract void ejecutarEfecto(Jugador jugador);
}
