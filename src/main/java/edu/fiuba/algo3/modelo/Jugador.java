package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Construccion.TipoConstruccion;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.ArrayList;

public class Jugador {

    private String nombre;
    private int puntosVictoria;
    private ArrayList<TipoConstruccion> construcciones;
    private ArrayList<Recurso> cartas;
    private Mapa mapa;

    public Jugador(String nombre, Mapa mapa) {
        this.nombre = nombre;
        this.construcciones= new ArrayList<>();
        this.cartas= new ArrayList<>();
        this.puntosVictoria = 0;
        this.mapa = mapa;
    }

    public ArrayList<Recurso> construir(TipoConstruccion tipoConstruccion) {
        construcciones.add(tipoConstruccion);
        if (construcciones.size()==4){
            return mapa.colocarConstruccion(tipoConstruccion);
        }
        return null;
    }

}
