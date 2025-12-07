package edu.fiuba.algo3.modelo.Carta;

import edu.fiuba.algo3.modelo.Jugador.Jugador;

public class CartaPuntoVictoria extends CartaDesarrollo {
    @Override
    protected void ejecutarEfecto(Jugador jugador) {
        jugador.sumarPuntoVictoria();
    }
}