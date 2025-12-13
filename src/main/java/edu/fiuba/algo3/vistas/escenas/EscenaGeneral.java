package edu.fiuba.algo3.vistas.escenas;

import edu.fiuba.algo3.controllers.ControladorJuego;
import edu.fiuba.algo3.modelo.Juego;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * EscenaGeneral
 */
public abstract class EscenaGeneral {

    protected Juego juego;
    protected Pane layout;
    protected Scene scene;
    protected Stage stage;

    public EscenaGeneral(Stage stage, Juego juego) {
        this.stage = stage;
        this.juego = juego;
        this.init(stage);
    }

    public EscenaGeneral(Stage stage) {
        this.stage = stage;
        this.init(stage);

    }

    public void init(Stage stage) {

        this.layout = this.crearLayout(stage);
        this.generarEstilos();
        this.cargarFuenteDeTexto(this.layout);
        cargarImagenDeFondo(layout);

        scene = new Scene(layout);

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
        Image image = new Image(getClass().getResource("/images/fondo.png").toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        layout.setBackground(new Background(backgroundImage));

    }

    public void mostrar() {
        stage.setScene(this.scene);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.show();
    }

}
