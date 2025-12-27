package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Vertice;

public class ConstruirAsentamiento implements Construible {

    ReglaConstruccion reglaConstruccion;

    public ConstruirAsentamiento(ReglaConstruccion reglaConstruccion) {
        this.reglaConstruccion = reglaConstruccion;
    }

    @Override
    public void construir(Construccion construccion, Jugador jugador, Object... ubicaciones) {

        Vertice vertice = (Vertice) ubicaciones[0];
        if ((construccion instanceof Poblado)) {

            System.out.println("\n-----> hola");
            reglaConstruccion.validar(jugador, ubicaciones);

            vertice.construir(construccion);
        } else {
            jugador.mejorarConstruccionUbicadoEn(vertice);
        }

    }

    @Override
    public void setRegla(ReglaConstruccion regla) {
        this.reglaConstruccion = regla;
    }
}
