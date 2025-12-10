package edu.fiuba.algo3.modelo.Carta;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

public class CartaMonopolio implements CartaDesarrollo{

    @Override
    public void jugar(Jugador jugador, Tablero tablero, Object... args) {
        Recurso tipo = (Recurso) args[0];
        Juego juego = (Juego) args[1];

        int total = juego.recolectarRecursosDeTodosMenos(jugador, tipo);
        jugador.agregarRecursos(tipo, total);
    }
}
