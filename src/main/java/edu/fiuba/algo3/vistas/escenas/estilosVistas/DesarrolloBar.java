package edu.fiuba.algo3.vistas.escenas.estilosVistas;

import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;

import edu.fiuba.algo3.controllers.ControladorJuego;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * DesarrolloBar
 */
public class DesarrolloBar extends HBox {

    private ControladorJuego controlador;

    private final Map<Carta, Label> labelsCantidad = new HashMap<>();
    private final Map<Carta, Integer> valoresMostrados = new HashMap<>();

    public DesarrolloBar(ControladorJuego controlador) {

        this.getStylesheets().add(
                getClass().getResource("/styles/estilos.css").toExternalForm());

        this.controlador = controlador;

        configurarEstiloBase();
        crearCartas();

    }

    public void crearCartas() {

        Jugador jugador = this.controlador.getJuego().getJugadorActivo();

        for (Carta carta : controlador.getTipoDeCartasDisponibles()) {

            if (carta == null) {
                continue;
            }

            if (labelsCantidad.containsKey(carta))
                continue;

            int cantidad = jugador.cantidadCartasTipo(carta);

            VBox cartaBox = crearCartaVisual(carta, cantidad);

            this.getChildren().add(cartaBox);
            valoresMostrados.put(carta, cantidad);

        }

    }

    public VBox crearCartaVisual(Carta carta, int cantidad) {
        VBox box = crearConteindoVertical();

        Label lblTipo = crearLabel(carta.toString());

        Label lblCantidad = crearLabel("cant: " + cantidad);
        labelsCantidad.put(carta, lblCantidad);

        box.getChildren().addAll(lblTipo, lblCantidad);

        return box;
    }

    public VBox crearConteindoVertical() {

        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(10));
        box.getStyleClass().add("carta");
        return box;

    }

    public Label crearLabel(String texto) {
        Label lbl = new Label(texto);
        lbl.getStyleClass().add("carta-cantidad");
        return lbl;
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
