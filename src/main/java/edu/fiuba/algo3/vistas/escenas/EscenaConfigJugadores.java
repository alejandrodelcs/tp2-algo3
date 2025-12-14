package edu.fiuba.algo3.vistas.escenas;

import edu.fiuba.algo3.controllers.ControladorRegistro;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.*;
import edu.fiuba.algo3.modelo.Recurso.Grano;
import edu.fiuba.algo3.modelo.Recurso.Ladrillo;
import edu.fiuba.algo3.modelo.Recurso.Lana;
import edu.fiuba.algo3.modelo.Recurso.Madera;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class EscenaConfigJugadores extends EscenaGeneral {

    private VBox root;
    private Spinner<Integer> spinnerCantidad;
    private final ControladorRegistro controlador;
    private VBox nombresContainer;
    private Button botonComenzar;
    private String[][] avataresDisponibles = { // quitar
            { "/images/larry.jpeg", "amarillo" },
            { "/images/pj1.jpg", "celeste" },
            { "/images/pj2.jpg", "negro" },
            { "/images/pj3.jpg", "rojo" },
            { "/images/pj4.jpg", "rojo" }
    };

    public EscenaConfigJugadores(Stage stage, ControladorRegistro controlador) {
        super(stage);
        this.controlador = controlador;
    }

    @Override
    protected Pane crearLayout(Stage stage) {

        BorderPane main = new BorderPane();
        main.setStyle("-fx-background-color: linear-gradient(to bottom, #dfe9f3, #ffffff);");

        root = new VBox(35);
        root.setPadding(new Insets(40));
        root.setAlignment(Pos.TOP_CENTER);

        root.setStyle(
                "-fx-background-radius: 20;" +
                        "-fx-border-color: #aaa;" +
                        "-fx-border-radius: 20;" +
                        "-fx-border-width: 1.5;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 25, 0, 0, 5);");

        Label titulo = new Label("Configuración de Jugadores");
        titulo.setStyle(
                "-fx-font-size: 36px;" +
                        "-fx-text-fill: #222;" +
                        "-fx-font-weight: bold;");

        spinnerCantidad = new Spinner<>(3, 4, 2);
        spinnerCantidad.setEditable(false);
        spinnerCantidad.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-padding: 10;");

        Label labelCantidad = new Label("Cantidad de jugadores:");
        labelCantidad.setStyle("-fx-font-size: 24px; -fx-text-fill: #333;");

        VBox cantidadBox = new VBox(10, labelCantidad, spinnerCantidad);
        cantidadBox.setAlignment(Pos.CENTER);

        nombresContainer = new VBox(25);
        nombresContainer.setAlignment(Pos.CENTER);

        generarCamposJugadores(spinnerCantidad.getValue());

        spinnerCantidad.valueProperty().addListener((obs, oldVal, newVal) -> {
            generarCamposJugadores(newVal);
        });

        botonComenzar = new Button("Comenzar partida");
        botonComenzar.setStyle(
                "-fx-font-size: 26px;" +
                        "-fx-padding: 15 40;" +
                        "-fx-background-color: #4CAF50;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 15;");

        botonComenzar.setOnMouseEntered(ev -> botonComenzar.setStyle(
                "-fx-font-size: 26px;" +
                        "-fx-padding: 15 40;" +
                        "-fx-background-color: #45A049;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 15;"));
        botonComenzar.setOnMouseExited(ev -> botonComenzar.setStyle(
                "-fx-font-size: 26px;" +
                        "-fx-padding: 15 40;" +
                        "-fx-background-color: #4CAF50;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 15;"));

        root.getChildren().addAll(titulo, cantidadBox, nombresContainer, botonComenzar);

        main.setCenter(root);
        return main;
    }

    @Override
    protected void crearControladores(Stage stage) {// modiicar

        botonComenzar.setOnAction(e -> {

            ArrayList<Jugador> jugadores = new ArrayList<>();

            for (var nodo : nombresContainer.getChildren()) {// que esto lo haga el controlador
                TextField campo = (TextField) nodo;
                if (!campo.getText().trim().isEmpty()) {
                    System.out.println(campo.getText());
                    Jugador jugadorAgregado = new Jugador(campo.getText(),
                            new Inventario(new Madera(), new Madera(), new Ladrillo(), new Ladrillo(), new Lana(),
                                    new Lana(), new Grano(), new Grano()));

                    jugadores.add(jugadorAgregado);
                }
            }

            if (jugadores.size() < 3) {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Faltan jugadores");
                alerta.setHeaderText("No se puede iniciar la partida");
                alerta.setContentText("Debes ingresar al menos 3 jugadores.");
                alerta.showAndWait();
                return;
            }
            Juego juego = new Juego(jugadores);

            EscenaJuego escenaJuego = new EscenaJuego(stage, juego);

            stage.setScene(escenaJuego.getScene());
            // CAMBIO PARA DEBUGEAR
            stage.setFullScreen(true);
        });
    }

    @Override
    protected void generarEstilos() {
    }

    private void generarCamposJugadores(int cantidad) {
        nombresContainer.getChildren().clear();

        for (int i = 1; i <= cantidad; i++) {
            TextField tf = new TextField();
            tf.setPromptText("Nombre del jugador " + i);
            tf.setMaxWidth(350);
            tf.setPrefHeight(45);

            tf.setStyle(
                    "-fx-font-size: 20px;" +
                            "-fx-padding: 10;" +
                            "-fx-background-color: #f7f7f7;" +
                            "-fx-background-radius: 12;" +
                            "-fx-border-radius: 12;" +
                            "-fx-border-color: #999;" +
                            "-fx-border-width: 1.2;");

            nombresContainer.getChildren().add(tf);
        }
    }
}
