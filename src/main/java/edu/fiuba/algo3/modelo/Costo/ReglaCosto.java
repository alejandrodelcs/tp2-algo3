package edu.fiuba.algo3.modelo.Costo;

import edu.fiuba.algo3.modelo.Construccion.Construccion;
import edu.fiuba.algo3.modelo.Jugador.Jugador;

public interface ReglaCosto {
    void aplicarSobre(Jugador jugador, Construccion construccion);
}
