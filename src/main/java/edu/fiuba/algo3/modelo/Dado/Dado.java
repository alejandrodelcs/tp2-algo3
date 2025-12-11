package edu.fiuba.algo3.modelo.Dado;

import java.util.Random;

public class Dado {
    private final int dado;

    public Dado(int dado) {
        this.dado = dado;
    }

    public Dado() {
        this.dado = 0;
    }

    public int generar() {
        return new Random().nextInt(6) + 1;
    }

    public int lanzar() {
        return this.generar() + this.generar();
    }

}
