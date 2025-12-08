package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.SystemInfo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;

        stage.setScene(new MenuInicio(stage).getScene());
        stage.setWidth(1280);
        stage.setHeight(720);
        stage.setFullScreen(true);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}
