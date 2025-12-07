package edu.fiuba.algo3.vistas.escenas.estilosVistas;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
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
        Image image = new Image(getClass().getResource("/images/menu1.png").toExternalForm());

        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, new BackgroundSize(100, 100, true, true, true, true));

        this.setBackground(new Background(backgroundImage));

        setStyle(
                "-fx-text-fill: #4A4A4A; " +
                        "-fx-font-size: 30; " +
                        "-fx-padding: 12 30 12 30; " +
                        "-fx-border-color: #8B7265; " +
                        "-fx-border-width: 6; ");

    }

}
