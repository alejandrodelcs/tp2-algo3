package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Ladron.Ladron;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.ArrayList;
import java.util.List;

public class Hexagono {
    private final Terreno terreno;
    private final int ficha;
    private Ladron ladron;
    private final List<Vertice> vertices;

    public Hexagono(Terreno terreno, int ficha) {
        this.terreno = terreno;
        this.ficha = ficha;
        this.vertices = new ArrayList<>();
    }

    public void colocarLadron(Ladron ladron) {
        this.ladron = ladron;
    }

    public void agregarVertice(Vertice vertice) {
        if (!vertices.contains(vertice)) {
            vertices.add(vertice);
        }
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

        if (this.ladron != null) {
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


    public List<Recurso> generarRecursos(int numeroDado, int cantidad) {
        List<Recurso> recursos = new ArrayList<>();

        if (!this.tieneLadron() || !this.coincideCon(numeroDado)) {
            return recursos;
        }

        for (int i = 0; i < cantidad; i++) {
            recursos.add(terreno.retornarRecurso());
        }

        return recursos;
    }


    public boolean tieneLadron(){
        return this.ladron == null;
    }


}
