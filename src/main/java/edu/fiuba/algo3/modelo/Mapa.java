package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Construccion.TipoConstruccion;
import edu.fiuba.algo3.modelo.Recurso.Lana;
import edu.fiuba.algo3.modelo.Recurso.Madera;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.ArrayList;

public class Mapa {

    ArrayList<Recurso> recursos;

    public Mapa() {
        this.recursos = new ArrayList<>();
    }

    public ArrayList<Recurso> colocarConstruccion(TipoConstruccion tipoConstruccion) {
        recursos.add(new Lana());
        recursos.add(new Madera());
        return recursos;
    }
}
