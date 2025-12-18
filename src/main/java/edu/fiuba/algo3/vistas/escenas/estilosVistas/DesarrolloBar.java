package edu.fiuba.algo3.vistas.escenas.estilosVistas;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;

import edu.fiuba.algo3.controllers.ControladorJuego;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Carta.CartaCaballero;
import edu.fiuba.algo3.modelo.Carta.CartaConstruccionCarreteras;
import edu.fiuba.algo3.modelo.Carta.CartaDescubrimiento;
import edu.fiuba.algo3.modelo.Carta.CartaMonopolio;
import edu.fiuba.algo3.modelo.Carta.CartaPuntoVictoria;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Arista;
import edu.fiuba.algo3.modelo.Tablero.Vertice;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * DesarrolloBar
 */
public class DesarrolloBar extends HBox {

    private ControladorJuego controlador;
    private VBox cartaSeleccionadaVisual = null;
    private BotonesVista botonUsar;
    private final Map<Carta, Label> labelsCantidad = new HashMap<>();
    private final Map<Carta, Integer> valoresMostrados = new HashMap<>();
    private List<Object> argumentos;

    public DesarrolloBar(ControladorJuego controlador) {

        this.getStylesheets().add(
                getClass().getResource("/styles/estilos.css").toExternalForm());

        this.controlador = controlador;
        this.argumentos = new ArrayList<>();

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

        this.botonUsar = new BotonesVista("elegir");
        botonUsar.setDisable(true);
        botonUsar.setOnAction(e -> this.armarArgumentos());
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

    public void armarArgumentos() {

        if (controlador.esCartaDesarrollo(new CartaPuntoVictoria())) {
            this.argumentos.add(null);

        } else if (controlador.esCartaDesarrollo(new CartaMonopolio())) {
            Recurso recurso = elegirRecurso();
            this.argumentos.add(recurso);
            this.argumentos.add(controlador.getJuego());

        } else if (controlador.esCartaDesarrollo(new CartaConstruccionCarreteras())) {

            List<Arista> aristas = elegirAristas();

            this.argumentos.add(aristas.get(0));
            this.argumentos.add(aristas.get(1));

        } else if (controlador.esCartaDesarrollo(new CartaDescubrimiento())) {
            Recurso rec1 = elegirRecurso();
            Recurso rec2 = elegirRecurso();

            this.argumentos.add(rec1);
            this.argumentos.add(rec2);

        } else if (controlador.esCartaDesarrollo(new CartaCaballero())) {

            this.argumentos.add(controlador.getJuego());
        }
        controlador.usarCartaSeleccionada(argumentos.toArray());
        argumentos.clear();
        controlador.actualizar();

    }

    public List<Arista> elegirAristas() {
        List<Arista> aristas = controlador.seleccionarAristas();
        return aristas;
    }

    public Recurso elegirRecurso() {

        List<String> opciones = List.of(
                "Madera",
                "Ladrillo",
                "Lana",
                "Grano",
                "Mineral");

        ChoiceDialog<String> dialogo = new ChoiceDialog<>(opciones.get(0), opciones);
        dialogo.setTitle("Seleccionar recurso");
        dialogo.setHeaderText("Carta de desarrollo");
        dialogo.setContentText("Elegí un recurso:");

        Optional<String> resultado = dialogo.showAndWait();

        if (!resultado.isPresent()) {
            return null;
        }

        String elegido = resultado.get();

        switch (elegido) {
            case "Madera":
                return new Madera();
            case "Ladrillo":
                return new Ladrillo();
            case "Lana":
                return new Lana();
            case "Grano":
                return new Grano();
            case "Mineral":
                return new Mineral();
            default:
                return null;
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

        box.getStyleClass().add("carta-seleccionada");

        botonUsar.setDisable(false);

        controlador.seleccionarCartaDesarrollo(carta);
        if (carta instanceof CartaConstruccionCarreteras) {

            controlador.activarSeleccionMultipleAristas();
        } else {
            controlador.desactivarSeleccionMultipleAristas();
        }
    }

    public void limpiarSeleccion() {
        if (cartaSeleccionadaVisual != null) {
            cartaSeleccionadaVisual.getStyleClass().remove("carta-seleccionada");
        }
        cartaSeleccionadaVisual = null;
        botonUsar.setDisable(true);
        argumentos.clear();
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

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
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
