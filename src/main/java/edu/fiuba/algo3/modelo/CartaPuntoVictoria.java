package edu.fiuba.algo3.modelo;

public class CartaPuntoVictoria extends CartaDesarrollo {
    @Override
    protected void ejecutarEfecto(Jugador jugador) {
        jugador.sumarPuntoVictoria();
    }
}