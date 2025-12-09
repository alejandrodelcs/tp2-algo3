package edu.fiuba.algo3.modelo.Jugador;

import java.util.*;

import edu.fiuba.algo3.modelo.Construccion.Construccion;
import edu.fiuba.algo3.modelo.Excepciones.NoHayRecursoDisponibleError;
import edu.fiuba.algo3.modelo.Excepciones.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

/**
 * Inventario
 */
public class Inventario {

    private List<Recurso> recursos;

    public Inventario(Recurso... recursos) {
        this.recursos = new ArrayList<>(Arrays.asList(recursos));
    }

    public Inventario() {
        this.recursos = new ArrayList<>();
    }

    public void agregar(Recurso recurso) {
        recursos.add(recurso);
    }

    public void agregar(List<Recurso> recursos) {
        this.recursos.addAll(recursos);
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
        for (Recurso r : recursos) {
            cant += r.acumular(total);
        }
        return cant;
    }

    public boolean excedeLimite() {
        return this.recursos.size() > 7;
    }

    public void consumir(Class<? extends Recurso> tipo) {
        Iterator<Recurso> it = recursos.iterator();

        while (it.hasNext()) {
            Recurso recurso = it.next();
            if (recurso.esDelMismoTipoQue(tipo)) {
                it.remove();
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

    public boolean posee(List<Recurso> costo) {
        List<Recurso> copiaInventario = new ArrayList<>(this.recursos);

        for (Recurso necesaria : costo) {
            if (!copiaInventario.remove(necesaria)) {
                return false;
            }
        }
        return true;
    }

    /*
     * Pre: -
     * Pro: retorna la mitad de una lista de recursos, el redondeo es para abajo.
     * e.g.: size = 8 , descarta 4 , mantiene 4, size=9, descarta 4, mantiene 5,
     * size=10, descarta 5, mantiene 5, size=11, descarte 5, mantiene 6.
     */
    public List<Recurso> descartarMitad() {
        int cantidadABorrar = recursos.size() / 2;
        List<Recurso> descartadas = new ArrayList<>();

        Collections.shuffle(recursos);

        for (int i = 0; i < cantidadABorrar; i++) {
            descartadas.add(recursos.remove(0));
        }

        return descartadas;
    }

    public void gastar(List<Recurso> costo) {
        if (!posee(costo)) {
            throw new RecursosInsuficientesException("No cubre el costo");
        }

        for (Recurso necesaria : costo) {
            this.recursos.remove(necesaria);
        }
    }

    public Recurso obtenerRecurso(int indice) {
        return this.recursos.get(indice);
    }

    public Recurso remover(Class<? extends Recurso> tipoRecurso) {

        for (Recurso recurso : recursos) {

            if (tipoRecurso.isInstance(recurso)) {
                recursos.remove(recurso);
                return recurso;
            }
        }
        return null;

    }

    public void descontarPara(Construccion construccion) {
        construccion.pagarCon(this);
    }

    public void agregarTodos(Iterable<Recurso> listaRecursos) {
        for (Recurso recurso : listaRecursos) {
            this.agregar(recurso);
        }
    }

    public void descartarMitadSiCorresponde() {
        if (excedeLimite())
            descartarMitad();
    }

    public boolean tiene(Class<? extends Recurso> tipo) {
        return recursos.stream().anyMatch(tipo::isInstance);
    }
}
