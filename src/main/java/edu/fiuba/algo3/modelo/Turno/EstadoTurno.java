package edu.fiuba.algo3.modelo.Turno;

import edu.fiuba.algo3.modelo.Carta.CartaDesarrollo;
import edu.fiuba.algo3.modelo.Construccion.Construccion;
import edu.fiuba.algo3.modelo.Comercio.Comercio;
import edu.fiuba.algo3.modelo.Dado.Dado;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Hexagono;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

public interface EstadoTurno {

    void tirarDado(Turno turno, Juego juego, Dado dado);

    void moverLadron(Turno turno, Tablero tablero, Hexagono destino);

    void robar(Turno turno, Tablero tablero, Jugador jugadorVictima, Jugador jugadorActivo);

    void construir(Turno turno, Jugador jugador, Construccion construccion,
                   Object... ubicaciones);

    void comerciar(Turno turno, Jugador receptor, Comercio comercio);

    void jugarCarta(Turno turno, Jugador jugador, Tablero tablero, CartaDesarrollo cartaDesarrollo, Object... args);

    void pasarTurno(Turno turno, Jugador jugador, Juego juego);
}
