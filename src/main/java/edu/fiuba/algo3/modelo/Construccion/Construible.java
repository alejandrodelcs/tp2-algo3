package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Jugador.Jugador;

public interface Construible {
    void construir(Construccion construccion, Jugador jugador, Object... ubicaciones);
}
