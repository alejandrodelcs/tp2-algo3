package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

    public void robarA(Jugador victima) {
        if (victima != null && victima != this) {
            victima.entregarRecursoA(this);
        }
    }

    public void recibirRecurso(Recurso recurso) {
        if (recurso != null) {
            this.cartasRecurso.add(recurso);
        }
    }

    public void entregarRecursoA(Jugador ladron) {
        if (this.cartasRecurso.isEmpty()) {
            return;
        }

        Random random = new Random();
        int indiceAleatorio = random.nextInt(this.cartasRecurso.size());

        Recurso recursoRobado = this.cartasRecurso.remove(indiceAleatorio);

        ladron.recibirRecurso(recursoRobado);
    }

    public void generarSegunDado(int dado) {
        if (dado == 7) {
            this.reducirALaMitadLosRecurosos();
            return;
        }

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
        System.out.println("\ncartas recurso: " + this.cartasRecurso);
    }

    public void reducirALaMitadLosRecurosos() {
        if (this.cartasRecurso.size() > 7) {

            this.elegirCartaDescartadas();
        }
    }

    private void elegirCartaDescartadas() {

    }
}
