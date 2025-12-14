package edu.fiuba.algo3.modelo.Construccion;

import java.util.ArrayList;
import edu.fiuba.algo3.modelo.Costo.Costo;
import edu.fiuba.algo3.modelo.Costo.ReglaCostoConstruccion;
import edu.fiuba.algo3.modelo.Recurso.Grano;
import edu.fiuba.algo3.modelo.Recurso.Mineral;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

public class Ciudad extends Construccion {

    public Ciudad() {
        super(new ConstruirAsentamiento(new ReglaDistancia()), new ReglaCostoConstruccion());
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
    public void producirSegun(Recurso recurso) {
        for (int i = 0 ; i<2; i++){
            propietario.recibirRecurso(recurso);
        }
    }




}
