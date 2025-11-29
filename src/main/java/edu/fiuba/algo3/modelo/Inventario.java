package edu.fiuba.algo3.modelo;

import java.util.*;

import edu.fiuba.algo3.modelo.Errores.NoHayRecursoDisponibleError;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

/**
 * Inventario
 */
public class Inventario {

    private final ArrayList<Recurso> recursos;

    public Inventario(Recurso...recursos) {
        this.recursos = new ArrayList<>(Arrays.asList(recursos));
    }

    public Inventario(){
        this.recursos = new ArrayList<>();
    }


    public void agregar(Recurso recurso) {
        recursos.add(recurso);
    }

    public void agregarTodos(Iterable<Recurso> listaRecursos) {
        for (Recurso recurso : listaRecursos) {
            this.agregar(recurso);
        }
    }

    public int cantidadDeTipo(Class<? extends Recurso> tipo) {
        int cantidad = 0;
        for (Recurso recurso : recursos) {
            if (recurso.getClass().equals(tipo)) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public int total() {
        int total = 0;
        int cant = 0;
        for(Recurso r : recursos){
            cant += r.acumular(total);
        }
        return cant;
    }

    public void consumir(Class<? extends Recurso> tipo) {
        for (int i = 0; i < recursos.size(); i++) {
            Recurso recurso = recursos.get(i);

            if (recurso != null && recurso.getClass().equals(tipo)) {
                recursos.remove(i);
                return;
            }
        }
        throw new NoHayRecursoDisponibleError();
    }

    public Recurso robarUno() {
        if (estaVacio()) {
            return null;
        }
        Random random = new Random();
        int index = random.nextInt(recursos.size());
        return recursos.remove(index);
    }

    public boolean estaVacio() {
        return this.recursos.isEmpty();
    }

    public Set<Class<? extends Recurso>> tiposDisponibles() {
        Set<Class<? extends Recurso>> tipos = new HashSet<>();
        for (Recurso recurso : recursos) {
            tipos.add(recurso.getClass());
        }
        return tipos;
    }

    public void reducirALaMitad() {
        int total = recursos.size();
        int cantidadConservar = total / 2;
        int aEliminar = total - cantidadConservar;

        for (int i = 0; i < aEliminar; i++) {
            robarUno();
        }
    }
}
