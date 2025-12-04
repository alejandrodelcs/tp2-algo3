package edu.fiuba.algo3.modelo.Juego;

import java.util.ArrayList;

/**
 * Juego
 */
public class Juego {

    private ArrayList<Jugador> jugadores;

    public Juego() {
        this.jugadores = new ArrayList<>();// armar bien el inicializador;
    }

    public void producirSegunDado(int valorDado) {// testear
        for (Jugador jugador : jugadores) {
            jugador.generarSegunDado(valorDado);

        }

    }
}
