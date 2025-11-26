package edu.fiuba.algo3.modelo;

import java.util.Random;

public class Dado {
    private Random generador;

    public Dado(Random generador) {
        this.generador = generador;
    }

    public Dado(){
        this(new Random());
    }

    public int tirarDado() {
        return generador.nextInt(6) + 1;
    }
}
