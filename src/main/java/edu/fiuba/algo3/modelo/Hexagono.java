package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.ElementosTablero.Vertice;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.ArrayList;
import java.util.List;

public class Hexagono {
    private final Terreno terreno;
    private final int ficha;
    private boolean tieneLadron;
    private List<Vertice> vertices = new ArrayList<>();

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

    public void conectarVertice(Vertice vertice) {
        this.vertices.add(vertice);
    }

    public List<Jugador> obtenerVictimas() {
        List<Jugador> victimas = new ArrayList<>();

        for (Vertice vertice : this.vertices) {
            vertice.agregarVictimaPotencial(victimas);
        }
        return victimas;
    }

    public boolean coincideCon(int valorFicha) {
        return this.ficha == valorFicha;
    }

    public Recurso obtenerRecurso(int numeroDado) {

        if (this.tieneLadron) {
            return null;
        }

        if (this.coincideCon(numeroDado)) {

            return terreno.retornarRecurso();
        }
        return null;
    }

    public Recurso obtenerRecursoBase() {
        return terreno.retornarRecurso();
    }


    public Recurso generarRecurso() {
        return terreno.retornarRecurso();
    }

    public boolean puedeGenerar() {
        return !tieneLadron;
    }
}
