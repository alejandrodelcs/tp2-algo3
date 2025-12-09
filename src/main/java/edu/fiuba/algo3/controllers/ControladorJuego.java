package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.vistas.escenas.EscenaJuego;

public class ControladorJuego {
    private Juego juego;
    private EscenaJuego escenaJuego;

    public ControladorJuego(Juego juego, EscenaJuego escenaJuego) {
        this.juego = juego;
        this.escenaJuego = escenaJuego;
    }

    public void tirarDado(){
        juego.tirarDado();
        escenaJuego.actualizarVista();
    }

    public void construirCarretera(){
        juego.construirCarretera();
        escenaJuego.actualizarVista();
    }

    public void pasarTurno(){
        juego.pasarTurno();
        escenaJuego.actualizarVista();
    }

}
