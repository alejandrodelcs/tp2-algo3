package edu.fiuba.algo3.vistas.escenas.estilosVistas;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;

/**
 * BotonesVista
 */
public class BotonesVista extends Button {

    ScaleTransition transicion;

    public BotonesVista() {
        this.inicializar();
    }

    public BotonesVista(String contenido) {
        super(contenido);
        this.inicializar();
    }

    private void inicializar() {

        // setOnAction();

        this.transicion = new ScaleTransition(Duration.millis(70), this);
        this.transicion.setInterpolator(Interpolator.EASE_OUT);

        transicion = new ScaleTransition(Duration.millis(70), this);
        transicion.setInterpolator(Interpolator.EASE_OUT);
        setOnMouseEntered(e -> {
            transicion.setToX(1.1);
            transicion.setToY(1.1);
            transicion.stop();
            transicion.play();
        });
        setOnMouseExited(e -> {
            transicion.setToX(1);
            transicion.setToY(1);
            transicion.stop();
            transicion.play();
        });
        // setear que hacen los botones

        setStyle("-fx-background-color: #D4C4B0; " +
                "-fx-text-fill: #4A4A4A; " +
                "-fx-font-size: 18; " +
                "-fx-padding: 12 30 12 30; " +
                "-fx-background-radius: 8; " +
                "-fx-border-color: #8B7265; " +
                "-fx-border-width: 2; " +
                "-fx-border-radius: 8;");

    }

}
