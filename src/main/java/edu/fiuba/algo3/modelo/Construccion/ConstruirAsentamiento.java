package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Tablero.Vertice;
import edu.fiuba.algo3.modelo.Jugador.Jugador;

import java.util.Arrays;
import java.util.List;

public class ConstruirAsentamiento implements Construible{


    @Override
    public void construir(Jugador jugador, Construccion construccion, Object... ubicaciones) {
        Vertice vertice = (Vertice) ubicaciones[0];
        construccion.asignarJugador(jugador);
        jugador.reglaDistancia().validar(vertice);
        jugador.descontarPara(construccion);
        jugador.agregarConstruccion(construccion);
        vertice.construir(construccion);
    }
}
