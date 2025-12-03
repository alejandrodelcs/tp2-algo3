package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.ElementosTablero.Hexagono;

import java.util.List;

public class Ladron {
    private Hexagono ubicacionActual;

    public Ladron(Hexagono ubicacionInicial) {
        this.ubicacionActual = ubicacionInicial; //desierto
        this.ubicacionActual.colocarLadron(); // tieneLadron = true
    }

    public void moverA(Hexagono nuevaUbicacion) {
        /*if (nuevaUbicacion.equals(this.ubicacionActual)) {
           throw new MovimientoLadronError();
        }*/
        this.ubicacionActual.moverLadron(); // tieneLadron = false

        this.ubicacionActual = nuevaUbicacion;
        this.ubicacionActual.colocarLadron(); // tieneLadron = true
    }

    public void robar(Jugador ladron) {
        List<Jugador> candidatos = this.ubicacionActual.obtenerVictimas();

        AccionRobar accionRobo = new AccionRobar(ladron);

        for (Jugador victima : candidatos) {
            if (!victima.equals(ladron)) {
            accionRobo.agregarPosibleVictima(victima);
            }
         }

        accionRobo.ejecutar();
    }
}
