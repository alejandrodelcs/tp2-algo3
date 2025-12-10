package edu.fiuba.algo3.modelo.Carta;

import edu.fiuba.algo3.modelo.Construccion.Carretera;
import edu.fiuba.algo3.modelo.Jugador.Jugador;

import edu.fiuba.algo3.modelo.Tablero.Arista;
import edu.fiuba.algo3.modelo.Tablero.Tablero;



public class CartaConstruccionCarreteras implements CartaDesarrollo {
    @Override
    public void jugar(Jugador jugador, Tablero tablero, Object... args) {
        Arista a1 = (Arista) args[0];
        Arista a2 = (Arista) args[1];

        Carretera c1 = new Carretera();
        c1.asignarJugador(jugador);
        tablero.construirCarreteraGratis(jugador, c1, a1);

        Carretera c2 = new Carretera();
        c2.asignarJugador(jugador);
        tablero.construirCarreteraGratis(jugador, c2, a2);

    }
}
