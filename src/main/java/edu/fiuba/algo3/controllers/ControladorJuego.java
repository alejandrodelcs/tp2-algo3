package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Juego;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.vistas.escenas.EscenaJuego;

public class ControladorJuego {
    private boolean comercioAbierto;
    private Jugador jugadorSeleccionado;
    private final Juego juego;
    private final EscenaJuego escenaJuego;
    private Map<Jugador, String> avatarDeJugador = new HashMap<>();
    private Map<Jugador, String> coloresConstrucciones = new HashMap<>();
    private String[][] avataresDisponibles = {
            { "/images/larry.jpeg", "negro" },
            { "/images/pj1.jpg", "celeste" },
            { "/images/pj2.jpg", "amarillo" },
            { "/images/pj3.jpg", "rojo" },

    };

    public ControladorJuego(Juego juego, EscenaJuego escenaJuego) {
        this.juego = juego;
        this.escenaJuego = escenaJuego;
        this.setAvatares();
        this.comercioAbierto = false;
    }

    private void setAvatares() {
        List<Jugador> jugadores = this.juego.getJugadores();

        for (int i = 0; i < jugadores.size(); i++) {

            this.avatarDeJugador.put(jugadores.get(i), avataresDisponibles[i][0]);
            this.coloresConstrucciones.put(jugadores.get(i), avataresDisponibles[i][1]);

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

    public void actualizar() {
        escenaJuego.actualizarVista();
    }

    public void abrirComercio() {
        escenaJuego.mostrarBarraComercio();
        escenaJuego.actualizarVista();
        this.comercioAbierto = true;

    }

    public void cerrarComercio() {
        escenaJuego.ocultarBarraComercio();
        escenaJuego.actualizarVista();
        this.comercioAbierto = false;
    }

    public void seleccionarJugador(Jugador jugador) {
        this.jugadorSeleccionado = jugador;
        this.escenaJuego.actualizarVista();
    }

    public Jugador getJugadorSeleccionado() {
        return this.jugadorSeleccionado;
    }

    public boolean comercioEstaAbierto() {
        return this.comercioAbierto;
    }

    public String getColor(Jugador jugador) {
        return this.coloresConstrucciones.get(jugador);
    }
}
