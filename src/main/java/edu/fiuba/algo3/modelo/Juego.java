package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Excepciones.JugadoresMinimosRegistradosError;
import edu.fiuba.algo3.modelo.Jugador.*;
import edu.fiuba.algo3.modelo.Tablero.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Juego
 */
public class Juego {

    private ArrayList<Jugador> jugadores;
    private Tablero tablero;

    /*public Juego() {
        this.jugadores = jugadores;
        this.tablero = new Tablero();
        this.tablero.construir();
    }*/

    public Juego() {
        this.jugadores = new ArrayList<>();// armar bien el inicializador;
        this.tablero = new Tablero();
        this.tablero.construir();
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public List<Terreno> getTerrenos() {
        return this.tablero.getTerrenos();

    }

    public void crearJugadores(List<String> nombres, List<String> avatares) {
        for (int i = 0; i < nombres.size(); i++) {
            Jugador jugador = new Jugador(nombres.get(i), new Inventario());
            jugador.setAvatar(avatares.get(i));
            jugadores.add(jugador);
        }

    }

    public void validarJugadores() {
        if (jugadores == null  || jugadores.size() < 3) {
            throw new JugadoresMinimosRegistradosError("Jugadores Menor a 3");
        }

    }


    public void tirarDado() {
    }

    public void construirCarretera() {
    }

    public void pasarTurno() {
    }
}
