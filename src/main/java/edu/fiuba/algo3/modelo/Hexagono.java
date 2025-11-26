package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Recurso.Recurso;

public class Hexagono {
    private final Terreno terreno;
    private final int ficha;

    public Hexagono(Terreno terreno, int ficha) {
        this.terreno = terreno;
        this.ficha = ficha;
    }

    public boolean tieneFicha(int valorFicha) {
        return this.ficha == valorFicha;
    }

    public Recurso obtenerRecurso(int numeroDado) {
        if (this.tieneFicha(numeroDado)) {

            return terreno.devolverRecurso();
        }
        return null;
    }

}
