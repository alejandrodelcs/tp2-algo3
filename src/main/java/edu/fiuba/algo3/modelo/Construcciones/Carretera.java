package edu.fiuba.algo3.modelo.Construcciones;

import edu.fiuba.algo3.modelo.Hexagono;
import edu.fiuba.algo3.modelo.Inventario;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.ArrayList;

public class Carretera extends Construccion{


    @Override
    public int getPuntosDeVictoria() {
        return 0;
    }



    @Override
    public Recurso generarSegunHexagono(Hexagono hexagono, int numDado) {
        return hexagono.obtenerRecurso(numDado);
    }

    @Override
    public ArrayList<Recurso> generarSegunVertice(int dado) {
        return null;
    }


    @Override
    public void consumir(Inventario inventario) {

    }
}
