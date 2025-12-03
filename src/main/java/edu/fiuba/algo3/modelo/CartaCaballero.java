package edu.fiuba.algo3.modelo;

public class CartaCaballero extends CartaDesarrollo {
    @Override
    protected void ejecutarEfecto(Jugador jugador) {
        jugador.habilitarMovimientoLadron();
    }
}