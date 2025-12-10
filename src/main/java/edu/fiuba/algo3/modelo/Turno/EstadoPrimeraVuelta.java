package edu.fiuba.algo3.modelo.Turno;

import edu.fiuba.algo3.modelo.Carta.CartaDesarrollo;
import edu.fiuba.algo3.modelo.Construccion.Construccion;
import edu.fiuba.algo3.modelo.Construccion.Construible;
import edu.fiuba.algo3.modelo.Dado.AccionDado;
import edu.fiuba.algo3.modelo.Dado.AccionGenerarRecursos;
import edu.fiuba.algo3.modelo.Dado.AccionReducirRecursos;
import edu.fiuba.algo3.modelo.Dado.Dado;
import edu.fiuba.algo3.modelo.Comercio.Comercio;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Hexagono;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

/**
 * EstadoPrimeraVuelta
 */
public class EstadoPrimeraVuelta implements EstadoTurno {

    @Override
    public void tirarDado(int dado, Jugador jugador, Turno turno) {

    }

    @Override
    public void moverLadron(Turno turno, Tablero tablero, Hexagono destino) {

    }

    @Override
    public void robar(Turno turno, Tablero tablero, Jugador jugadorVictima, Jugador jugadorActivo) {

    }

    @Override
    public void construir(Turno turno, Jugador jugador, Construible construible, Construccion construccion,
            Object... ubicaciones) {

    }

    @Override
    public void comerciar(Turno turno, Jugador receptor, Comercio comercio) {

    }

    @Override
    public void jugarCarta(Turno turno, Jugador jugador, Tablero tablero, CartaDesarrollo cartaDesarrollo,
            Object... args) {

    }

    @Override
    public void pasarTurno(Turno turno, Jugador jugador, Juego juego) {

    }

}
