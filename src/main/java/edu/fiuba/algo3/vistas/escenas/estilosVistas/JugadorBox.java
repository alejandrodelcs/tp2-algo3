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
        this.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10;");
        this.setAlignment(Pos.TOP_LEFT);

        String nombre = jugador.getNombre();

        ImageView avatar = new ImageView(new Image(
                getClass().getResource(jugador.getAvatar()).toExternalForm()));

        avatar.setFitWidth(60);
        avatar.setFitHeight(60);

        VBox derecha = new VBox(5);
        derecha.setAlignment(Pos.CENTER_LEFT);

        HBox filaIcons = new HBox(10);
        filaIcons.setAlignment(Pos.CENTER_LEFT);

        Label iconCartas = new Label("C");
        Label iconConstrucciones = new Label("C");
        Label iconPv = new Label("P");

        filaIcons.getChildren().addAll(iconCartas, iconConstrucciones, iconPv);

        HBox filaValores = new HBox(30);
        filaValores.setAlignment(Pos.CENTER_LEFT);

        Label valorCartas = new Label(String.valueOf(jugador.cantidadCartas()));
        Label valorConstrucciones = new Label(String.valueOf(jugador.cantidadConstrucciones()));
        Label valorPv = new Label(String.valueOf(jugador.cantidadCartas()));// calcular PV

        filaValores.getChildren().addAll(valorCartas, valorConstrucciones, valorPv);
        derecha.getChildren().addAll(filaIcons, filaValores);

        HBox contenedorPrincipal = new HBox(15);
        contenedorPrincipal.setAlignment(Pos.CENTER_LEFT);
        contenedorPrincipal.getChildren().addAll(avatar, derecha);

        this.getChildren().add(contenedorPrincipal);

    }
}
