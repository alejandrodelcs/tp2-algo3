package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Juego.Jugador;

public class CartaCaballero extends CartaDesarrollo {
    @Override
    protected void ejecutarEfecto(Jugador jugador) {
        jugador.habilitarMovimientoLadron();
    }
}