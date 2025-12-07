package edu.fiuba.algo3.modelo.ElementosDeJuego;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.Terreno;

/**
 * Juego
 */
public class Juego {

    private ArrayList<Jugador> jugadores;
    private Tablero tablero;

    public Juego() {
        this.jugadores = new ArrayList<>();// armar bien el inicializador;
        this.tablero = new Tablero();
        this.tablero.construir();

    }

    public Juego(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
        this.tablero = new Tablero();
        this.tablero.construir();
    }

    public Juego(Jugador jugador) {
        this.jugadores = new ArrayList<>();// armar bien el inicializador;
        this.tablero = new Tablero();
        this.tablero.construir();
        this.jugadores.add(jugador);
    }

    public void producirSegunDado(int valorDado) {// testear
        for (Jugador jugador : jugadores) {
            jugador.generarSegunDado(valorDado);

        }

    }

    public ArrayList<Jugador> getJugadores() {
        return this.jugadores;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public List<Terreno> getTerrenos() {
        return this.tablero.getTerrenos();

    }
}
