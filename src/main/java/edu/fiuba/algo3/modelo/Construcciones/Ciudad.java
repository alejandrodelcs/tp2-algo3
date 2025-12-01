package edu.fiuba.algo3.modelo.Construcciones;

import java.util.ArrayList;
import edu.fiuba.algo3.modelo.Costo.Costo;
import edu.fiuba.algo3.modelo.Hexagono;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Grano;
import edu.fiuba.algo3.modelo.Recurso.Mineral;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

public class Ciudad extends Construccion {

    public Ciudad(Jugador duenio) {
        super(duenio);
    }

    public Ciudad() {
        this.costo = new Costo(Grano.class, Grano.class, Mineral.class, Mineral.class, Mineral.class);
    }

    @Override
    public int getPuntosDeVictoria() {
        return 2;
    }

    @Override
    public Recurso generarSegunHexagono(Hexagono hexagono, int numDado) {
        Recurso recurso = hexagono.obtenerRecurso(numDado);

        if (recurso != null) {
            recurso.aumentar(1);
            return recurso;
        }

        return null;
    }

    @Override
    public ArrayList<Recurso> generarSegunVertice(int dado) {

        return this.verticeAsignado.generarRecurso(dado,2);
    }

}
