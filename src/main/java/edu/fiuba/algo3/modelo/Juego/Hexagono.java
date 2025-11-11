package edu.fiuba.algo3.modelo.Juego;

import edu.fiuba.algo3.modelo.Material.TipoRecurso;
import javafx.scene.paint.Material;

import java.util.ArrayList;
import java.util.List;

public class Hexagono {
    private TipoRecurso recurso;
    //private Ficha ficha;
    private List<Cruce> cruces = new ArrayList<Cruce>(6);

    public void setRecurso(TipoRecurso recurso) {
        this.recurso = recurso;
    }
    //un set de ficha
    public void agregarCruce(Cruce cruce) {
        cruces.add(cruce);
    }

    public void generarProduccionSiCorresponde(int numero) {
        //if(ficha.esIgualA(numero)){
            for(Cruce cruce : cruces) {
                cruce.generarProduccion(this.recurso);
            }
        //}
    }

}
