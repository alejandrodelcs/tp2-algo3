package edu.fiuba.algo3.vistas.escenas;

import javafx.scene.Scene;
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
        layout = this.crearLayout();

        Scene escenePreliminar = stage.getScene();

        if (escenePreliminar != null) {
            scene = new Scene(layout, stage.getScene().getWidth(), stage.getScene().getHeight());
        } else {
            scene = new Scene(layout);
        }

    }

    protected abstract Pane crearLayout();

    public Scene getScene() {
        return this.scene;
    }

}
