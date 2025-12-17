package edu.fiuba.algo3.modelo.Carta;

import edu.fiuba.algo3.modelo.Construccion.Carretera;
import edu.fiuba.algo3.modelo.Costo.ReglaCostoGratis;
import edu.fiuba.algo3.modelo.Jugador.Jugador;

import edu.fiuba.algo3.modelo.Tablero.Arista;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

public class CartaConstruccionCarreteras extends Carta {
    @Override
    public void jugar(Jugador jugador, Tablero tablero, Object... args) {
        Arista a1 = (Arista) args[0];
        Arista a2 = (Arista) args[1];

        Carretera c1 = new Carretera(new ReglaCostoGratis());
        c1.asignarJugador(jugador);
        jugador.construir(c1, a1);

        Carretera c2 = new Carretera(new ReglaCostoGratis());
        c2.asignarJugador(jugador);
        jugador.construir(c2, a2);

    }

    @Override
    public String toString() {
        return "Carretera";
    }
}
