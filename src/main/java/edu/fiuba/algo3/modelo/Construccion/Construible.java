package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Juego.Jugador;

public interface Construible {
    void construir(Jugador jugador, Construccion construccion, Object... ubicaciones);
}
