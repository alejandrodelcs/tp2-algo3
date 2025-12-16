package edu.fiuba.algo3.vistas.escenas.estilosVistas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private final Map<Jugador, JugadorBox> jugadorBoxes = new HashMap<>();

    public JugadoresBar(Juego juego, ControladorJuego controlador) {
        this.controlador = controlador;

        this.getStylesheets().add(
                getClass().getResource("/styles/estilos.css").toExternalForm());

        getStyleClass().add("principal-bar");
        configurarEstiloBase();

        for (Jugador jugador : juego.getJugadores()) {
            JugadorBox box = new JugadorBox(jugador, controlador);
            jugadorBoxes.put(jugador, box);
            this.getChildren().add(box);
        }

        actualizar(juego);
    }

    public void actualizar(Juego juego) {
        Jugador activo;
        try {
            activo = juego.getJugadorActivo();
        } catch (Exception e) {
            activo = null;
        }

        for (Jugador jugador : juego.getJugadores()) {
            JugadorBox box = jugadorBoxes.get(jugador);

            // actualizar valores → dispara animaciones
            box.actualizar(jugador);

            // limpiar estados
            box.getStyleClass().removeAll(
                    "jugador-activo",
                    "jugador-seleccionado");

            if (jugador.equals(activo)) {
                box.getStyleClass().add("jugador-activo");
            }

            if (controlador.comercioEstaAbierto()
                    && jugador.equals(controlador.getJugadorSeleccionado())
                    && !jugador.equals(activo)) {

                box.getStyleClass().add("jugador-seleccionado");
            }
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
