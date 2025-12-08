package edu.fiuba.algo3.vistas.escenas.estilosVistas;

import java.util.ArrayList;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

/**
 * JugadoresBar
 */
public class JugadoresBar extends VBox {

    public JugadoresBar(Juego juego) {
        ArrayList<Jugador> jugadores = juego.getJugadores();

        this.setPrefWidth(300);
        this.setSpacing(20);
        this.setAlignment(Pos.CENTER_RIGHT);
        this.setStyle("-fx-background-color: #4d3a35;" +
                "-fx-background-radius: 20;" +
                "-fx-padding: 40 20 40 20;");

        // --- Agregar una caja por cada jugador ---

        for (Jugador jugador : jugadores) {
            JugadorBox box = new JugadorBox(jugador);
            this.getChildren().add(box);
        }

    }

}
