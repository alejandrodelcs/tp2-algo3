package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.Construcciones.*;
import edu.fiuba.algo3.modelo.ElementosTablero.*;
import edu.fiuba.algo3.modelo.Recurso.*;

/**
 * Jugador
 */
public class Jugador {

    private ArrayList<Construccion> construcciones;
    private String nombre;
    private Inventario inventario;

    public Jugador(String nombre, Inventario inventario) {
        this.nombre = nombre;
        this.construcciones = new ArrayList<>();
        this.inventario = inventario;
    }

    public int cantidadCartas() {
        return this.inventario.total();
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
            this.inventario.agregar(recurso);
        }
    }

    public void entregarRecursoA(Jugador ladron) {
        Recurso recurso = this.inventario.robarUno();

        if (recurso != null) {
            ladron.recibirRecurso(recurso);
        }
    }

    public void generarSegunDado(int dado) {
        if (dado == 7) {
            this.reducirALaMitadLosRecurosos();
            return;
        }

        for (Construccion construccion : this.construcciones) {

            ArrayList<Recurso> recursos = construccion.generarSegunVertice(dado);
            this.inventario.agregarTodos(recursos);

        }
    }

    public void reducirALaMitadLosRecurosos() {
        if (this.inventario.total() > 7) {

            this.inventario.reducirALaMitad();
        }
    }

    public void contruirCarretera(Vertice inicio, Vertice fin, Carretera carretera) {
        Arista a = new Arista(inicio, fin);
        this.construcciones.add(carretera);
        inventario.consumir(Madera.class);
        inventario.consumir(Ladrillo.class);
    }

    public int consultarRecursos() {
        return this.inventario.total();
    }

    public boolean tieneEnInventario(List<Class<? extends Recurso>> solicitud) {

        for (Class<? extends Recurso> recurso : solicitud) {
            if (this.inventario.cantidadDeTipo(recurso) == 0) {
                return false;
            }

        }
        return true;
    }

    public void entregarTipos(Jugador otroJugador, List<Class<? extends Recurso>> solicitud) {

        for (Class<? extends Recurso> tipo : solicitud) {

            Recurso recurso = inventario.remover(tipo);
            otroJugador.recibirRecurso(recurso);
        }

    }

    public int cantidadDeRecursoTipo(Class<? extends Recurso> tipo) {
        return this.inventario.cantidadDeTipo(tipo);

    }

    public Jugador seleccionarVictima(List<Jugador> candidatas) {
        return candidatas.get(0);// ver como fx selecciona a la victima
    }

}
