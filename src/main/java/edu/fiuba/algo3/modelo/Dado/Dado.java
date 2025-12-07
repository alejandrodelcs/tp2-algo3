package edu.fiuba.algo3.modelo.Dado;

import java.util.Random;

public class Dado {
    private final int dado;

    public Dado(int dado) {
        this.dado = dado;
    }

    public int tirarDado() {
        return new Random().nextInt(6) + 1;
    }

    public int lanzar() {
        return this.tirarDado() + this.tirarDado();
    }

}
