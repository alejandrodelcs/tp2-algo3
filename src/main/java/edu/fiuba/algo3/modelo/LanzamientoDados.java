package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Dado.Dado;

public class LanzamientoDados {
    private Dado dado1;
    private Dado dado2;

    public LanzamientoDados() {
        this.dado1 = new Dado();
        this.dado2 = new Dado();
    }

    // Constructor para testing
    public LanzamientoDados(Dado d1, Dado d2) {
        this.dado1 = d1;
        this.dado2 = d2;
    }

    public int lanzar() {
        return dado1.tirarDado() + dado2.tirarDado();
    }
}
