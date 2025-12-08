package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Excepciones.CarreteraNoConectadaError;
import edu.fiuba.algo3.modelo.Tablero.Arista;

import java.util.List;

public class ReglaAdyacencia implements ReglaConstruccion {
    private final List<Construccion> construcciones;

    public ReglaAdyacencia(List<Construccion> construcciones) {
        this.construcciones = construcciones;
    }


    @Override
    public void validar(Object... ubicaciones) {
        Arista nueva = (Arista) ubicaciones[0];
        if (construcciones.isEmpty()) return;
        boolean esAdyacente =
                construcciones.stream()
                        .anyMatch(c -> c.esAdyacenteA(nueva));
        if (!esAdyacente) throw new CarreteraNoConectadaError();
    }
}
