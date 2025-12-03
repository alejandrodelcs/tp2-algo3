package edu.fiuba.algo3.modelo.Construcciones;

import edu.fiuba.algo3.modelo.ElementosTablero.Vertice;
import edu.fiuba.algo3.modelo.Errores.ReglaDistanciaException;

public class ReglaDistancia implements ReglaConstruccion{
    @Override
    public void validar(Vertice vertice) {
        if (vertice.tieneVecinoConstruido()) {
            throw new ReglaDistanciaException();
        }
    }
}
