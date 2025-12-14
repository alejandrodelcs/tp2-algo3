package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Jugador.Jugador;

public interface ReglaConstruccion {
    void validar(Jugador jugador, Object...ubicaciones);
}
