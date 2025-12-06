package edu.fiuba.algo3.vistas.escenas.estilosVistas;

import edu.fiuba.algo3.modelo.ElementosDeJuego.Juego;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * CartasBar
 */
public class CartasBar extends HBox {

    public CartasBar(Juego juego) {

        this.setPrefWidth(300);
        this.setSpacing(20);
        this.setAlignment(Pos.CENTER_RIGHT);
        this.setStyle("-fx-background-color: #4d3a35;" +
                "-fx-background-radius: 20;" +
                "-fx-padding: 40 20 40 20;");
        VBox.setMargin(this, new Insets(20, 20, 20, 20));
    }

}
