package edu.fiuba.algo3.vistas.escenas.estilosVistas;

import javafx.scene.image.Image;
import edu.fiuba.algo3.controllers.ControladorJuego;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * ComercioRecursoBox
 */
public class ComercioRecursoBox extends VBox {
    private Recurso recurso;

    public ComercioRecursoBox(Recurso recurso) {
        this.recurso = recurso;
        this.setPrefSize(260, 120);

        this.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background-radius: 0;" +
                        "-fx-border-radius: 0;" +
                        "-fx-border-color: transparent;" +
                        "-fx-border-width: 0;" +
                        "-fx-padding: 10;");
        this.setAlignment(Pos.TOP_RIGHT);

        ImageView avatar = new ImageView(new Image(
                getClass().getResource(this.obtenerRutaImagen(recurso.toString())).toExternalForm()));

        avatar.setFitWidth(70);
        avatar.setFitHeight(70);
        avatar.setStyle(
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 4, 0, 0, 1);" +
                        "-fx-background-radius: 30;" +
                        "-fx-border-radius: 30;" +
                        "-fx-border-color: #dddddd;" +
                        "-fx-border-width: 1;");

        HBox contenedorPrincipal = new HBox(15);
        contenedorPrincipal.setAlignment(Pos.CENTER);
        contenedorPrincipal.getChildren().addAll(avatar);

        this.getChildren().add(contenedorPrincipal);

    }

    private String obtenerRutaImagen(String recurso) {

        switch (recurso.toUpperCase()) {
            case "MADERA":
                return "/images/madera.png";
            case "LADRILLO":
                return "/images/ladrillo.png";
            case "LANA":
                return "/images/lana.png";
            case "GRANO":
                return "/images/grano.png";
            case "MINERAL":
                return "/images/mineral.png";
            case "DESIERTO":
                return "/images/desierto.png";
            default:
                return "/images/mar.png";
        }
    }

}
