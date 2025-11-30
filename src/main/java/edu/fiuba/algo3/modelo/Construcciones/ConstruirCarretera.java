package edu.fiuba.algo3.modelo.Construcciones;

import edu.fiuba.algo3.modelo.ElementosTablero.Arista;
import edu.fiuba.algo3.modelo.ElementosTablero.Vertice;
import edu.fiuba.algo3.modelo.Errores.CarreteraNoConectadaError;
import edu.fiuba.algo3.modelo.Jugador;

public class ConstruirCarretera implements Construible{
    @Override
    public void construir(Jugador jugador, Construccion construccion, Object... ubicaciones) {
        Vertice inicio = (Vertice) ubicaciones[0];
        Vertice fin = (Vertice) ubicaciones[1];
        Carretera carretera = (Carretera) construccion;

        Arista nueva = new Arista(inicio, fin);
        carretera.asignarArista(nueva);

        if (!jugador.esAdyacenteA(nueva)) {
            throw new CarreteraNoConectadaError();
        }
        jugador.descontarPara(construccion);
        nueva.colocarCarretera(carretera);
        jugador.agregarConstruccion(construccion);
    }
}
