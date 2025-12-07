package edu.fiuba.algo3.vistas.escenas.estilosVistas;

import edu.fiuba.algo3.modelo.Jugador;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * JugadorBox
 */
public class JugadorBox extends VBox {

    public JugadorBox(Jugador jugador) {
        this.setPrefSize(260, 120);
        this.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background-radius: 12;" +
                        "-fx-border-radius: 12;" +
                        "-fx-border-color: transparent;" +
                        "-fx-border-width: 1;" +
                        "-fx-padding: 10;");
        this.setAlignment(Pos.TOP_LEFT);

        // --- Avatar ---
        ImageView avatar = new ImageView(new Image(
                getClass().getResource(jugador.getAvatar()).toExternalForm()));
        avatar.setFitWidth(60);
        avatar.setFitHeight(60);
        avatar.setStyle(
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 4, 0, 0, 1);" +
                        "-fx-background-radius: 30;" +
                        "-fx-border-radius: 30;" +
                        "-fx-border-color: #dddddd;" +
                        "-fx-border-width: 1;");

        // --- Columna derecha ---
        VBox derecha = new VBox(8);
        derecha.setAlignment(Pos.CENTER_LEFT);

        // --- Caja superior: íconos ---
        HBox filaIcons = new HBox(15);
        filaIcons.setAlignment(Pos.CENTER_LEFT);

        Label iconCartas = new Label("📜");
        Label iconConstrucciones = new Label("🏠");
        Label iconPv = new Label("⭐");

        filaIcons.getChildren().addAll(iconCartas, iconConstrucciones, iconPv);

        // --- Caja inferior: valores ---
        HBox filaValores = new HBox(35);
        filaValores.setAlignment(Pos.CENTER_LEFT);

        Label valorCartas = new Label(String.valueOf(jugador.cantidadCartas()));
        Label valorConstrucciones = new Label(String.valueOf(jugador.cantidadConstrucciones()));
        Label valorPv = new Label(String.valueOf(jugador.consultarRecursos()));

        filaValores.getChildren().addAll(valorCartas, valorConstrucciones, valorPv);

        // Cajita con borde para derecha ---
        VBox cajaDerecha = new VBox(5, filaIcons, filaValores);
        cajaDerecha.setStyle(
                "-fx-background-color: #ffffff;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-color: #e1e1e1;" +
                        "-fx-border-width: 1;" +
                        "-fx-padding: 8;");

        // --- Contenedor principal ---
        HBox contenedorPrincipal = new HBox(15);
        contenedorPrincipal.setAlignment(Pos.CENTER_LEFT);
        contenedorPrincipal.getChildren().addAll(avatar, cajaDerecha);

        this.getChildren().add(contenedorPrincipal);

    }
}
