package edu.fiuba.algo3.modelo.Carta;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Hexagono;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

import java.util.List;

public class CartaCaballero extends Carta {

    @Override
    public void jugar(Jugador jugador, Tablero tablero, Object... args) {
        Hexagono destino = (Hexagono) args[0];

        tablero.moverLadronA(destino);

        jugador.registrarCaballeroJugado();

        List<Jugador> victimas = destino.obtenerVictimas();
        if (!victimas.isEmpty()) {
            tablero.robarConLadronA(jugador, victimas.get(0));
        }
    }

    @Override
    public String toString() {
        return "Caballero";
    }
}
