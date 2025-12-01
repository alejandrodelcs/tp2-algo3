package edu.fiuba.algo3.modelo.Construcciones;

import java.util.List;
import java.util.ArrayList;

import edu.fiuba.algo3.modelo.Hexagono;
import edu.fiuba.algo3.modelo.Inventario;

import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Jugador;

/**
 * ConstruccionNula
 */
public class ConstruccionNula extends Construccion {

    public ConstruccionNula() {
        this.costo = null;
    }

    public int getPuntosDeVictoria() {
        return 0;
    }


    @Override
    public boolean esNula() {
        return true;
    }


    @Override
    public Recurso generarSegunHexagono(Hexagono hexagono, int numDado) {
        return null;
    }

    @Override
    public ArrayList<Recurso> generarSegunVertice(int dado) {
        return null;
    }


    @Override
    public void pagarCon(Inventario inventario) {

    }
}
