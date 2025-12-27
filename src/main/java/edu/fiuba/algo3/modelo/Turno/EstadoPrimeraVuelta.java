package edu.fiuba.algo3.modelo.Turno;

import edu.fiuba.algo3.modelo.Carta.CartaDesarrollo;
import edu.fiuba.algo3.modelo.Construccion.Carretera;
import edu.fiuba.algo3.modelo.Construccion.Construccion;
import edu.fiuba.algo3.modelo.Construccion.Poblado;
import edu.fiuba.algo3.modelo.Construccion.ReglaAdyacencia;
import edu.fiuba.algo3.modelo.Construccion.ReglaDistancia;
import edu.fiuba.algo3.modelo.Construccion.ReglaDistanciaYAdyacencia;
import edu.fiuba.algo3.modelo.Costo.ReglaCostoGratis;
import edu.fiuba.algo3.modelo.Comercio.Comercio;
import edu.fiuba.algo3.modelo.Dado.Dado;
import edu.fiuba.algo3.modelo.Excepciones.AccionNoPermitidaException;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Hexagono;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

/**
 * EstadoPrimeraVuelta
 */
public class EstadoPrimeraVuelta implements EstadoTurno {
    private boolean usoCarretera = false;
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

        if ((construccion instanceof Carretera) && !usoCarretera) {

            construccion.cambiarReglaCosto(new ReglaCostoGratis());
            System.out.println(construccion.toString());
            jugador.construir(construccion, ubicaciones);

            this.usoCarretera = true;
        }
        if ((construccion instanceof Poblado) && !usoPoblado) {

            construccion.cambiarReglaCosto(new ReglaCostoGratis());
            construccion.cambiarReglaConstruccion(new ReglaDistancia());

            jugador.construir(construccion, ubicaciones);
            this.usoPoblado = true;
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
