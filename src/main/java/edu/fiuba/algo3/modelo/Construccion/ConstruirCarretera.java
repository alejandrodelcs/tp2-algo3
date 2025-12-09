package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Tablero.Arista;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Vertice;

public class ConstruirCarretera implements Construible {
    @Override
    public void construir(Jugador jugador, Construccion construccion, Object... ubicaciones) {
        Arista arista = (Arista) ubicaciones[0];
        Carretera carretera = (Carretera) construccion;
        jugador.reglaAdyacencia().validar(arista);
        carretera.asignarArista(arista);
        jugador.descontarPara(construccion);
        arista.colocarCarretera(carretera);

    }
}
