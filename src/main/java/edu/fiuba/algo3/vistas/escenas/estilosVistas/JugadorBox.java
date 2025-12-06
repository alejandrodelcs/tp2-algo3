package edu.fiuba.algo3.vistas.escenas.estilosVistas;

import edu.fiuba.algo3.modelo.Jugador;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

/**
 * JugadorBox
 */
public class JugadorBox extends VBox {

    private Jugador jugador;

    public JugadorBox(Jugador jugador) {
        this.jugador = jugador;

        this.setPrefSize(260, 120);
        this.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10;");
        this.setAlignment(Pos.TOP_RIGHT);

        Label nombre = new Label(jugador.getNombre());
        nombre.setStyle(
                "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: black;");

        this.getChildren().add(nombre);
    }
}
