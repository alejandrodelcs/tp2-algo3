package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Juego;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.vistas.escenas.EscenaJuego;

public class ControladorJuego {
    private final Juego juego;
    private final EscenaJuego escenaJuego;
    private final Map<Jugador, String> avatarDeJugador = new HashMap<>();
    private final String[] avataresDisponibles = {
            "/images/larry.jpeg",
            "/images/pj1.jpg",
            "/images/pj2.jpg",
            "/images/pj3.jpg",
            "/images/pj4.jpg"
    };

    public ControladorJuego(Juego juego, EscenaJuego escenaJuego) {
        this.juego = juego;
        this.escenaJuego = escenaJuego;
        this.setAvatares();
    }

    private void setAvatares() {
        List<Jugador> jugadores = this.juego.getJugadores();

        for (int i = 0; i < jugadores.size(); i++) {

            this.avatarDeJugador.put(jugadores.get(i), avataresDisponibles[i]);

        }
    }

    public void tirarDado() {
        juego.tirarDado();
        escenaJuego.actualizarVista();
    }

    public void construirCarretera() {
        juego.construirCarretera();
        escenaJuego.actualizarVista();
    }

    public String getAvatar(Jugador jugador) {
        return avatarDeJugador.get(jugador);

    }

    public void pasarTurno() {
        juego.pasarTurno();
        escenaJuego.actualizarVista();
    }

}
