
package edu.fiuba.algo3.vistas.escenas.estilosVistas;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import edu.fiuba.algo3.controllers.ControladorJuego;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Recurso.*;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class CartasBar extends HBox {

    private final ControladorJuego controlador;

    private final Map<Class<? extends Recurso>, Label> labelsCantidad = new HashMap<>();
    private final Map<Class<? extends Recurso>, Integer> valoresMostrados = new HashMap<>();

    private Juego juego;

    private ImageView imgAvatar;
    private Label lblNombreJugador;

    public CartasBar(ControladorJuego controlador) {

        this.controlador = controlador;
        this.juego = controlador.getJuego();

        this.getStylesheets().add(
                getClass().getResource("/styles/estilos.css").toExternalForm());
        configurarEstiloBase();
        crearCartas();
        actualizar();
    }

    private void crearCartas() {

        Jugador jugadorActivo = this.juego.getJugadorActivo();

        this.getChildren().add(crearIconoJugador(jugadorActivo));

        for (Recurso terreno : this.juego.getTerrenos()) {

            if (terreno == null)
                continue;

            Class<? extends Recurso> tipo = terreno.getClass();

            if (labelsCantidad.containsKey(tipo))
                continue;

            int cantidad = jugadorActivo.cantidadDe(tipo);

            VBox carta = crearCartaVisual(tipo, cantidad);

            this.getChildren().add(carta);
            valoresMostrados.put(tipo, cantidad);
        }
    }

    public void actualizar() {

        Jugador jugadorActivo = this.juego.getJugadorActivo();

        if (this.imgAvatar != null && this.lblNombreJugador != null) {
            this.lblNombreJugador.setText(jugadorActivo.getNombre());

            String rutaAvatar = controlador.getAvatar(jugadorActivo);
            try {
                InputStream is = getClass().getResourceAsStream(rutaAvatar);
                if (is != null) {
                    this.imgAvatar.setImage(new Image(is));
                }
            } catch (Exception e) {
                System.err.println("Error actualizando avatar: " + e.getMessage());
            }
        }

        for (var entry : labelsCantidad.entrySet()) {

            Class<? extends Recurso> tipo = entry.getKey();
            Label label = entry.getValue();

            int viejo = valoresMostrados.getOrDefault(tipo, 0);
            int nuevo = jugadorActivo.cantidadDe(tipo);

            if (nuevo > viejo) {
                animarCambio(label, true);
                valoresMostrados.put(tipo, nuevo);
            } else if (nuevo < viejo) {
                label.setText("x " + nuevo);
                animarCambio(label, false);
            }

            label.setText("x " + nuevo);

            valoresMostrados.put(tipo, nuevo);
        }
    }

    private VBox crearIconoJugador(Jugador jugador) {
        VBox box = crearContenedorVertical();

        this.imgAvatar = crearImagen(this.controlador.getAvatar(jugador));
        this.imgAvatar.setFitHeight(70);
        this.imgAvatar.setPreserveRatio(true);

        this.lblNombreJugador = crearLabel(jugador.getNombre());

        box.getChildren().addAll(this.imgAvatar, this.lblNombreJugador);
        return box;
    }

    private VBox crearCartaVisual(Class<? extends Recurso> tipo, int cantidad) {
        try {
            Recurso recurso = tipo.getDeclaredConstructor().newInstance();
            VBox box = crearContenedorVertical();

            ImageView imgView = crearImagen(obtenerRutaImagen(recurso.toString()));
            imgView.setFitHeight(70);
            imgView.setPreserveRatio(true);

            Label lblCantidad = crearLabel("x " + cantidad);

            labelsCantidad.put(tipo, lblCantidad);

            box.getChildren().addAll(imgView, lblCantidad);
            return box;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private VBox crearContenedorVertical() {
        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(10));
        box.getStyleClass().add("carta");
        return box;
    }

    private ImageView crearImagen(String ruta) {
        try {
            InputStream is = getClass().getResourceAsStream(ruta);
            if (is == null) {
                System.err.println("Imagen no encontrada: " + ruta);
                return new ImageView();
            }
            Image img = new Image(is);
            return new ImageView(img);
        } catch (Exception e) {
            System.err.println("Error cargando imagen: " + e.getMessage());
            return new ImageView();
        }
    }

    private Label crearLabel(String texto) {
        Label lbl = new Label(texto);
        lbl.getStyleClass().add("carta-cantidad");
        return lbl;
    }

    private void animarCambio(Label label, boolean sube) {

        String clase = sube ? "valor-sube" : "valor-baja";
        label.getStyleClass().add(clase);

        ScaleTransition scale = new ScaleTransition(Duration.millis(300), label);
        scale.setFromX(1.0);
        scale.setFromY(1.0);
        scale.setToX(1.25);
        scale.setToY(1.25);
        scale.setAutoReverse(true);
        scale.setCycleCount(2);

        scale.setOnFinished(e -> label.getStyleClass().remove(clase));
        scale.play();
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
}
