package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Tablero.Arista;
import edu.fiuba.algo3.modelo.Tablero.Vertice;
import edu.fiuba.algo3.modelo.Juego.Jugador;

public class ConstruirCarretera implements Construible {
    @Override
    public void construir(Jugador jugador, Construccion construccion, Object... ubicaciones) {
        Vertice inicio = (Vertice) ubicaciones[0];
        Vertice fin = (Vertice) ubicaciones[1];
        Carretera carretera = (Carretera) construccion;
        Arista nueva = new Arista(inicio, fin);
        carretera.asignarArista(nueva);
        jugador.reglaAdyacencia().validar(nueva);
        jugador.descontarPara(construccion);
        nueva.colocarCarretera(carretera);
        jugador.agregarConstruccion(construccion);
    }
}
