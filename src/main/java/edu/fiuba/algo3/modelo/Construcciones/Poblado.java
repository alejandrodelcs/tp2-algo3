package edu.fiuba.algo3.modelo.Construcciones;

import java.util.ArrayList;

import edu.fiuba.algo3.modelo.Costo.Costo;
import edu.fiuba.algo3.modelo.Tablero.Hexagono;
import edu.fiuba.algo3.modelo.Juego.Jugador;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Vertice;

/**
 * Poblado
 */
public class Poblado extends Construccion {


    public Poblado(){
        this.costo = new Costo(Madera.class, Ladrillo.class,Lana.class, Grano.class);
    }

    public Poblado(Jugador propietario) {
        super(propietario);
    }

    public int getPuntosDeVictoria() {
        return 1;
    }

    @Override
    public Recurso generarSegunHexagono(Hexagono hexagono, int numDado) {
        return hexagono.obtenerRecurso(numDado);
    }


    @Override
    public boolean puedeSerMejorada() {
        return true;
    }


    @Override
    public ArrayList<Recurso> generarSegunVertice(int dado) {
        return this.verticeAsignado.generarRecurso(dado,1);
    }


}
