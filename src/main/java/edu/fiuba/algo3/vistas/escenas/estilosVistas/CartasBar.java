package edu.fiuba.algo3.vistas.escenas.estilosVistas;

import java.io.InputStream;
import java.util.List;

import edu.fiuba.algo3.modelo.Jugador.Inventario;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Terreno;
import javafx.scene.control.Label;
import edu.fiuba.algo3.modelo.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Recurso.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * CartasBar
 */
public class CartasBar extends HBox {

    public CartasBar(Juego juego) {

        this.setPrefWidth(600);
        this.setSpacing(25);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(20));
        this.setStyle(
                "-fx-background-color: #4d3a35;" +
                        "-fx-background-radius: 20;" +
                        "-fx-border-radius: 20;" +
                        "-fx-padding: 20;");

        // Jugador activo (cuando tengas uno real, lo reemplazás)
        Jugador jugadorActivo = new Jugador("a1", new Inventario(new Ladrillo(), new Madera()));

        for (Terreno terreno : juego.getTerrenos()) {

            Recurso rec = terreno.retornarRecurso();
            if (rec == null) {
                continue;
            }
            System.out.println(rec.toString());

            int cantidadRecurso = jugadorActivo.cantidadDeRecursoTipo(rec.getClass());

            HBox carta = crearCartaVisual(rec.getClass(), cantidadRecurso);

            this.getChildren().add(carta);
        }
    }

    private HBox crearCartaVisual(Class<? extends Recurso> tipo, int cantidad) {

        Recurso recurso;
        try {
            recurso = tipo.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("No se pudo instanciar el recurso " + tipo.getSimpleName(), e);
        }

        // --- Contenedor de la carta ---
        HBox box = new HBox(10);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(10));
        box.setStyle(
                "-fx-background-color: #6d524c;" +
                        "-fx-background-radius: 12;" +
                        "-fx-border-radius: 12;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 8, 0, 2, 4);");

        // --- Imagen ---
        ImageView imgView = new ImageView();
        try {
            String ruta = recurso.getImagen();
            InputStream is = getClass().getResourceAsStream(ruta);

            if (is != null) {
                Image img = new Image(is);
                imgView.setImage(img);

                imgView.setFitHeight(70);
                imgView.setPreserveRatio(true);

            } else {
                System.err.println("⚠️ Imagen no encontrada: " + ruta);
            }

        } catch (Exception e) {
            System.err.println("⚠️ Error cargando imagen: " + e.getMessage());
        }

        // --- Cantidad ---
        Label lbl = new Label("x" + cantidad);
        lbl.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-size: 26px;" +
                        "-fx-font-weight: bold;");

        box.getChildren().addAll(imgView, lbl);
        return box;
    }
}
