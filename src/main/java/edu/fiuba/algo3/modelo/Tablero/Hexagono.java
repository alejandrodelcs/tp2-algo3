package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Ladron.Ladron;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.ArrayList;
import java.util.List;

public class Hexagono {
    private final Recurso recurso;
    private int ficha;
    private Ladron ladron;
    private final List<Vertice> vertices;

    public Hexagono(Recurso recurso, int ficha) {
        this.recurso = recurso;
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

    public void intercambiarFicha(Hexagono destino) {
        int fichaTemporal = this.ficha;
        this.ficha = destino.ficha;
        destino.ficha = fichaTemporal;

    }

    public List<Jugador> obtenerVictimas() {
        List<Jugador> victimas = new ArrayList<>();

        for (Vertice v : this.vertices) {
            if (v.tieneConstruccion()) {
                v.jugadorPropietario().ifPresent(victimas::add);
            }
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

            return this.recurso;
        }
        return null;
    }

    public Recurso obtenerRecursoBase() {
        return this.recurso;
    }

    public List<Recurso> generarRecursos(int numeroDado, int cantidad) {
        List<Recurso> recursos = new ArrayList<>();

        if (!this.tieneLadron() || !this.coincideCon(numeroDado)) {
            return recursos;
        }

        for (int i = 0; i < cantidad; i++) {
            recursos.add(this.recurso);
        }

        return recursos;
    }

    public boolean tieneLadron() {
        return this.ladron == null;
    }

    public Recurso getTerreno() {
        return this.recurso;
    }

    public int getFicha() {
        return ficha;
    }

    // Para el visual
    public List<Vertice> getVertices() {
        return vertices;
    }
}
