package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Excepciones.CarreteraNoConectadaError;

import edu.fiuba.algo3.modelo.Excepciones.NoSePuedeConstruirCarreteraError;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Arista;
import edu.fiuba.algo3.modelo.Tablero.Vertice;

public class ReglaAdyacencia implements ReglaConstruccion {

    private final Jugador jugador;

    public ReglaAdyacencia(Jugador jugador) {
        this.jugador = jugador;
    }

    @Override
    public void validar(Object... ubicaciones) {
        Arista arista = (Arista)  ubicaciones[0];

        boolean conectado = arista.consultarConexionCon(jugador);

        if (!conectado) {
            throw new NoSePuedeConstruirCarreteraError("");
        }
    }
}
