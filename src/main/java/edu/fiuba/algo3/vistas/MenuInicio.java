package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.vistas.escenas.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * MenuScene
 */
public class MenuInicio extends EscenaGeneral {

    public MenuInicio(Stage stage) {
        super(stage);
    }

    protected Pane crearLayout() {
        // ---- ROOT ----
        StackPane root = new StackPane();

        // ---- FONDO ----
        ImageView background = new ImageView(new Image("file:src/main/resources/images/grano.png"));
        background.setFitWidth(1280);
        background.setFitHeight(720);
        background.setPreserveRatio(false);

        // ---- PANEL LATERAL ----
        VBox panel = new VBox(30);
        panel.setPadding(new Insets(40));
        panel.setAlignment(Pos.CENTER_LEFT);

        panel.setStyle(
                "-fx-background-color: rgba(80,0,0,0.55); " + // color oscuro semi-transparente
                        "-fx-background-radius: 20; " +
                        "-fx-border-radius: 20; " +
                        "-fx-border-color: #ffaaaa; " +
                        "-fx-border-width: 3;");
        panel.setMaxWidth(350);

        // ---- BOTONES / ELEMENTOS ----
        Label titulo = new Label("Catan");
        titulo.setStyle("-fx-font-size: 40px; -fx-text-fill: white;");

        Button btnJuego = new Button("Juego nuevo");
        Button btnConfig = new Button("Configuración");
        Button btnVolumen = new Button("Volumen");

        btnJuego.setPrefWidth(250);
        btnConfig.setPrefWidth(250);
        btnVolumen.setPrefWidth(250);

        btnJuego.setStyle("-fx-font-size: 24px;");
        btnConfig.setStyle("-fx-font-size: 24px;");
        btnVolumen.setStyle("-fx-font-size: 24px;");

        panel.getChildren().addAll(titulo, btnJuego, btnConfig, btnVolumen);

        // ---- POSICIONAR PANEL A LA IZQUIERDA ----
        StackPane.setAlignment(panel, Pos.CENTER_LEFT);
        StackPane.setMargin(panel, new Insets(0, 0, 0, 40));

        // ---- AGREGAR TODO ----
        root.getChildren().addAll(background, panel);

        return root;

    }

}
