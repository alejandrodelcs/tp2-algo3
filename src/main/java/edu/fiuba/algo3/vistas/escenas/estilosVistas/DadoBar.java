package edu.fiuba.algo3.vistas.escenas.estilosVistas;

import edu.fiuba.algo3.modelo.Juego;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class DadoBar extends HBox {

    private Label numeroDadoLabel;

    public DadoBar(Juego juego) {
        numeroDadoLabel = new Label();
        this.getChildren().add(numeroDadoLabel);

        this.refrescarContenido(juego);
        this.configurarEstiloBase();
    }

    public void actualizar(Juego juego) {
        this.refrescarContenido(juego);
    }

    public void refrescarContenido(Juego juego) {
        int numeroActual = juego.getDadoActual(); // asumiendo que esto existe

        numeroDadoLabel.setText(String.valueOf(numeroActual));
        numeroDadoLabel.setStyle(
                "-fx-font-size: 72px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #ffffff;");
    }

    private void configurarEstiloBase() {
        this.setPrefSize(260, 120);
        this.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background-radius: 12;" +
                        "-fx-border-radius: 12;" +
                        "-fx-border-color: transparent;" +
                        "-fx-border-width: 1;" +
                        "-fx-padding: 10;");
        this.setAlignment(Pos.CENTER);
    }
}
