package edu.fiuba.algo3.modelo.Construccion;

import java.util.ArrayList;

import edu.fiuba.algo3.modelo.Jugador.Inventario;

import edu.fiuba.algo3.modelo.Recurso.Recurso;

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
    public String getNombre(){
        return "";
    }

    @Override
    public boolean esNula() {
        return true;
    }


    @Override
    public ArrayList<Recurso> producirSegun(int dado) {
        return null;
    }


    @Override
    public void pagarCon(Inventario inventario) {

    }
}
