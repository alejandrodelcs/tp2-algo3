package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

import edu.fiuba.algo3.modelo.Construcciones.*;
import edu.fiuba.algo3.modelo.ElementosTablero.*;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

/**
 * Jugador
 */
public class Jugador {

    private ArrayList<Recurso> cartasRecurso;
    private ArrayList<Construccion> construcciones;
    private String nombre;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.cartasRecurso = new ArrayList<>();
        this.construcciones = new ArrayList<>();
    }

    public int cantidadCartas() {
        return this.cartasRecurso.size();
    }

    public int cantidadConstrucciones() {
        return this.construcciones.size();
    }

    public void construir(Vertice vertice, Construccion construccion) {
        vertice.construir(construccion);
        this.construcciones.add(construccion);

    }

    public void generarSegunDado(int dado) {

        for (Construccion construccion : this.construcciones) {

            ArrayList<Recurso> recursos = construccion.generarSegunVertice(dado);
            this.cartasRecurso.addAll(recursos);

        }
    }
}
