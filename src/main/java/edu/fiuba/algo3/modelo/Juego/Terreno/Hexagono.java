package edu.fiuba.algo3.modelo.Juego.Terreno;

import edu.fiuba.algo3.modelo.Recurso.TipoRecurso;
import edu.fiuba.algo3.modelo.elementos.*;

import java.util.ArrayList;
import java.util.List;

public class Hexagono {

    private TipoRecurso recurso;
    private Ficha ficha;
    private List<Cruce> cruces = new ArrayList<Cruce>(6);



    public void setRecurso(TipoRecurso recurso) {
        this.recurso = recurso;
    }

    public TipoRecurso getTipoRecurso() {
        return this.recurso;
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
