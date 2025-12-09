package edu.fiuba.algo3.modelo.Carta;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Turno.Turno;

public class CartaDescubrimiento extends CartaDesarrollo{
    @Override
    protected void usar(Jugador jugador, Turno turno) {
        jugador.obtenerDosRecursosAEleccion();
    }
}
