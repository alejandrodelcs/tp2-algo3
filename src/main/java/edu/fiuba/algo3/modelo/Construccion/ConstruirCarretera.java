package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Arista;

public class ConstruirCarretera implements Construible {

    ReglaConstruccion reglaConstruccion;

    public ConstruirCarretera(ReglaConstruccion reglaConstruccion) {
        this.reglaConstruccion = reglaConstruccion;
    }

    @Override
    public void construir(Construccion construccion, Jugador jugador, Object... ubicaciones) {
        Arista arista = (Arista) ubicaciones[0];
        reglaConstruccion.validar(jugador, arista);
        arista.colocarCarretera((Carretera) construccion);
    }
}
