package edu.fiuba.algo3.modelo.ElementosDeJuego;

import java.util.ArrayList;

import edu.fiuba.algo3.modelo.Jugador;

/**
 * Juego
 */
public class Juego {

    private ArrayList<Jugador> jugadores;

    public Juego() {
        this.jugadores = new ArrayList<>();// armar bien el inicializador;
    }

    public Juego(Jugador jugador) {
        this.jugadores = new ArrayList<>();// armar bien el inicializador;
        this.jugadores.add(jugador);
    }

    public void producirSegunDado(int valorDado) {// testear
        for (Jugador jugador : jugadores) {
            jugador.generarSegunDado(valorDado);

        }

    }
}
