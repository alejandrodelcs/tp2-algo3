package edu.fiuba.algo3.modelo;

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

    public int obtenerRecurso() {
        return terreno.obtenerRecurso();
    }
}
