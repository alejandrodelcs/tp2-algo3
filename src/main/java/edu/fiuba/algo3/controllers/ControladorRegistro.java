package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Excepciones.JugadoresMinimosRegistradosError;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.vistas.Alertas.AlertaWarning;
import edu.fiuba.algo3.vistas.escenas.EscenaConfigJugadores;
import edu.fiuba.algo3.vistas.escenas.EscenaJuego;
import javafx.stage.Stage;

import java.util.List;

public class ControladorRegistro {
    private final Juego juego;
    private final AlertaWarning alertaWarning;

    public ControladorRegistro(AlertaWarning alertaWarning,
                               Juego juego) {
        this.alertaWarning = alertaWarning;
        this.juego = juego;
    }

    public void registroJugadores(List<String> nombresJugadores,
                                  List<String> avataresSeleccionados,
                                  Stage stage){
        try{
            juego.crearJugadores(
                    nombresJugadores, avataresSeleccionados);
            juego.validarJugadores();

            EscenaJuego escenaJuego = new EscenaJuego(stage, this.juego);
            escenaJuego.mostrar();


        }catch (JugadoresMinimosRegistradosError e){
            alertaWarning.mostrarWarning("Faltan Jugadores",
                    "No se puede iniciar la partida",
                    "Debe ingresar al menos 3 jugadores");
        }


    }

}
