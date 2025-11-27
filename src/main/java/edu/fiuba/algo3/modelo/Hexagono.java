package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Recurso.Recurso;

public class Hexagono {
    private final Terreno terreno;
    private final int ficha;
    private boolean tieneLadron;

    public Hexagono(Terreno terreno, int ficha) {
        this.terreno = terreno;
        this.ficha = ficha;
        this.tieneLadron = false;
    }

    public void colocarLadron() {
        this.tieneLadron = true;
    }

    public void moverLadron() {
        this.tieneLadron = false;
    }

    public boolean tieneLadron() {
        return this.tieneLadron;
    }

    public boolean tieneFicha(int valorFicha) {
        return this.ficha == valorFicha;
    }

    public Recurso obtenerRecurso(int numeroDado) {

        if (this.tieneLadron) {
            return null;
        }

        if (this.tieneFicha(numeroDado)) {

            return terreno.devolverRecurso();
        }
        return null;
    }

    public Recurso obtenerRecursoBase() {
        return terreno.devolverRecurso();
    }
}
