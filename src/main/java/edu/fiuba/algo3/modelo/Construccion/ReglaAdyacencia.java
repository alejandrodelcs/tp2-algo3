package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Excepciones.NoSePuedeConstruirCarreteraError;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Arista;

public class ReglaAdyacencia implements ReglaConstruccion {



    @Override
    public void validar(Jugador jugador, Object... ubicaciones) {
        Arista arista = (Arista)  ubicaciones[0];

        boolean conectado = arista.consultarConexionCon(jugador);

        if (!conectado) {
            throw new NoSePuedeConstruirCarreteraError("Tiene que estar conectado");
        }
    }
}
