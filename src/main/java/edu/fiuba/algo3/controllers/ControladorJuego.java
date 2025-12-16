package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Comercio.Banca;
import edu.fiuba.algo3.modelo.Comercio.Comercio;
import edu.fiuba.algo3.modelo.Comercio.ComercioBanca;
import edu.fiuba.algo3.modelo.Comercio.ComercioInterior;
import edu.fiuba.algo3.modelo.Comercio.ReglaComercio4a1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Turno.Turno;
import edu.fiuba.algo3.vistas.escenas.EscenaJuego;

public class ControladorJuego {
    private boolean comercioAbierto;
    private Jugador jugadorSeleccionado;
    private final Juego juego;
    private Map<Class<? extends Recurso>, Integer> demandaActual, ofertaActual;
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

    public void abrirSeleccionComercio() {
        escenaJuego.mostrarBarraSeleccionComercio();
        escenaJuego.actualizarVista();
    }

    public void abrirComercioInterno(int num) {

        this.cerrarSeleccionComercio();
        escenaJuego.mostrarBarraComercioInterno(num);
        this.comercioAbierto = true;
        escenaJuego.actualizarVista();

    }

    public void cerrarSeleccionComercio() {
        escenaJuego.ocultarBarraSeleccionComercio();
        escenaJuego.actualizarVista();
    }

    public void cerrarComercioInterno() {
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

    public void armarPaqueteOferta(Map<Class<? extends Recurso>, Integer> oferta) {
        this.ofertaActual = oferta;
    }

    public void armarPaqueteDemanda(Map<Class<? extends Recurso>, Integer> demanda) {
        this.demandaActual = demanda;
    }

    private List<Class<? extends Recurso>> expandirPaquete(
            Map<Class<? extends Recurso>, Integer> paquete) {

        List<Class<? extends Recurso>> resultado = new ArrayList<>();

        for (Map.Entry<Class<? extends Recurso>, Integer> entry : paquete.entrySet()) {
            Class<? extends Recurso> tipo = entry.getKey();
            int cantidad = entry.getValue();

            for (int i = 0; i < cantidad; i++) {
                resultado.add(tipo);
            }
        }
        return resultado;
    }

    public void confirmarComercio(int relacionTradeo) {
        if (!comercioAbierto || jugadorSeleccionado == null) {
            return;
        }

        List<Class<? extends Recurso>> oferta = expandirPaquete(ofertaActual);

        List<Class<? extends Recurso>> demanda = expandirPaquete(demandaActual);

        if (relacionTradeo == 1) {
            Comercio comercio = new ComercioInterior(
                    oferta,
                    demanda,
                    juego.getJugadorActivo());
            comercio.aplicarSobre(jugadorSeleccionado);
        } else {
            System.out.println("demadna: " + demanda + " oferta: " + oferta);
            ComercioBanca comercio = new ComercioBanca(oferta, demanda, new ReglaComercio4a1(), new Banca());
            comercio.aplicarSobre(juego.jugadorActivo());
        }

        ofertaActual.clear();
        demandaActual.clear();

        escenaJuego.actualizarVista();
    }

    public List<Recurso> getTerrenos() {
        return juego.getTerrenos();
    }

}
