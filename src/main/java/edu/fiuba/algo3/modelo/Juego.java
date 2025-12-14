package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Carta.Mazo;
import edu.fiuba.algo3.modelo.Dado.Dado;
import edu.fiuba.algo3.modelo.Excepciones.AccionNoPermitidaException;
import edu.fiuba.algo3.modelo.Excepciones.JugadoresMinimosRegistradosError;
import edu.fiuba.algo3.modelo.Jugador.*;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Tablero.*;
import edu.fiuba.algo3.modelo.Turno.EstadoMoverLadron;
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
    private Turno turnoActual;
    private final Mazo mazo;
    private final Dado dado;
    private int numeroActualDado;
    private boolean debeMoverLadron;
    private int indiceJugadorActual;

    public Juego() {
        this.indiceJugadorActual = 0;
        this.jugadores = new ArrayList<>();// armar bien el inicializador;
        this.tablero = new Tablero();
        this.tablero.construir();
        this.mazo = new Mazo();
        this.dado = new Dado();
    }

    public Juego(ArrayList<Jugador> jugadores) {
        this.indiceJugadorActual = 0;
        this.jugadores = jugadores;
        this.tablero = new Tablero();
        this.tablero.construir();
        this.mazo = new Mazo();
        this.dado = new Dado();
        this.turnoActual = new Turno(jugadorActivo(), tablero, dado);

    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public List<Recurso> getTerrenos() {
        return this.tablero.getTerrenos();

    }

    public void crearJugadores(List<String> nombres, List<String> avatares) {
        for (int i = 0; i < nombres.size(); i++) {
            Jugador jugador = new Jugador(nombres.get(i), new Inventario());
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
        this.turnoActual.pasarTurno(this);
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
        this.turnoActual.tirarDado(this);
        // tablero.producirRecursosSegun(numeroActualDado);
    }

    public void construirCarretera() {
    }

    public Jugador jugadorActivo() {
        return this.jugadores.get(indiceJugadorActual);
    }

    public void agregarJugador(Jugador jugador) {
        jugadores.add(jugador);
    }

    public void finalizarTurnoActual() {
        this.indiceJugadorActual = (this.indiceJugadorActual + 1) % jugadores.size();

        turnoActual = new Turno(jugadorActivo(), tablero, dado);
    }

    public int cantidadJugadores() {
        return this.jugadores.size();
    }

    public int getDadoActual() {
        return this.numeroActualDado;
    }

    public void repartirRecursosPorDado(int numDado) {
        tablero.producirRecursosSegun(numDado);
    }

    public void aplicarPenalidadPorSiete() {
        for (Jugador j : jugadores) {
            j.descartarMitadSiCorresponde();
        }
    }

    public void activarLadron() {
        this.debeMoverLadron = true;
    }

    public void moverLadronA(Hexagono destino) {
        if (!debeMoverLadron) {
            throw new AccionNoPermitidaException("");
        }
        tablero.moverLadronA(destino);
        debeMoverLadron = false;
    }

    public Turno turnoActual() {
        return turnoActual;
    }

    public void resolverTirada(int resultado) {
        this.numeroActualDado = resultado;
        if (resultado == 7) {
            aplicarPenalidadPorSiete();
            turnoActual.cambiarEstado(new EstadoMoverLadron());
        } else {
            tablero.producirRecursosSegun(resultado);
        }
    }

    public Jugador getJugadorActivo() {
        return jugadores.get(indiceJugadorActual);
    }

}
