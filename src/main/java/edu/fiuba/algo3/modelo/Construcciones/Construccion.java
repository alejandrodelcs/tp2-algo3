package edu.fiuba.algo3.modelo.Construcciones;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.Costo.Costo;
import edu.fiuba.algo3.modelo.Juego.Jugador;
import edu.fiuba.algo3.modelo.Inventario;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Vertice;
import edu.fiuba.algo3.modelo.Tablero.Hexagono;
import edu.fiuba.algo3.modelo.Tablero.Arista;

/**
 * Construccion
 */
public abstract class Construccion {
    protected Vertice vertice;
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

    public Jugador getPropietario() {
        return this.propietario;
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


    public boolean esAdyacenteA(Arista nueva) {
        return false;
    }

    public void agregarPropietario(List<Jugador> listaVictimas) {
        if (!listaVictimas.contains(this.propietario)) {
            listaVictimas.add(this.propietario);
        }
    }

    public void asignarJugador(Jugador jugador) {
        this.propietario = jugador;
    }







}
