package edu.fiuba.algo3.modelo.Turno;

import edu.fiuba.algo3.modelo.Carta.CartaDesarrollo;
import edu.fiuba.algo3.modelo.Construccion.Construccion;
import edu.fiuba.algo3.modelo.Construccion.Construible;
import edu.fiuba.algo3.modelo.Dado.Dado;
import edu.fiuba.algo3.modelo.Comercio.Comercio;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Hexagono;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

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
    private boolean primeraVuelta;
    private int numeroTurno;
    private Dado dado;


    private Jugador jugadorActivo;
    private final Tablero tablero;

    public Turno(Jugador jugadorActivo, Tablero tablero) {
        this.jugadorActivo = jugadorActivo;
        this.tablero = tablero;
        this.estadoActual = new EstadoInicial();
        this.primeraVuelta = true;
        this.numeroTurno = 0;
        this.dado = new Dado();
    }

    public void tirarDado(Dado dado) {
       estadoActual.tirarDado(dado, jugadorActivo, this);
   }

    public int tirarDado() {
        return this.dado.lanzar();
    }

    public void cambiarEstado(EstadoTurno nuevo) {
        this.estadoActual = nuevo;

    }

    public void moverLadronA(Hexagono destino) {
        this.estadoActual.moverLadron(this, this.tablero, destino);
    }

    public void robar(Jugador jugadorVictima) {
        this.estadoActual.robar(this, tablero, jugadorVictima, jugadorActivo);
    }

    public void construir(Construible construible, Construccion construccion, Object... ubicaciones) {
        this.estadoActual.construir(this, jugadorActivo, construible, construccion, ubicaciones);

    }

    public void comerciar(Comercio comercio) {
        this.estadoActual.comerciar(this, jugadorActivo, comercio);
        System.out.println(estadoActual.toString() + "comercio\n");
    }

    public void jugarCarta(CartaDesarrollo carta, Object... args) {
        this.estadoActual.jugarCarta(this, jugadorActivo, tablero, carta, args);
    }

    public void pasarTurno(Juego juego) {
        jugadorActivo.habilitarCartasDesarrollo();
        jugadorActivo = juego.siguienteJugador();
        this.estadoActual = new EstadoInicial();

        this.numeroTurno++;
        if (numeroTurno < juego.cantidadJugadores()) {
            this.primeraVuelta = false;
            this.estadoActual = new EstadoInicial();

        }

    }

    public Jugador jugador() {
        return this.jugadorActivo;
    }

    public void habilitarAccionCaballero() {
        this.estadoActual = new EstadoMoverLadron();
    }

    public void avanzarAlSiguienteJugador(Juego juego) {
        this.jugadorActivo = juego.siguienteJugador();
    }

    public Jugador getJugadorActivo() {
        return this.jugadorActivo;
    }

}
