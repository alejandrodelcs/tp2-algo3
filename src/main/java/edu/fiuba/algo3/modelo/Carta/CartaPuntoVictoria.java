package edu.fiuba.algo3.modelo.Carta;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

public class CartaPuntoVictoria extends Carta {
    @Override
    public void jugar(Jugador jugador, Tablero tablero, Object... args) {
        jugador.sumarPuntoVictoria();
    }

    @Override
    public boolean esDeUnSoloUso() {
        return false;
    }

    @Override
    public String toString() {
        return "Victoria";
    }
}
