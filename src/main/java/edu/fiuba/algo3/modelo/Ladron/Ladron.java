package edu.fiuba.algo3.modelo.Ladron;
import edu.fiuba.algo3.modelo.Excepciones.MovimientoLadronError;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Hexagono;
import java.util.List;

public class Ladron {
    public Hexagono ubicacionActual;

    public Ladron(Hexagono ubicacionInicial) {
        this.ubicacionActual = ubicacionInicial;
        this.ubicacionActual.colocarLadron(this);
    }


    public void moverA(Hexagono nuevaUbicacion) {
        if (nuevaUbicacion.equals(this.ubicacionActual)) {
           throw new MovimientoLadronError();
        }
        this.ubicacionActual.intercambiarFicha(nuevaUbicacion);
        this.ubicacionActual.colocarLadron(null);
        this.ubicacionActual = nuevaUbicacion;
        this.ubicacionActual.colocarLadron(this);
    }


    public Hexagono ubicacion() {
        return ubicacionActual;
    }


    public void robar(Jugador jugadorQueRoba, Jugador victima) {

       victima.entregarRecursoA(jugadorQueRoba);

    }
}
