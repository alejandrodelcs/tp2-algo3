package edu.fiuba.algo3.modelo.Dado;

import edu.fiuba.algo3.modelo.Juego;

public class ReglaDadoNormal implements ReglaDado {
    private final int dado;

    public ReglaDadoNormal(int dado) {
        this.dado = dado;
    }

   @Override
    public void aplicar(int numDado, Juego juego) {
       juego.repartirRecursosPorDado(numDado);
    }
}
