package edu.fiuba.algo3.modelo.Dado;

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

    public AccionDado lanzarAccion() {
        int numero = tirarDado() + tirarDado();
        return (numero == 7)
                ? new AccionReducirRecursos()
                : new AccionGenerarRecursos(numero);
    }

}
