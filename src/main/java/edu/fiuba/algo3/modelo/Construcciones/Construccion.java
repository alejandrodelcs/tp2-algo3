package edu.fiuba.algo3.modelo.Construcciones;

import java.util.ArrayList;

import edu.fiuba.algo3.modelo.Costo.Costo;
import edu.fiuba.algo3.modelo.Hexagono;
import edu.fiuba.algo3.modelo.ElementosTablero.Vertice;
import edu.fiuba.algo3.modelo.Inventario;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

/**
 * Construccion
 */
public abstract class Construccion {

    protected Costo costo;

    public void pagarCon(Inventario inventario) {
        costo.aplicar(inventario);
    }

    protected Jugador dueño;
    protected Vertice verticeAsignado;

    public Construccion() {
        this.dueño = null;

    }

    public abstract List<Recurso> getCosto();

    public Construccion(Jugador dueño) {
        this.dueño = dueño;
    }

    public boolean estaEn(Vertice v) {
        return this.verticeAsignado == v;
    }

    public boolean puedeSerMejorada() {
        return false;
    }

    public Jugador getDuenio() {
        return this.dueño;
    }

    public abstract int getPuntosDeVictoria();

    public boolean esNula() {
        return false;
    }

    public void asignarVertice(Vertice vertice) {
        this.verticeAsignado = vertice;
    }

    public abstract Recurso generarSegunHexagono(Hexagono hexagono, int numDado);

    public abstract ArrayList<Recurso> generarSegunVertice(int dado);

    public abstract void agregarDuenio(List<Jugador> listaVictimas);

}
