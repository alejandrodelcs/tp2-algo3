package edu.fiuba.algo3.vistas.escenas.estilosVistas;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.controllers.ControladorJuego;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

/**
 * JugadoresBar
 */
public class JugadoresBar extends VBox {
    private final ControladorJuego controlador;

    public JugadoresBar(Juego juego, ControladorJuego controlador) {
        this.controlador = controlador;

        this.getStylesheets().add(
                getClass().getResource("/styles/estilos.css").toExternalForm());

        getStyleClass().add("principal-bar");

        configurarEstiloBase();
        refrescarContenido(juego);
    }

    public void actualizar(Juego juego) {
        refrescarContenido(juego);
    }

    private void refrescarContenido(Juego juego) {
        this.getChildren().clear();
        List<Jugador> jugadores = juego.getJugadores();

        Jugador jugadorActivo;
        try {
            jugadorActivo = juego.getJugadorActivo();
        } catch (Exception e) {
            jugadorActivo = null;
        }

        for (Jugador jugador : jugadores) {
            JugadorBox box = new JugadorBox(jugador, this.controlador);

            box.getStyleClass().add("jugador-box");

            if (jugador.equals(jugadorActivo)) {
                box.getStyleClass().add("jugador-activo");
            }

            if (controlador.comercioEstaAbierto()
                    && jugador.equals(controlador.getJugadorSeleccionado())
                    && !jugador.equals(jugadorActivo)) {

                box.getStyleClass().add("jugador-seleccionado");
            }
            this.getChildren().add(box);
        }

    }

    private void configurarEstiloBase() {
        this.setPrefWidth(300);
        this.setSpacing(20);
        this.setAlignment(Pos.CENTER_RIGHT);
        this.setPadding(new Insets(40, 20, 40, 20));
        this.setStyle("-fx-background-color: #4d3a35;");
    }
}
