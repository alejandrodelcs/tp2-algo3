package edu.fiuba.algo3.modelo.Construcciones;

import edu.fiuba.algo3.modelo.Tablero.Vertice;
import edu.fiuba.algo3.modelo.Juego.Jugador;

public class ConstruirAsentamiento implements Construible{


    @Override
    public void construir(Jugador jugador, Construccion construccion, Object... ubicaciones) {
        Vertice vertice = (Vertice) ubicaciones[0];
        jugador.reglaDistancia().validar(vertice);
        construccion.asignarJugador(jugador);
        jugador.descontarPara(construccion);
        jugador.agregarConstruccion(construccion);
        vertice.construir(construccion);
    }
}
