package edu.fiuba.algo3.modelo.Turno;

import edu.fiuba.algo3.modelo.Carta.MazoDesarrollo;
import edu.fiuba.algo3.modelo.Construccion.Construccion;
import edu.fiuba.algo3.modelo.Construccion.Construible;
import edu.fiuba.algo3.modelo.Dado.Dado;
import edu.fiuba.algo3.modelo.Comercio.Comercio;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Hexagono;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

/*
    Puede robar una carta aun jugador con construcciones adyacentes sino excepction
 */


public class EstadoPuedeRobar implements EstadoTurno{
    @Override
    public void tirarDado(Dado dado, Jugador jugador, Turno turno) {

    }

    @Override
    public void moverLadron(Turno turno, Tablero tablero, Hexagono destino) {

    }


    @Override
    public void robar(Turno turno, Tablero tablero, Jugador victima) {
        tablero.robarConLadronA(victima);
        turno.cambiarEstado(new EstadoAcciones());
    }

    @Override
    public void construir(Turno turno, Jugador jugador, Construible construible, Construccion construccion, Object... ubicaciones) {

    }

    @Override
    public void comerciar(Turno turno, Jugador receptor, Comercio comercio) {

    }

    @Override
    public void jugarCarta(Turno turno, Jugador jugador, MazoDesarrollo mazoDesarrollo) {

    }

    @Override
    public void pasarTurno(Turno turno, Jugador jugador) {

    }


}
