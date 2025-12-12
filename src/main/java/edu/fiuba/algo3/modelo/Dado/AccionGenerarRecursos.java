package edu.fiuba.algo3.modelo.Dado;

import edu.fiuba.algo3.modelo.Jugador.Jugador;

public class AccionGenerarRecursos implements AccionDado{
    private final int dado;

    public AccionGenerarRecursos(int dado) {
        this.dado = dado;
    }

   @Override
    public void aplicar(Jugador jugador) {
        jugador.generarRecursosPorConstrucciones(this.dado);
    }
}
