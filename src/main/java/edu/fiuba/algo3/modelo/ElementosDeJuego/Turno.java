package edu.fiuba.algo3.modelo.ElementosDeJuego;

import java.util.List;

import edu.fiuba.algo3.modelo.ElementosTablero.Hexagono;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.LanzamientoDados;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

/**
 * Turno
 */
public class Turno {

    private Jugador jugadorActivo;
    private Juego juego;
    private boolean dadoLanzado;
    private LanzamientoDados lanzamientoDados;

    public Turno(Jugador jugadorActivo, Juego juego) {
        this.jugadorActivo = jugadorActivo;
        this.lanzamientoDados = new LanzamientoDados();
        this.juego = juego;
    }

    public Jugador jugadorActivo() {
        return this.jugadorActivo;
    }

    public int tirarDados() {
        if (this.dadoLanzado) {
            throw new IllegalStateException("El dado ya fue lanzado ");// armar un error en carpeta errores
        }

        int valor = this.lanzamientoDados.lanzar();
        this.dadoLanzado = true;

        this.juego.producirSegunDado(valor);
        return valor;
    }

    public void comerciarCon(Jugador otroJugador,
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

    }

}
