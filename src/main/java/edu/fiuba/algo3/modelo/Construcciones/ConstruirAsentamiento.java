package edu.fiuba.algo3.modelo.Construcciones;

import edu.fiuba.algo3.modelo.ElementosTablero.Vertice;
import edu.fiuba.algo3.modelo.Jugador;

import java.util.List;

public class ConstruirAsentamiento implements Construible{


    @Override
    public void construir(Jugador jugador, Construccion construccion, Object... ubicaciones) {
        Vertice vertice = (Vertice) ubicaciones[0];

        new ReglaDistancia().validar(vertice);

        construccion.asignarJugador(jugador);
        vertice.construir(construccion);
        jugador.descontarPara(construccion);
        jugador.agregarConstruccion(construccion);
    }
}
