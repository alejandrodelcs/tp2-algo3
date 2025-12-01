package edu.fiuba.algo3.modelo.Construcciones;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.Costo.Costo;
import edu.fiuba.algo3.modelo.ElementosTablero.Arista;
import edu.fiuba.algo3.modelo.Jugador;
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

    protected Jugador propietario;
    protected Vertice verticeAsignado;

    public Construccion() {
        this.propietario = null;

    }

    public Construccion(Jugador propietario) {
        this.propietario = propietario;
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


    public boolean esAdyacenteA(Arista nueva) {
        return false;
    }

    public void agregarDuenio(List<Jugador> listaVictimas) {
        if (!listaVictimas.contains(this.propietario)) {
            listaVictimas.add(this.propietario);
        }
    }

    public void asignarJugador(Jugador jugador) {
        this.propietario = jugador;
    }
}
