package edu.fiuba.algo3.modelo.Dado;


import edu.fiuba.algo3.modelo.Juego;

import java.util.Random;

public class Dado {
    private final int dado;
    private ReglaDado reglaDado;
    private Random random;

    public Dado(int dado) {
        this.dado = dado;
        this.random = new Random();
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
