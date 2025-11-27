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
        this.cartasRecurso = new ArrayList<>();
        this.construcciones = new ArrayList<>();
    }

    public int cantidadCartas() {
        int contador = 0;
        for (Recurso recurso : cartasRecurso) {
            contador = recurso.acumular(contador);

        }
        return contador;
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
        if (valorDado == 7 && cartasRecurso.size() >= 7) {
            return cartasRecurso.subList(0, Math.round((float) cartasRecurso.size() / 2));
        }

        return null;
    }

    public void cartas() {
        System.out.println(this.cartasRecurso);
    }
}
