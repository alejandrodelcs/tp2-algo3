package edu.fiuba.algo3.vistas.escenas.estilosVistas;

import edu.fiuba.algo3.controllers.ControladorJuego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * JugadorBox
 */
public class JugadorBox extends VBox {
    private final ControladorJuego controlador;
    private Jugador jugador;

    private int cartasMostradas;
    private int construccionesMostradas;
    private int pvMostrados;

    private Label valorCartas;
    private Label valorConstrucciones;
    private Label valorPv;

    public JugadorBox(Jugador jugador, ControladorJuego controlador) {
        this.getStylesheets().add(
                getClass().getResource("/styles/estilos.css").toExternalForm());

        this.jugador = jugador;
        this.controlador = controlador;

        this.cartasMostradas = jugador.cantidadCartas();
        this.construccionesMostradas = jugador.cantidadConstrucciones();
        this.pvMostrados = jugador.getPuntosVictoria();

        this.valorCartas = crearValor(cartasMostradas);
        this.valorConstrucciones = crearValor(construccionesMostradas);
        this.valorPv = crearValor(pvMostrados);

        definirContenido();
    }

    public void actualizar(Jugador jugador) {

        actualizarLabel(valorCartas, cartasMostradas, jugador.cantidadCartas());
        cartasMostradas = jugador.cantidadCartas();

        actualizarLabel(valorConstrucciones,
                construccionesMostradas,
                jugador.cantidadConstrucciones());
        construccionesMostradas = jugador.cantidadConstrucciones();

        actualizarLabel(valorPv, pvMostrados, jugador.getPuntosVictoria());
        pvMostrados = jugador.getPuntosVictoria();
    }

    private void actualizarLabel(Label label, int viejo, int nuevo) {
        if (viejo == nuevo)
            return;

        label.setText(String.valueOf(nuevo));

        if (nuevo > viejo) {
            animarCambio(label, true);
        } else {
            animarCambio(label, false);
        }
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

    private void definirContenido() {

        this.getStyleClass().add("jugador-box");
        this.setPrefSize(260, 120);
        this.setAlignment(Pos.TOP_LEFT);

        ImageView avatar = new ImageView(new Image(
                getClass().getResource(controlador.getAvatar(jugador)).toExternalForm()));
        avatar.setFitWidth(70);
        avatar.setFitHeight(70);
        avatar.getStyleClass().add("jugador-avatar");

        Label nombreJugador = new Label(jugador.getNombre());
        nombreJugador.getStyleClass().add("jugador-nombre");

        HBox filaIcons = new HBox(15,
                crearIcono("Rec"),
                crearIcono("Con"),
                crearIcono("Pv"));
        filaIcons.setAlignment(Pos.CENTER_LEFT);

        HBox filaValores = new HBox(35,
                valorCartas,
                valorConstrucciones,
                valorPv);
        filaValores.setAlignment(Pos.CENTER_LEFT);

        VBox datos = new VBox(5, nombreJugador, filaIcons, filaValores);
        datos.getStyleClass().add("jugador-datos");

        HBox contenedor = new HBox(15, avatar, datos);
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
