package edu.fiuba.algo3.modelo.Juego.Terreno;

import edu.fiuba.algo3.modelo.Construccion.Construccion;

public class Arista {
    private Construccion construccion = null;
    private Cruce cruceA;
    private Cruce cruceB;


    public void conectar(Cruce cruceA, Cruce cruceB) {
        this.cruceA = cruceA;
        this.cruceB = cruceB;
    }
}
