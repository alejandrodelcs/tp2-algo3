package edu.fiuba.algo3.modelo.Turno;

import edu.fiuba.algo3.modelo.Carta.CartaDesarrollo;
import edu.fiuba.algo3.modelo.Construccion.Construccion;
import edu.fiuba.algo3.modelo.Construccion.Construible;
import edu.fiuba.algo3.modelo.Dado.AccionDado;
import edu.fiuba.algo3.modelo.Dado.AccionGenerarRecursos;
import edu.fiuba.algo3.modelo.Dado.AccionReducirRecursos;
import edu.fiuba.algo3.modelo.Dado.Dado;
import edu.fiuba.algo3.modelo.Jugador.InteraccionJugador;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Hexagono;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

public class EstadoInicial implements EstadoTurno{

    @Override
    public void tirarDado(Dado dado, Jugador jugador, Turno turno) {
        int numero = dado.lanzar();
        AccionDado accion = (numero == 7)
                        ? new AccionReducirRecursos(numero)
                        : new AccionGenerarRecursos(numero);
        accion.aplicar(jugador);

        if (numero == 7) {
            turno.cambiarEstado(new EstadoMoverLadron());
        }
    }

    @Override
    public void moverLadron(Turno turno, Tablero tablero, Hexagono destino) {

    }


    @Override
    public void robar(Jugador jugador, Tablero tablero) {

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


}
