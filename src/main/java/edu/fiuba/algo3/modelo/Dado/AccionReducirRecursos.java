package edu.fiuba.algo3.modelo.Dado;

import edu.fiuba.algo3.modelo.Jugador;

public class AccionReducirRecursos implements AccionDado{

    private final int dado;

    public AccionReducirRecursos(int dado){
        this.dado = dado ;
    }

    @Override
    public void aplicar(Jugador jugador) {
        jugador.descartarMitadSiCorresponde();
    }
}
