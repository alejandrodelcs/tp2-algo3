package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Carta.CartaDesarrollo;
import edu.fiuba.algo3.modelo.Carta.MazoDesarrollo;
import edu.fiuba.algo3.modelo.Dado.Dado;
import edu.fiuba.algo3.modelo.Excepciones.JugadoresMinimosRegistradosError;
import edu.fiuba.algo3.modelo.Jugador.*;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.*;
import edu.fiuba.algo3.modelo.Turno.Turno;

import java.util.ArrayList;
import java.util.List;

/**
 * Juego
 */
public class Juego {

    private final ArrayList<Jugador> jugadores;
    private final Tablero tablero;
    private Jugador granCaballeria;
    private Jugador granRutaComercial;
    private Turno turno;
    private int indiceTurno = 0;
    private MazoDesarrollo mazo;
    private Dado dado;

    /*
     * public Juego() {
     * this.jugadores = jugadores;
     * this.tablero = new Tablero();
     * this.tablero.construir();
     * }
     */

    public Juego() {
        this.jugadores = new ArrayList<>();// armar bien el inicializador;
        this.tablero = new Tablero();
        this.tablero.construir();
        this.mazo = new MazoDesarrollo();
        this.dado = new Dado();
    }

    public Juego(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
        this.tablero = new Tablero();
        this.tablero.construir();
        this.mazo = new MazoDesarrollo();
        this.turno = new Turno(jugadores.get(0), this.tablero);
        this.dado = new Dado();

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
        if (jugadores == null || jugadores.size() < 3) {
            throw new JugadoresMinimosRegistradosError("Jugadores Menor a 3");
        }

    }

    public int recolectarRecursosDeTodosMenos(Jugador excluido, Recurso tipo) {
        int total = 0;

        for (Jugador j : jugadores) {
            if (!j.equals(excluido)) {
                total += j.recolectarRecursosDelTipo(tipo);
            }
        }
        return total;
    }

    public void pasarTurno() {
        this.turno.pasarTurno(this);
    }

    public CartaDesarrollo comprarCartaDesarrollo() {
        return mazo.comprarCarta(turno.getJugadorActivo());
    }

    public void actualizarGranCaballeria(Jugador jugador) {

        if (!jugador.puedeReclamarGranCaballeria())
            return;

        if (granCaballeria == null ||
                jugador.superaEnCaballerosA(granCaballeria)) {

            if (granCaballeria != null) {
                granCaballeria.restarPuntosVictoria(2);
            }

            granCaballeria = jugador;
            jugador.sumarPuntoVictoria(2);
        }
    }

    public void actualizarGranRutaComercial(Jugador jugador) {

        int longitud = tablero.calcularLaRutaMasLarga(jugador);

        if (longitud < 5)
            return;

        if (granRutaComercial == null ||
                longitud > tablero.calcularLaRutaMasLarga(granRutaComercial)) {

            if (granRutaComercial != null) {
                granRutaComercial.restarPuntosVictoria(2);
            }

            granRutaComercial = jugador;
            jugador.sumarPuntoVictoria(2);
        }
    }

    public void tirarDado() {
        int numero = this.dado.lanzar();
        this.turno.tirarDado(numero);
        for (Jugador jugador : jugadores) {
            jugador.generarRecursosPorConstrucciones(numero);

        }
    }

    public void construirCarretera() {
    }

    public Jugador getJugadorActivo() {
        return this.turno.getJugadorActivo();
    }

    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    public Jugador siguienteJugador() {
        this.indiceTurno = (this.indiceTurno + 1) % jugadores.size();
        return jugadores.get(indiceTurno);
    }

    public int cantidadJugadores() {
        return this.jugadores.size();
    }
}
