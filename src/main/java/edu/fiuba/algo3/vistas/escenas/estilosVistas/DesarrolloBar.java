package edu.fiuba.algo3.vistas.escenas.estilosVistas;

import java.security.KeyStore.Entry;
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
    private VBox cartaSeleccionadaVisual = null;
    private Carta cartaSeleccionada = null;
    private BotonesVista botonUsar;

    private final Map<Carta, Label> labelsCantidad = new HashMap<>();
    private final Map<Carta, Integer> valoresMostrados = new HashMap<>();

    public DesarrolloBar(ControladorJuego controlador) {

        this.getStylesheets().add(
                getClass().getResource("/styles/estilos.css").toExternalForm());

        this.controlador = controlador;

        configurarEstiloBase();
        crearCartas();

    }

    public void actualizar() {

        Jugador jugador = controlador.getJuego().getJugadorActivo();

        for (Map.Entry<Carta, Label> entry : labelsCantidad.entrySet()) {
            Carta carta = entry.getKey();
            Label label = entry.getValue();

            int nuevaCantidad = jugador.cantidadCartasTipo(carta);
            label.setText("cant: " + nuevaCantidad);
        }

        limpiarSeleccion();
    }

    public void crearCartas() {

        Jugador jugador = this.controlador.getJuego().getJugadorActivo();

        this.botonUsar = new BotonesVista("usar");
        botonUsar.setDisable(true);
        botonUsar.setOnAction(e -> controlador.usarCartaSeleccionada());
        this.getChildren().add(botonUsar);

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

        box.setOnMouseClicked(e -> seleccionarCarta(box, carta));
        return box;
    }

    private void seleccionarCarta(VBox box, Carta carta) {

        if (cartaSeleccionadaVisual != null) {
            cartaSeleccionadaVisual.getStyleClass().remove("carta-seleccionada");
        }

        cartaSeleccionadaVisual = box;
        cartaSeleccionada = carta;

        box.getStyleClass().add("carta-seleccionada");

        botonUsar.setDisable(false);

        controlador.seleccionarCartaDesarrollo(carta);
    }

    public void limpiarSeleccion() {
        if (cartaSeleccionadaVisual != null) {
            cartaSeleccionadaVisual.getStyleClass().remove("carta-seleccionada");
        }
        cartaSeleccionadaVisual = null;
        cartaSeleccionada = null;
        botonUsar.setDisable(true);
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
