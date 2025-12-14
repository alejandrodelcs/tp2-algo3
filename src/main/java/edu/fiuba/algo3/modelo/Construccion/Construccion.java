package edu.fiuba.algo3.modelo.Construccion;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.fiuba.algo3.modelo.Costo.Costo;
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


    public void pagarCon(Inventario inventario) {
        costo.aplicar(inventario);
    }

    public abstract String getNombre();

    public boolean esPropietarioElJugador(Jugador jugador) {
        return this.propietario == jugador;
    }

    public abstract int getPuntosDeVictoria();


    public boolean esNula() {
        return false;
    }

    public void asignarVertice(Vertice vertice) {
        this.verticeAsignado = vertice;
    }


    public abstract ArrayList<Recurso> producirSegun(int dado);


    public boolean esAdyacenteA(Arista nueva) {
        return false;
    }

    public void agregarPropietario(List<Jugador> listaVictimas) {
        if (this.propietario != null){
            listaVictimas.add(this.propietario);
        }
    }

    public void asignarJugador(Jugador jugador) {
        this.propietario = jugador;
    }

    public Optional<Jugador> propietario(){
        return Optional.of(propietario);
    }

    public Jugador getPropietario() {
        return propietario;
    }


}
