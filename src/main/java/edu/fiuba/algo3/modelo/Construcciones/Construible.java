package edu.fiuba.algo3.modelo.Construcciones;

import edu.fiuba.algo3.modelo.Jugador;

public interface Construible {
    void construir(Jugador jugador, Construccion construccion, Object... ubicaciones);
}
