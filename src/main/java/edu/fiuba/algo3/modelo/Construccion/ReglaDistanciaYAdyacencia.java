
package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Excepciones.ReglaDistanciaException;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Vertice;

/**
 * NoReglaAdyacencia
 */
public class ReglaDistanciaYAdyacencia implements ReglaConstruccion {

    @Override
    public void validar(Jugador jugador, Object... ubicaciones) {

        Vertice destino = (Vertice) ubicaciones[0];

        boolean carretera = destino.tieneCarreteraDel(jugador);

        if (!carretera) {
            throw new ReglaDistanciaException("no tiene una carretera adyacente");
        }

        boolean prohibido = destino.verticesVecinos()
                .stream()
                .anyMatch(Vertice::tieneConstruccion);

        if (prohibido) {
            throw new ReglaDistanciaException("Ya hay una construccion adyacente.");
        }

    }
}
