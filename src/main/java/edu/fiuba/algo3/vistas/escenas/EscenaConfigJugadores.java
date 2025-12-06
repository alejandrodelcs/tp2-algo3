package edu.fiuba.algo3.vistas.escenas;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Inventario;
import edu.fiuba.algo3.modelo.ElementosDeJuego.Juego;

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
    private VBox nombresContainer;
    private Button botonComenzar;

    public EscenaConfigJugadores(Stage stage) {
        super(stage);
        this.init(stage);
    }

    @Override
    protected Pane crearLayout(Stage stage) {

        root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.TOP_CENTER);

        spinnerCantidad = new Spinner<>(2, 4, 2);
        spinnerCantidad.setEditable(false);

        Label labelCantidad = new Label("Cantidad de jugadores:");

        VBox cantidadBox = new VBox(10, labelCantidad, spinnerCantidad);
        cantidadBox.setAlignment(Pos.CENTER);

        nombresContainer = new VBox(15);
        nombresContainer.setAlignment(Pos.CENTER);

        generarCamposJugadores(spinnerCantidad.getValue());

        spinnerCantidad.valueProperty().addListener((obs, oldVal, newVal) -> {
            generarCamposJugadores(newVal);
        });

        botonComenzar = new Button("Comenzar partida");

        root.getChildren().addAll(cantidadBox, nombresContainer, botonComenzar);

        return root;
    }

    @Override
    protected void crearControladores(Stage stage) {

        botonComenzar.setOnAction(e -> {

            ArrayList<Jugador> jugadores = new ArrayList<>();

            for (var nodo : nombresContainer.getChildren()) {
                TextField campo = (TextField) nodo;
                jugadores.add(new Jugador(campo.getText(), new Inventario()));
            }

            Juego juego = new Juego(jugadores);

            EscenaJuego escenaJuego = new EscenaJuego(stage, juego);

            stage.setScene(escenaJuego.getScene());
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
            tf.setMaxWidth(250);
            nombresContainer.getChildren().add(tf);
        }
    }
}
