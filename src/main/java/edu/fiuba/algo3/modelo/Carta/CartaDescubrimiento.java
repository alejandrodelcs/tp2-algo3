package edu.fiuba.algo3.modelo.Carta;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

public class CartaDescubrimiento extends Carta{
    @Override
    public void jugar(Jugador jugador, Tablero tablero, Object... args) {
        Recurso r1 = (Recurso) args[0];
        Recurso r2 = (Recurso) args[1];

        jugador.recibirRecurso(r1);
        jugador.recibirRecurso(r2);
    }
}
