package edu.fiuba.algo3.modelo.Construccion;

import java.util.Optional;

import edu.fiuba.algo3.modelo.Costo.Costo;
import edu.fiuba.algo3.modelo.Costo.ReglaCosto;
import edu.fiuba.algo3.modelo.Excepciones.NoSePuedeMejorarConstruccionError;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Jugador.Inventario;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Vertice;
import edu.fiuba.algo3.modelo.Tablero.Arista;

/**
 * Construccion
 */
public abstract class Construccion {
    protected Costo costo;
    protected Jugador propietario;
    protected Vertice verticeAsignado;
    protected Construible construible;
    protected ReglaCosto reglaCosto;

    public Construccion(Construible construible, ReglaCosto reglaCosto) {
        this.construible = construible;
        this.reglaCosto = reglaCosto;
    }

    public void pagarCon(Inventario inventario) {
        costo.aplicar(inventario);
    }

    public abstract String getNombre();

    public boolean esPropietarioElJugador(Jugador jugador) {
        return this.propietario == jugador;
    }

    public abstract int getPuntosDeVictoria();

    public void asignarVertice(Vertice vertice) {
        this.verticeAsignado = vertice;
    }

    public abstract void producirSegun(Recurso recurso);

    public boolean puedeMejorarse() {
        return false;
    }

    public Construccion mejorar() {
        throw new NoSePuedeMejorarConstruccionError();
    }

    /*
     * public boolean esAdyacenteA(Arista nueva) {
     * return false;
     * }
     */

    public void asignarJugador(Jugador jugador) {
        this.propietario = jugador;
    }

    public Optional<Jugador> propietario() {
        return Optional.of(propietario);
    }

    public Jugador getPropietario() {
        return propietario;
    }

    public void cobrarA(Jugador jugador) {
        jugador.descontarCon(costo);
    }

    public void construir(Object[] ubicaciones) {
        construible.construir(this, propietario, ubicaciones);
        reglaCosto.aplicarSobre(propietario, this);
    }
}
