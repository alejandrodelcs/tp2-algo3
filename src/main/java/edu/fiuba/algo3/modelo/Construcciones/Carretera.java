package edu.fiuba.algo3.modelo.Construcciones;

import edu.fiuba.algo3.modelo.Costo.Costo;
import edu.fiuba.algo3.modelo.Tablero.Arista;
import edu.fiuba.algo3.modelo.Tablero.Hexagono;
import edu.fiuba.algo3.modelo.Recurso.Ladrillo;
import edu.fiuba.algo3.modelo.Recurso.Madera;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Vertice;

import java.util.ArrayList;

public class Carretera extends Construccion{

    Arista arista;


    public Carretera() {
        this.costo = new Costo(Madera.class, Ladrillo.class);
    }


    @Override
    public int getPuntosDeVictoria() {
        return 0;
    }

    @Override
    public boolean puedeSerMejorada() {
        return false;
    }


    @Override
    public Recurso generarSegunHexagono(Hexagono hexagono, int numDado) {
        return hexagono.obtenerRecurso(numDado);
    }

    @Override
    public ArrayList<Recurso> generarSegunVertice(int dado) {
        return null;
    }

    public void asignarArista(Arista arista) {
        this.arista = arista;
    }


    @Override
    public boolean esAdyacenteA(Arista otra) {
        return this.arista != null && this.arista.esAdyacenteA(otra);
    }



}
