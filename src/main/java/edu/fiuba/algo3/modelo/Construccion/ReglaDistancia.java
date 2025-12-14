package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Vertice;
import edu.fiuba.algo3.modelo.Excepciones.ReglaDistanciaException;


public class ReglaDistancia implements ReglaConstruccion {


    @Override
    public void validar(Jugador jugador, Object...ubicaciones) {
        Vertice destino = (Vertice) ubicaciones[0];
        boolean prohibido =
                destino.verticesVecinos()
                        .stream()
                        .anyMatch(Vertice::tieneConstruccion);

        if (prohibido) {
            throw new ReglaDistanciaException();
        }
    }
}
