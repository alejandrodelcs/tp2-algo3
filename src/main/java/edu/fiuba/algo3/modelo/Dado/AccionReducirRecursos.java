package edu.fiuba.algo3.modelo.Dado;

import edu.fiuba.algo3.modelo.Jugador;

public class AccionReducirRecursos implements AccionDado{

    @Override
    public void aplicar(Jugador jugador) {
        jugador.descartarMitadSiCorresponde();
    }
}
