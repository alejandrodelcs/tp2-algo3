package edu.fiuba.algo3.modelo.Juego.Terreno;

import edu.fiuba.algo3.modelo.Material.TipoRecurso;
import edu.fiuba.algo3.modelo.elementos.*;

import java.util.ArrayList;
import java.util.List;

public class Hexagono {
    private int id;

    private TipoRecurso recurso;
    private Ficha ficha;
    private List<Cruce> cruces = new ArrayList<Cruce>(6);

    public Hexagono(int id) {
        this.id = id;
    }

    public void setRecurso(TipoRecurso recurso) {
        this.recurso = recurso;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    public void agregarCruce(Cruce cruce) {
        cruces.add(cruce);
    }

    public void generarProduccionSiCorresponde(int numero) {
        if(ficha.tieneNumero(numero)){
            for(Cruce cruce : cruces) {
                cruce.generarProduccion(this.recurso);
            }
        }
    }

}
