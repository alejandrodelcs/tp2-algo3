package edu.fiuba.algo3.modelo.Construccion;

import java.util.ArrayList;
import edu.fiuba.algo3.modelo.Costo.Costo;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Grano;
import edu.fiuba.algo3.modelo.Recurso.Mineral;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

public class Ciudad extends Construccion {

    public Ciudad() {
        this.costo = new Costo(Grano.class, Grano.class, Mineral.class, Mineral.class, Mineral.class);
    }

    @Override
    public int getPuntosDeVictoria() {
        return 2;
    }

    @Override
    public String getNombre(){
        return "ciudad";
    }

    @Override
    public ArrayList<Recurso> producirSegun(int dado) {

        return this.verticeAsignado.generarRecurso(dado,2);
    }




}
