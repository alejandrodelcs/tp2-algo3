package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Tablero.Vertice;
import edu.fiuba.algo3.modelo.Excepciones.ReglaDistanciaException;

import java.util.List;


public class ReglaDistancia implements ReglaConstruccion {

    private final List<Construccion> construcciones;


    public ReglaDistancia(List<Construccion> construcciones) {
        this.construcciones = construcciones;
    }

    @Override
    public void validar(Object...ubicaciones) {
        Vertice destino = (Vertice) ubicaciones[0];
        boolean prohibido =
                destino.verticesVecinos()
                            .stream()
                            .anyMatch(v->
                                    construcciones.stream().anyMatch(c->c.estaEn(v)));

        if (prohibido) {
            throw new ReglaDistanciaException();
        }
    }
}
