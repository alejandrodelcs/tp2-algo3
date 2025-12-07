package edu.fiuba.algo3.modelo.Turno;

import edu.fiuba.algo3.modelo.Carta.CartaDesarrollo;
import edu.fiuba.algo3.modelo.Construccion.Construccion;
import edu.fiuba.algo3.modelo.Construccion.Construible;
import edu.fiuba.algo3.modelo.Dado.Dado;
import edu.fiuba.algo3.modelo.Jugador.InteraccionJugador;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Hexagono;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

/**
 * 1.- Inicio Turno
 * 2.- Tirar Dados :
 *       - Si sale 7 -> mover ladrón + robar
 *       - si no -> repartir recursos
 * 3.- Fase de acciones
 *      -construir
 *      -comerciar
 *      -jugar cartas desarrollo
 *  4. fin del turno
 */
public class Turno {
    private EstadoTurno estadoActual;
    private final Jugador jugadorActivo;
    private final Tablero tablero;


    public Turno(Jugador jugadorActivo,Tablero tablero) {
        this.jugadorActivo = jugadorActivo;
        this.tablero = tablero;
        this.estadoActual = new EstadoInicial();

    }


    public void tirarDado(Dado dado) {
        estadoActual.tirarDado(dado,  jugadorActivo, this);
    }

    public void cambiarEstado(EstadoTurno nuevo) {
        this.estadoActual = nuevo;

    }

    public void moverLadronA(Hexagono destino) {
        this.estadoActual.moverLadron(this,this.tablero, destino);
    }


    public void robar(){
        this.estadoActual.robar(this, tablero, jugadorActivo);
    }


    public void contruir(Construible construible, Construccion construccion, Object...ubicaciones) {
        this.estadoActual.construir(this,jugadorActivo,construible,construccion,ubicaciones);

    }

    public void comerciar(InteraccionJugador interaccionJugador) {
        this.estadoActual.comerciar(this,jugadorActivo,interaccionJugador);

    }


    public void jugarCarta(CartaDesarrollo carta){
        this.estadoActual.jugarCarta(this, jugadorActivo, carta);
    }


    public void pasarTurno(){
        this.estadoActual.pasarTurno(this, jugadorActivo);
    }


    public Jugador jugador() {
        return this.jugadorActivo;
    }

 /*  public void comerciarCon(Jugador otroJugador,
            List<Class<? extends Recurso>> ofrece,
            List<Class<? extends Recurso>> pide) {

        if (!this.jugadorActivo.tieneEnInventario(ofrece)) {
            throw new IllegalArgumentException("El jugador activo no tiene los recursos");
        }
        if (!otroJugador.tieneEnInventario(pide)) {
            throw new IllegalArgumentException("El jugador destino no tiene los recursos");
        }

        this.jugadorActivo.entregarTipos(otroJugador, ofrece);
        otroJugador.entregarTipos(this.jugadorActivo, pide);

    }

    public void robarA(Jugador otroJugador, Hexagono destino) {
        List<Jugador> victimas = destino.obtenerVictimas();
        Jugador victima = this.jugadorActivo.seleccionarVictima(victimas);

        this.jugadorActivo.robarA(victima);
    }*/


}
