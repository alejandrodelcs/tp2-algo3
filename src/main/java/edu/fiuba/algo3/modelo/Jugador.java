package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.fiuba.algo3.modelo.Construcciones.*;
import edu.fiuba.algo3.modelo.ElementosTablero.*;
import edu.fiuba.algo3.modelo.Recurso.*;

/**
 * Jugador
 */
public class Jugador {

    private List<Recurso> cartasRecurso;
    private ArrayList<Construccion> construcciones;
    private String nombre;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.cartasRecurso = Arrays.asList(new Madera(), new Ladrillo(),
                new Lana(), new Grano(), new Lana(),
                new Ladrillo(), new Grano());
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

    public List<Recurso> validarCartas(int valorDado) {
        if (valorDado == 7 && cartasRecurso.size() >= 7){
            return cartasRecurso.subList(0,Math.round((float) cartasRecurso.size() /2));
        }

        return null;
    }
}
