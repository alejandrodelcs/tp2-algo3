package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Carta.CartaCaballero;
import edu.fiuba.algo3.modelo.Carta.CartaConstruccionCarreteras;
import edu.fiuba.algo3.modelo.Carta.CartaDescubrimiento;
import edu.fiuba.algo3.modelo.Carta.CartaMonopolio;
import edu.fiuba.algo3.modelo.Carta.CartaPuntoVictoria;
import edu.fiuba.algo3.modelo.Comercio.*;

import java.util.*;
import java.util.stream.Collectors;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.Hexagono;
import edu.fiuba.algo3.modelo.Turno.EstadoMoverLadron;
import edu.fiuba.algo3.modelo.Turno.EstadoTurno;
import edu.fiuba.algo3.modelo.Turno.ObservadorTurno;
import edu.fiuba.algo3.vistas.escenas.EscenaJuego;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;

public class ControladorJuego implements ObservadorTurno {
    private Carta cartaDesSeleccionada;
    private SesionDeComercio sesionComercio;
    private boolean seleccionJuador;
    private Jugador jugadorSeleccionado;
    private final Juego juego;
    private final EscenaJuego escenaJuego;
    private Map<Jugador, String> avatarDeJugador = new HashMap<>();
    private List<? extends Carta> cartasDisponibles = List.of(new CartaConstruccionCarreteras(),
            new CartaPuntoVictoria(), new CartaCaballero(), new CartaMonopolio(), new CartaDescubrimiento());

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

        this.juego.turnoActual().agregarObservador(this);

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
        escenaJuego.actualizarVista();
    }

    @Override
    public void onEstadoCambio(EstadoTurno nuevoEstado) {
        if (nuevoEstado instanceof EstadoMoverLadron) {
            iniciarSecuenciaLadron();
        }
    }

    private void iniciarSecuenciaLadron() {
        mostrarAlerta("¡LADRÓN ACTIVADO!", "Salió un 7. Selecciona un hexágono para mover al ladrón.");
        escenaJuego.getTablero().activarSelectorHexagono(this::procesarMovimientoLadron);
    }

    private void procesarMovimientoLadron(Hexagono destino) {
        try {
            juego.turnoActual().moverLadronA(destino);

            escenaJuego.getTablero().actualizarPosicionLadron();
            escenaJuego.getTablero().desactivarSelectorHexagono();

            gestionarRobo(destino);

        } catch (Exception e) {
            mostrarAlerta("Movimiento Inválido", "El ladrón debe moverse a un lugar distinto.");
        }
    }

    private void gestionarRobo(Hexagono hexDondeEstaElLadron) {
        List<Jugador> victimas = hexDondeEstaElLadron.obtenerVictimas();
        Jugador yo = juego.getJugadorActivo();
        victimas.remove(yo);

        if (victimas.isEmpty()) {
            mostrarAlerta("Información", "No hay nadie a quien robar en este lugar.");
            return;
        }

        Jugador victimaElegida = null;

        if (victimas.size() == 1) {
            victimaElegida = victimas.get(0);
        } else {
            victimaElegida = mostrarDialogoEleccionVictima(victimas);
        }

        if (victimaElegida != null) {
            juego.turnoActual().robar(victimaElegida);

            escenaJuego.actualizarVista();

            mostrarAlerta("Robo Exitoso", "Le has robado un recurso a " + victimaElegida.getNombre());
        }

    }

    private Jugador mostrarDialogoEleccionVictima(List<Jugador> victimas) {
        List<String> nombres = victimas.stream().map(Jugador::getNombre).collect(Collectors.toList());

        ChoiceDialog<String> dialog = new ChoiceDialog<>(nombres.get(0), nombres);
        dialog.setTitle("Robar Recurso");
        dialog.setHeaderText("Elige a tu víctima");
        dialog.setContentText("Jugador:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String nombre = result.get();
            return victimas.stream().filter(j -> j.getNombre().equals(nombre)).findFirst().orElse(null);
        }
        return null;
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void construirCarretera() {
        ejecutarAccion(juego::construirCarretera);
    }

    public String getAvatar(Jugador jugador) {
        return avatarDeJugador.get(jugador);

    }

    public void pasarTurno() {
        try {
            juego.pasarTurno();

            this.juego.turnoActual().agregarObservador(this);

            escenaJuego.actualizarVista();

            mostrarAlerta("Cambio de Turno", "Ahora es el turno de: " + juego.getJugadorActivo().getNombre());

        } catch (Exception e) {
            mostrarAlerta("Error", e.getMessage());
        }
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

    public void mostrarManoCartas() {
        ejecutarAccion(escenaJuego::mostrarBarraCartasMano);

    }

    public void cerrarManoCartas() {
        ejecutarAccion(escenaJuego::ocultarManoCartas);
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

    public List<? extends Carta> getTipoDeCartasDisponibles() {
        return this.cartasDisponibles;

    }

    public void seleccionarCartaDesarrollo(Carta carta) {
        this.cartaDesSeleccionada = carta;

    }

    public void usarCartaSeleccionada() {
        this.juego.getJugadorActivo().habilitarCartasDesarrollo();
        this.juego.getJugadorActivo().jugarCartaDesarrollo(cartaDesSeleccionada, juego.getTablero());
        this.escenaJuego.actualizarVista();
    }

}
