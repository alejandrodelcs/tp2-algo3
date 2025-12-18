package edu.fiuba.algo3.modelo.Turno;

import edu.fiuba.algo3.modelo.Carta.CartaDesarrollo;
import edu.fiuba.algo3.modelo.Construccion.Construccion;
import edu.fiuba.algo3.modelo.Comercio.Comercio;
import edu.fiuba.algo3.modelo.Dado.Dado;
import edu.fiuba.algo3.modelo.Excepciones.AccionNoPermitidaException;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Hexagono;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

public class EstadoMoverLadron implements EstadoTurno {

    @Override
    public void tirarDado(Turno turno, Juego juego, Dado dado) {
        throw new AccionNoPermitidaException("");
    }

    @Override
    public void moverLadron(Turno turno, Tablero tablero, Hexagono destino) {
        tablero.moverLadronA(destino);
        turno.cambiarEstado(new EstadoPuedeRobar());
    }

    @Override
    public void robar(Turno turno, Tablero tablero, Jugador jugadorVictima, Jugador jugadorActivo) {
        throw new AccionNoPermitidaException("");
    }

    @Override
    public void construir(Turno turno, Jugador jugador, Construccion construccion,
            Object... ubicaciones) {
        throw new AccionNoPermitidaException("");
    }

    @Override
    public void comerciar(Turno turno, Jugador receptor, Comercio comercio) {
        throw new AccionNoPermitidaException("");
    }

    @Override
    public void jugarCarta(Turno turno, Jugador jugador, Tablero tablero, CartaDesarrollo cartaDesarrollo,
            Object... args) {
        throw new AccionNoPermitidaException("");
    }

    @Override
    public void pasarTurno(Turno turno, Jugador jugadorActivo, Juego juego) {
        System.out.println(jugadorActivo.getNombre());
        jugadorActivo.habilitarCartasDesarrollo();

        juego.finalizarTurnoActual();
        // turno.cambiarEstado(new EstadoInicial());
    }

}
