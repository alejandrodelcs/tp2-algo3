package edu.fiuba.algo3.modelo.Costo;

import edu.fiuba.algo3.modelo.Construccion.Construccion;
import edu.fiuba.algo3.modelo.Jugador.Jugador;

public class ReglaCostoConstruccion implements ReglaCosto{
    @Override
    public void aplicarSobre(Jugador jugador, Construccion construccion) {
        construccion.cobrarA(jugador);
    }
}
