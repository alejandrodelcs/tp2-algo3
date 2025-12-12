package edu.fiuba.algo3.vistas.escenas.estilosVistas;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;

/**
 * JugadoresBar
 */
public class JugadoresBar extends VBox {

    public JugadoresBar(Juego juego) {
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
            JugadorBox box = new JugadorBox(jugador);

            if (jugador.equals(jugadorActivo)) {
                box.setStyle(
                        "-fx-background-color: #7e57c2;" +
                                "-fx-background-radius: 12;" +
                                "-fx-border-radius: 12;" +
                                "-fx-padding: 10;" +
                                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 8, 0, 2, 4);");
            } else {
                box.setStyle(
                        "-fx-background-color: #6d524c;" +
                                "-fx-background-radius: 12;" +
                                "-fx-border-radius: 12;" +
                                "-fx-padding: 10;");
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
