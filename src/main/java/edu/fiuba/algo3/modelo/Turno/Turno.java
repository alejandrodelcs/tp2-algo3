package edu.fiuba.algo3.modelo.Turno;

import edu.fiuba.algo3.modelo.Carta.CartaDesarrollo;
import edu.fiuba.algo3.modelo.Carta.CartaDescubrimiento;
import edu.fiuba.algo3.modelo.Construccion.Construccion;
import edu.fiuba.algo3.modelo.Dado.Dado;
import edu.fiuba.algo3.modelo.Comercio.Comercio;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Hexagono;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.- Inicio Turno
 * 2.- Tirar Dados :
 * - Si sale 7 -> mover ladrón + robar
 * - si no -> repartir recursos
 * 3.- Fase de acciones
 * -construir
 * -comerciar
 * -jugar cartas desarrollo
 * 4. fin del turno
 */
public class Turno {
    private EstadoTurno estadoActual;
    private final boolean primeraVuelta;
    private final int numeroTurno;
    private final Dado dado;

    private final Jugador jugadorActivo;
    private final Tablero tablero;

    private List<ObservadorTurno> observadores = new ArrayList<>();

    public Turno(Jugador jugadorActivo, Tablero tablero, Dado dado) {
        this.jugadorActivo = jugadorActivo;
        this.tablero = tablero;
        this.estadoActual = new EstadoInicial();
        this.primeraVuelta = true;
        this.numeroTurno = 0;
        this.dado = dado;

        // PARA PROBAR CONSTRUCCIONES, ELIMINAR
        jugadorActivo.agregarRecursos(new Madera(), 10);
        jugadorActivo.agregarRecursos(new Grano(), 10);
        jugadorActivo.agregarRecursos(new Ladrillo(), 10);
        jugadorActivo.agregarRecursos(new Mineral(), 10);
        jugadorActivo.agregarRecursos(new Lana(), 10);
        jugadorActivo.compraCartaDesarrollo(new CartaDescubrimiento());
    }

    public void agregarObservador(ObservadorTurno observador) {
        this.observadores.add(observador);
    }

    public void tirarDado(Juego juego) {

        estadoActual.tirarDado(this, juego, dado);
    }

    public void cambiarEstado(EstadoTurno nuevo) {
        this.estadoActual = nuevo;

        notificarCambioDeEstado();
    }

    private void notificarCambioDeEstado() {
        for (ObservadorTurno obs : observadores) {
            obs.onEstadoCambio(this.estadoActual);
        }
    }

    public void moverLadronA(Hexagono destino) {
        this.estadoActual.moverLadron(this, this.tablero, destino);
    }

    public void robar(Jugador jugadorVictima) {
        this.estadoActual.robar(this, tablero, jugadorVictima, jugadorActivo);
    }

    public void construir(Construccion construccion, Object... ubicaciones) {
        this.estadoActual.construir(this, jugadorActivo, construccion, ubicaciones);

    }

    public void comerciar(Comercio comercio) {
        this.estadoActual.comerciar(this, jugadorActivo, comercio);
        System.out.println(estadoActual.toString() + "comercio\n");
    }

    public void jugarCarta(CartaDesarrollo carta, Object... args) {
        this.estadoActual.jugarCarta(this, jugadorActivo, tablero, carta, args);
    }

    public void pasarTurno(Juego juego) {
        this.estadoActual.pasarTurno(this, jugadorActivo, juego);

    }

}
