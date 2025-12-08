package edu.fiuba.algo3.modelo.Ladron;
import edu.fiuba.algo3.modelo.Excepciones.MovimientoLadronError;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Hexagono;
import java.util.List;

public class Ladron {
    public Hexagono ubicacionActual;

    public Ladron(Hexagono ubicacionInicial) {
        this.ubicacionActual = ubicacionInicial; //desierto
        this.ubicacionActual.colocarLadron(this); // tieneLadron = true
    }




    public void moverA(Hexagono nuevaUbicacion) {
        if (nuevaUbicacion.equals(this.ubicacionActual)) {
           throw new MovimientoLadronError();
        }
        this.ubicacionActual.colocarLadron(null);
        this.ubicacionActual = nuevaUbicacion;
        this.ubicacionActual.colocarLadron(this);
    }

    public void robar(Jugador ladron) {
        List<Jugador> candidatos = this.ubicacionActual.obtenerVictimas();
        AccionRobar accionRobo = new AccionRobar(ladron, candidatos);
        accionRobo.ejecutar();
    }


    public Hexagono ubicacion() {
        return ubicacionActual;
    }
}
