package edu.fiuba.algo3.modelo.Carta;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Hexagono;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Turno.EstadoMoverLadron;
import edu.fiuba.algo3.modelo.Turno.Turno;

import java.util.List;

public class CartaCaballero extends Carta {

    @Override
    public void jugar(Jugador jugador, Tablero tablero, Object... args) {
        Juego juego = (Juego) args[0];
        Turno turno = juego.turnoActual();

        turno.cambiarEstado(new EstadoMoverLadron());
    }

    @Override
    public String toString() {
        return "Caballero";
    }
}
