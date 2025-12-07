package edu.fiuba.algo3.modelo.Dado;

import edu.fiuba.algo3.modelo.Jugador.Jugador;

public class AccionReducirRecursos implements AccionDado{
    int dado;
    public AccionReducirRecursos(int dado) {
        this.dado = dado;
    }

    @Override
    public void aplicar(Jugador jugador) {
        jugador.descartarMitadSiCorresponde();
    }
}
