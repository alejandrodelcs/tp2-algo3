package edu.fiuba.algo3.modelo.Turno;

import edu.fiuba.algo3.modelo.Carta.CartaDesarrollo;
import edu.fiuba.algo3.modelo.Construccion.Construccion;
import edu.fiuba.algo3.modelo.Construccion.Construible;
import edu.fiuba.algo3.modelo.Dado.Dado;
import edu.fiuba.algo3.modelo.Jugador.InteraccionJugador;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Hexagono;
import edu.fiuba.algo3.modelo.Tablero.Tablero;


public interface  EstadoTurno {

    void tirarDado(Dado dado, Jugador jugador, Turno turno);


    void moverLadron(Turno turno, Tablero tablero, Hexagono destino);


    void robar(Turno turno, Tablero tablero, Jugador jugador);


    void construir(Turno turno, Jugador jugador, Construible construible, Construccion construccion, Object... ubicaciones);

    void comerciar(Turno turno, Jugador receptor, InteraccionJugador interaccionJugador);

    void jugarCarta(Turno turno, Jugador jugador, CartaDesarrollo cartaDesarrollo);

    void pasarTurno(Turno turno, Jugador jugador);
}
