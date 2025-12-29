package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Excepciones.NoSePuedeConstruirCarreteraError;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Ubicacion;

public class ReglaAdyacencia implements ReglaConstruccion {

    @Override
    public void validar(Jugador jugador, Object... ubicaciones) {
        Ubicacion arista = (Ubicacion) ubicaciones[0];

        boolean conectado = arista.consultarConexion(jugador);

        if (!conectado) {
            throw new NoSePuedeConstruirCarreteraError("Tiene que ser adyacente a alguna construccion.");
        }
    }
}
