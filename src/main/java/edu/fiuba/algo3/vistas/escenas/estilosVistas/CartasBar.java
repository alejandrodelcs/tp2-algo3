
package edu.fiuba.algo3.vistas.escenas.estilosVistas;

import java.io.InputStream;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Recurso.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CartasBar extends HBox {

    private final String CARTA_STYLE = "-fx-background-color: #6d524c;" +
            "-fx-background-radius: 12;" +
            "-fx-border-radius: 12;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 8, 0, 2, 4);";

    public CartasBar(Juego juego) {
        configurarEstiloBase();
        refrescarContenido(juego);
    }

    public void actualizar(Juego juego) {
        refrescarContenido(juego);
    }

    private void refrescarContenido(Juego juego) {

        this.getChildren().clear();
        Jugador jugadorActivo = juego.getJugadorActivo();

        this.getChildren().add(crearIconoJugador(jugadorActivo));

        for (Recurso terreno : juego.getTerrenos()) {

            Recurso rec = terreno;
            if (rec == null)
                continue;

            int cantidad = jugadorActivo.cantidadDe(rec.getClass());
            this.getChildren().add(crearCartaVisual(rec.getClass(), cantidad));
        }
    }

    private void configurarEstiloBase() {
        this.setPrefWidth(600);
        this.setSpacing(25);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(20));
        this.setStyle(
                "-fx-background-color: #4d3a35;" +
                        "-fx-background-radius: 0;" +
                        "-fx-border-radius: 0;" +
                        "-fx-padding: 20;");
    }

    private VBox crearIconoJugador(Jugador jugador) {
        VBox box = crearContenedorVertical();
        ImageView iconView = crearImagen(jugador.getAvatar());
        iconView.setFitHeight(70);
        iconView.setPreserveRatio(true);
        Label lbl = crearLabel(jugador.getNombre());
        box.getChildren().addAll(iconView, lbl);
        return box;
    }

    private VBox crearCartaVisual(Class<? extends Recurso> tipo, int cantidad) {
        try {
            Recurso recurso = tipo.getDeclaredConstructor().newInstance();
            VBox box = crearContenedorHorizontal();

            ImageView imgView = crearImagen(recurso.getImagen());
            if (imgView != null) {
                imgView.setFitHeight(70);
                imgView.setPreserveRatio(true);
            }

            Label lbl = crearLabel("x " + cantidad);
            box.getChildren().addAll(imgView, lbl);

            return box;

        } catch (Exception e) {
            throw new RuntimeException("No se pudo instanciar el recurso " + tipo.getSimpleName(), e);
        }
    }

    private VBox crearContenedorHorizontal() {
        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(10));
        box.setStyle(CARTA_STYLE);
        return box;
    }

    private VBox crearContenedorVertical() {
        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(10));
        box.setStyle(CARTA_STYLE);
        return box;
    }

    private ImageView crearImagen(String ruta) {
        try {
            InputStream is = getClass().getResourceAsStream(ruta);
            if (is == null) {
                System.err.println("⚠️ Imagen no encontrada: " + ruta);
                return new ImageView();
            }
            Image img = new Image(is);
            return new ImageView(img);
        } catch (Exception e) {
            System.err.println("⚠️ Error cargando imagen: " + e.getMessage());
            return new ImageView();
        }
    }

    private Label crearLabel(String texto) {
        Label lbl = new Label(texto);
        lbl.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-size: 26px;" +
                        "-fx-font-weight: bold;");
        return lbl;
    }
}
