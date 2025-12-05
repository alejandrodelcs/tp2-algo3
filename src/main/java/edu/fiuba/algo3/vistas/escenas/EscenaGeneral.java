package edu.fiuba.algo3.vistas.escenas;

import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * EscenaGeneral
 */
public abstract class EscenaGeneral {

    protected Pane layout;
    Scene scene;

    public EscenaGeneral(Stage stage) {
        this.layout = this.crearLayout(stage);
        this.generarEstilos();
        this.cargarFuenteDeTexto(this.layout);
        // cargarImagenDeFondo(layout);

        Scene escenePreliminar = stage.getScene();

        if (escenePreliminar != null) {
            scene = new Scene(layout, stage.getScene().getWidth(), stage.getScene().getHeight());
        } else {
            scene = new Scene(layout);
        }

        this.crearControladores(stage);
    }

    protected abstract Pane crearLayout(Stage stage);

    protected abstract void crearControladores(Stage stage);

    protected abstract void generarEstilos();

    public Scene getScene() {
        return this.scene;
    }

    private void cargarFuenteDeTexto(Pane layout) {
        Font.loadFont(getClass().getResourceAsStream("/fuentes/boldpixels/BoldPixels.ttf"), 20);
        layout.setStyle("-fx-font-family: 'BoldPixels';");
    }

    private void cargarImagenDeFondo(Pane layout) {
        Image image = new Image(getClass().getResource("/images/madera.jpg").toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        layout.setBackground(new Background(backgroundImage));

    }

}
