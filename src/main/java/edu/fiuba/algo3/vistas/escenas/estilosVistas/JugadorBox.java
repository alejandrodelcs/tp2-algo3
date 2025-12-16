package edu.fiuba.algo3.vistas.escenas.estilosVistas;

import edu.fiuba.algo3.controllers.ControladorJuego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
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
    private final ControladorJuego controlador;

    public JugadorBox(Jugador jugador, ControladorJuego controlador) {
        this.controlador = controlador;

        // Clase base (el estado se agrega afuera)
        this.getStyleClass().add("jugador-box");

        this.setPrefSize(260, 120);
        this.setAlignment(Pos.TOP_LEFT);

        // --- Avatar ---
        ImageView avatar = new ImageView(new Image(
                getClass().getResource(controlador.getAvatar(jugador)).toExternalForm()));
        avatar.setFitWidth(70);
        avatar.setFitHeight(70);
        avatar.getStyleClass().add("jugador-avatar");

        // --- Columna derecha ---
        VBox derecha = new VBox(8);
        derecha.setAlignment(Pos.CENTER_LEFT);

        // --- Íconos ---
        HBox filaIcons = new HBox(15);
        filaIcons.setAlignment(Pos.CENTER_LEFT);

        Label iconCartas = crearIcono("Rec");
        Label iconConstrucciones = crearIcono("Con");
        Label iconPv = crearIcono("Pv");

        filaIcons.getChildren().addAll(iconCartas, iconConstrucciones, iconPv);

        // --- Valores ---
        HBox filaValores = new HBox(35);
        filaValores.setAlignment(Pos.CENTER_LEFT);

        Label valorCartas = crearValor(jugador.cantidadCartas());
        Label valorConstrucciones = crearValor(jugador.cantidadConstrucciones());
        Label valorPv = crearValor(jugador.getPuntosVictoria());

        Label nombreJugador = new Label(jugador.getNombre());
        nombreJugador.getStyleClass().add("jugador-nombre");

        filaValores.getChildren().addAll(
                valorCartas,
                valorConstrucciones,
                valorPv);

        VBox cajaDerecha = new VBox(5, nombreJugador, filaIcons, filaValores);
        cajaDerecha.getStyleClass().add("jugador-datos");

        HBox contenedor = new HBox(15, avatar, cajaDerecha);
        contenedor.setAlignment(Pos.CENTER_LEFT);

        this.setOnMouseClicked(e -> controlador.seleccionarJugador(jugador));

        this.getChildren().add(contenedor);
    }

    private Label crearIcono(String texto) {
        Label label = new Label(texto);
        label.getStyleClass().add("jugador-icono");
        return label;
    }

    private Label crearValor(int valor) {
        Label label = new Label(String.valueOf(valor));
        label.getStyleClass().add("jugador-valor");
        return label;
    }
}
