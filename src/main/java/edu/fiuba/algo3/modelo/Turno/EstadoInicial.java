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

public class EstadoInicial implements EstadoTurno {

    @Override
    public void tirarDado(Turno turno, Juego juego, Dado dado) {
        int resultado = dado.lanzar();
        if (resultado == 7) {
            turno.cambiarEstado(new EstadoMoverLadron());
            juego.numeroDado(resultado);
        } else {
            turno.cambiarEstado(new EstadoAcciones());
        }

        juego.resolverTirada(resultado);
    }

    @Override
    public void moverLadron(Turno turno, Tablero tablero, Hexagono destino) {
        throw new AccionNoPermitidaException("Hay que tirar el dado primero");

    }

    @Override
    public void robar(Turno turno, Tablero tablero, Jugador jugadorVictima, Jugador jugadorActivo) {
        throw new AccionNoPermitidaException("Hay que tirar el dado primero");
    }

    @Override
    public void construir(Turno turno, Jugador jugador, Construccion construccion,
            Object... ubicaciones) {
        throw new AccionNoPermitidaException("Hay que tirar el dado primero");
    }

    @Override
    public void comerciar(Turno turno, Jugador receptor, Comercio comercio) {
        throw new AccionNoPermitidaException("Hay que tirar el dado primero");
    }

    @Override
    public void jugarCarta(Turno turno, Jugador jugador, Tablero tablero, CartaDesarrollo cartaDesarrollo,
            Object... args) {
        throw new AccionNoPermitidaException("Hay que tirar el dado primero");
    }

    @Override
    public void pasarTurno(Turno turno, Jugador jugador, Juego juego) {
        throw new AccionNoPermitidaException("Hay que tirar el dado primero");
    }

    public void avanzarJugador(Juego juego) {

        throw new AccionNoPermitidaException("no se puede avanzar");
    }
}
