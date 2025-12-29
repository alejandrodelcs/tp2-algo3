package edu.fiuba.algo3.modelo.Construccion;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Vertice;

public class ConstruirAsentamiento implements Construible {

    private List<ReglaConstruccion> reglasConstruccion;

    public ConstruirAsentamiento(List<ReglaConstruccion> reglaConstruccion) {
        this.reglasConstruccion = new ArrayList<>();
        this.reglasConstruccion.addAll(reglaConstruccion);
    }

    @Override
    public void construir(Construccion construccion, Jugador jugador, Object... ubicaciones) {

        Vertice vertice = (Vertice) ubicaciones[0];
        if ((construccion instanceof Poblado)) {

            this.validar(jugador, ubicaciones);

            vertice.construir(construccion);
        } else {
            jugador.mejorarConstruccionUbicadoEn(vertice);
        }

    }

    private void validar(Jugador jug, Object... ubicaciones) {
        for (ReglaConstruccion regla : this.reglasConstruccion) {
            regla.validar(jug, ubicaciones);
        }
    }

    @Override
    public void setRegla(List<ReglaConstruccion> regla) {
        this.reglasConstruccion = regla;
    }
}
