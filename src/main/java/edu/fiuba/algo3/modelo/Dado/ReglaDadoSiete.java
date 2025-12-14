package edu.fiuba.algo3.modelo.Dado;

import edu.fiuba.algo3.modelo.Juego;

public class ReglaDadoSiete implements ReglaDado {
    int dado;
    public ReglaDadoSiete(int dado) {
        this.dado = dado;
    }

    @Override
    public void aplicar(int numDado, Juego juego) {
        juego.aplicarPenalidadPorSiete();
        juego.activarLadron();
    }
}
