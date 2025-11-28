package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Random;

import edu.fiuba.algo3.modelo.Recurso.Recurso;

/**
 * Inventario
 */
public class Inventario {

    private Map<Class<? extends Recurso>, Integer> recursos = new HashMap<>();

    public void agregar(Recurso recurso) {
        Class<? extends Recurso> tipo = recurso.getClass();
        this.recursos.put(tipo, this.recursos.getOrDefault(tipo, 0) + recurso.getCantidad());
    }

    public void agregarTodos(Iterable<Recurso> listaRecursos) {
        for (Recurso recurso : listaRecursos) {
            this.agregar(recurso);
        }
    }

    public int cantidadDeTipo(Class<? extends Recurso> tipo) {
        return this.recursos.getOrDefault(tipo, 0);
    }

    public int total() {
        return recursos.values().stream().mapToInt(i -> i).sum();
    }

    public Recurso quitarUno(Class<? extends Recurso> tipo) {
        int cant = this.recursos.getOrDefault(tipo, 0);

        if (cant <= 0) {
            return null;
        }

        this.recursos.put(tipo, cant - 1);
        if (cant - 1 == 0) {
            this.recursos.remove(tipo);
        }

        try {
            return tipo.getDeclaredConstructor().newInstance();

        } catch (Exception e) {
            throw new RuntimeException("No se pudo instanciar el recurso");
        }
    }

    public Recurso robarUno() {
        if (this.estaVacio()) {
            return null;
        }
        java.util.List<Class<? extends Recurso>> tipos = new ArrayList<>(this.tiposDisponibles());

        int numRandom = new Random().nextInt(tipos.size());
        Class<? extends Recurso> tipoElegido = tipos.get(numRandom);

        return quitarUno(tipoElegido);
    }

    public boolean estaVacio() {
        return this.recursos.isEmpty();
    }

    public Set<Class<? extends Recurso>> tiposDisponibles() {
        return this.recursos.keySet();
    }

    public void reducirALaMitad() {
        int total = this.total();

        int cantidadConservar = total / 2;
        int aEliminar = total - cantidadConservar;

        for (int i = 0; i < aEliminar; i++) {
            this.robarUno();
        }
    }
}
