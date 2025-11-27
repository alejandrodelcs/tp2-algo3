package edu.fiuba.algo3.modelo.Construcciones;

import java.util.ArrayList;

import edu.fiuba.algo3.modelo.Hexagono;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

public class Ciudad extends Construccion {

    @Override
    public int getPuntosDeVictoria() {
        return 2;
    }

    @Override
    public Recurso generarSegunHexagono(Hexagono hexagono, int numDado) {
        Recurso recurso = hexagono.obtenerRecurso(numDado);

        if (recurso != null) {
            recurso.cantidad = 2;
            return recurso;
        }

        return null;
    }

    @Override
    public ArrayList<Recurso> generarSegunVertice(int dado) {

        return this.verticeAsignado.generarRecurso(dado);
    }
}
