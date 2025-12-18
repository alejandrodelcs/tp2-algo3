package edu.fiuba.algo3.modelo.Turno;

import edu.fiuba.algo3.modelo.Carta.CartaDesarrollo;
import edu.fiuba.algo3.modelo.Construccion.Carretera;
import edu.fiuba.algo3.modelo.Construccion.Construccion;
import edu.fiuba.algo3.modelo.Construccion.Poblado;
import edu.fiuba.algo3.modelo.Costo.ReglaCostoGratis;
import edu.fiuba.algo3.modelo.Comercio.Comercio;
import edu.fiuba.algo3.modelo.Dado.Dado;
import edu.fiuba.algo3.modelo.Excepciones.AccionNoPermitidaException;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Hexagono;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Vertice;

/**
 * EstadoSegundaVuelta
 */
public class EstadoSegundaVuelta implements EstadoTurno {

    private boolean usoPoblado = false;

    @Override
    public void tirarDado(Turno turno, Juego juego, Dado dado) {

        throw new AccionNoPermitidaException("");
    }

    @Override
    public void moverLadron(Turno turno, Tablero tablero, Hexagono destino) {

        throw new AccionNoPermitidaException("");
    }

    @Override
    public void robar(Turno turno, Tablero tablero, Jugador jugadorVictima, Jugador jugadorActivo) {

        throw new AccionNoPermitidaException("");
    }

    @Override
    public void construir(Turno turno, Jugador jugador, Construccion construccion,
            Object... ubicaciones) {

        if ((construccion instanceof Carretera)) {
            throw new AccionNoPermitidaException("");

        }
        if ((construccion instanceof Poblado) && !usoPoblado) {
            this.usoPoblado = true;

            construccion.cambiarReglaCosto(new ReglaCostoGratis());
            jugador.construir(construccion, ubicaciones);
            Vertice ubicacion = (Vertice) ubicaciones[0];
            jugador.entregaInicial(ubicacion);
        }

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
    public void pasarTurno(Turno turno, Jugador jugador, Juego juego) {
        juego.avanzarJugador(turno);

    }
}
