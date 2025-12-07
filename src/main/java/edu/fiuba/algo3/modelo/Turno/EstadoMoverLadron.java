package edu.fiuba.algo3.modelo.Turno;

import edu.fiuba.algo3.modelo.Carta.CartaDesarrollo;
import edu.fiuba.algo3.modelo.Construccion.Construccion;
import edu.fiuba.algo3.modelo.Construccion.Construible;
import edu.fiuba.algo3.modelo.Dado.Dado;
import edu.fiuba.algo3.modelo.Jugador.InteraccionJugador;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Ladron.AccionRobar;
import edu.fiuba.algo3.modelo.Tablero.Hexagono;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

import java.util.List;

public class EstadoMoverLadron implements EstadoTurno{


    @Override
    public void moverLadron(Turno turno, Tablero tablero, Hexagono destino){
        tablero.moverLadronA(destino);
        turno.cambiarEstado(new EstadoPuedeRobar());
    }

    @Override
    public void robar(Turno turno, Tablero tablero, Jugador jugador) {


    }

    @Override
    public void construir(Turno turno, Jugador jugador, Construible construible, Construccion construccion, Object... ubicaciones) {

    }

    @Override
    public void comerciar(Turno turno, Jugador receptor, InteraccionJugador interaccionJugador) {

    }

    @Override
    public void jugarCarta(Turno turno, Jugador jugador, CartaDesarrollo cartaDesarrollo) {

    }

    @Override
    public void pasarTurno(Turno turno, Jugador jugador) {

    }


    @Override
    public void tirarDado(Dado dado, Jugador jugador, Turno turno) {

    }


}
