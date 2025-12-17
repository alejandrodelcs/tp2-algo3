package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Comercio.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.vistas.escenas.EscenaJuego;

public class ControladorJuego {
    private SesionDeComercio sesionComercio;
    private boolean seleccionJuador;
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
        this.seleccionJuador = false;
    }

    private void setAvatares() {
        List<Jugador> jugadores = this.juego.getJugadores();

        for (int i = 0; i < jugadores.size(); i++) {

            this.avatarDeJugador.put(jugadores.get(i), avataresDisponibles[i][0]);
            this.coloresConstrucciones.put(jugadores.get(i), avataresDisponibles[i][1]);

        }
    }

    private void ejecutarAccion(Runnable accion) {
        accion.run();
        escenaJuego.actualizarVista();
    }

    public void tirarDado() {
        ejecutarAccion(juego::tirarDado);
    }

    public void construirCarretera() {
        ejecutarAccion(juego::construirCarretera);
    }

    public String getAvatar(Jugador jugador) {
        return avatarDeJugador.get(jugador);

    }

    public void pasarTurno() {
        ejecutarAccion(juego::pasarTurno);
    }

    public void actualizar() {
        escenaJuego.actualizarVista();
    }

    public void abrirSeleccionComercio() {
        ejecutarAccion(escenaJuego::mostrarBarraSeleccionComercio);
    }

    public void setSleccion() {
        this.seleccionJuador = true;
    }

    public void abrirComercio() {

        this.cerrarSeleccionComercio();
        ejecutarAccion(escenaJuego::mostrarBarraComercioInterno);

    }

    public void cerrarSeleccionComercio() {
        ejecutarAccion(escenaJuego::ocultarBarraSeleccionComercio);
    }

    public void cerrarComercioInterno() {
        this.seleccionJuador = false;
        ejecutarAccion(escenaJuego::ocultarBarraComercio);
    }

    public void seleccionarJugador(Jugador jugador) {
        this.jugadorSeleccionado = jugador;
        this.escenaJuego.actualizarVista();
    }

    public Jugador getJugadorSeleccionado() {
        return this.jugadorSeleccionado;
    }

    public boolean comercioEstaAbierto() {
        return this.seleccionJuador;
    }

    public String getColor(Jugador jugador) {
        return this.coloresConstrucciones.get(jugador);
    }

    public void armarPaqueteOferta(Map<Class<? extends Recurso>, Integer> oferta) {
        sesionComercio.setOferta(oferta);
    }

    public void armarPaqueteDemanda(Map<Class<? extends Recurso>, Integer> demanda) {
        sesionComercio.setDemanda(demanda);

    }

    public void confirmarComercio() {

        sesionComercio.ejecutar(juego, jugadorSeleccionado);
        escenaJuego.actualizarVista();
    }

    public List<Recurso> getTerrenos() {
        return juego.getTerrenos();
    }

    public void setModoComercio(ModoDeComercio modo) {
        this.sesionComercio = new SesionDeComercio(modo);
    }

    public Juego getJuego() {
        return this.juego;
    }
}
