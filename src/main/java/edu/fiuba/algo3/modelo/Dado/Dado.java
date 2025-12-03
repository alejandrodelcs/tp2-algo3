package edu.fiuba.algo3.modelo.Dado;

import java.util.Random;

public class Dado {
    private final int dado;

    public Dado(int dado) {
        this.dado = dado;
    }

    public Dado(){
        this.dado = tirarDado() + tirarDado();
    }



    public int tirarDado() {
        return new Random().nextInt(6) + 1;
    }

    public AccionDado lanzar() {
        return (dado == 7)
                ? new AccionReducirRecursos(dado)
                : new AccionGenerarRecursos(dado);
    }

}
