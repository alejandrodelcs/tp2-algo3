package edu.fiuba.algo3.vistas.escenas.estilosVistas;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.ArrayList;
import java.util.List;
import edu.fiuba.algo3.controllers.ControladorJuego;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Construccion.*;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Vertice;
import edu.fiuba.algo3.vistas.TableroView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.layout.VBox;

/**
 * AccionesBar
 */
public class AccionesBar extends VBox {

    private ControladorJuego controlador;
    private TableroView tableroView;
    private Juego juego;

    public AccionesBar(Juego juego, ControladorJuego controlador, TableroView tablero) {
        this.controlador = controlador;
        this.tableroView = tablero;
        this.juego = juego;

        configurarEstiloBase();
        refrescarContenido();

    }

    private void refrescarContenido() {

        BotonesVista btnDado = new BotonesVista("Tirar Dado");
        btnDado.setOnAction(e -> controlador.tirarDado());

        BotonesVista btnConstruir = new BotonesVista("Construir");
        btnConstruir.setOnAction(e -> manejarClickConstruir());

        BotonesVista btnPasar = new BotonesVista("Pasar Turno");
        btnPasar.setOnAction(e -> controlador.pasarTurno());

        BotonesVista botonComercio = new BotonesVista("Comerciar");
        botonComercio.setOnAction(e -> controlador.abrirComercio());

        botonComercio.setMaxWidth(Double.MAX_VALUE);
        btnDado.setMaxWidth(Double.MAX_VALUE);
        btnConstruir.setMaxWidth(Double.MAX_VALUE);
        btnPasar.setMaxWidth(Double.MAX_VALUE);

        this.getChildren().addAll(btnDado, btnConstruir, botonComercio, btnPasar);

    }

    private void configurarEstiloBase() {

        this.setPrefWidth(300);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(15));

        this.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-background-radius: 10;");
        this.setMaxWidth(300);
    }

    private void manejarClickConstruir() {
        Vertice verticeSeleccionado = tableroView.obtenerVerticeSeleccionado();
        Jugador jugador = juego.getJugadorActivo();

        if (verticeSeleccionado == null) {
            mostrarAlerta("Atención", "¡Debes seleccionar un Vértice primero!");
            return;
        }

        Map<String, Supplier<Construccion>> opcionesDeConstruccion = new HashMap<>();

        opcionesDeConstruccion.put("Poblado", () -> new Poblado());
        opcionesDeConstruccion.put("Ciudad", () -> new Ciudad());

        List<String> listaNombres = new ArrayList<>(opcionesDeConstruccion.keySet());
        ChoiceDialog<String> dialogo = new ChoiceDialog<>("Poblado", listaNombres);
        dialogo.setTitle("Construcción");
        dialogo.setHeaderText("¿Qué deseas construir?");
        dialogo.setContentText("Selecciona:");

        Optional<String> resultado = dialogo.showAndWait();

        if (resultado.isPresent()) {
            String nombreElegido = resultado.get();

            try {
                Supplier<Construccion> constructor = opcionesDeConstruccion.get(nombreElegido);

                Construccion nuevaObra = constructor.get();

                System.out.println(" Construyendo " + nombreElegido + "...");

                verticeSeleccionado.construir(nuevaObra);
                jugador.construir(new ConstruirAsentamiento(), nuevaObra, verticeSeleccionado);

                // tableroView.actualizarVisualizacion();

            } catch (Exception e) {
                mostrarAlerta("Error de Construcción", e.getMessage());
            }
        }
        controlador.actualizar();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
