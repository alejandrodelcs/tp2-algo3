package edu.fiuba.algo3.modelo.Construccion;

import java.util.ArrayList;

import edu.fiuba.algo3.modelo.Costo.Costo;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.*;

/**
 * Poblado
 */
public class Poblado extends Construccion {


    public Poblado(){
        this.costo = new Costo(Madera.class, Ladrillo.class,Lana.class, Grano.class);
    }

    public int getPuntosDeVictoria() {
        return 1;
    }


    @Override
    public ArrayList<Recurso> producirSegun(int dado) {
        return this.verticeAsignado.generarRecurso(dado,1);
    }


}
