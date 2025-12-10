package edu.fiuba.algo3.vistas.escenas.estilosVistas;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

/**
 * CajaJugador
 */
public class CajaJugador extends HBox {
    public CajaJugador() {

        this.setSpacing(20);
        this.setAlignment(Pos.CENTER_LEFT);
        this.setStyle(
                "-fx-background-color: #4d3a35;" +
                        "-fx-background-radius: 20;" +
                        "-fx-padding: 20;");
    }
}
