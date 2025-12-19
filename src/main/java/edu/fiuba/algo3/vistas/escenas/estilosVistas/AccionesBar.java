package edu.fiuba.algo3.vistas.escenas.estilosVistas;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.ArrayList;
import java.util.List;
import edu.fiuba.algo3.controllers.ControladorJuego;
import edu.fiuba.algo3.modelo.Costo.ReglaCostoConstruccion;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Construccion.*;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Arista;
import edu.fiuba.algo3.modelo.Tablero.Vertice;
import edu.fiuba.algo3.vistas.AristaView;
import edu.fiuba.algo3.vistas.TableroView;
import edu.fiuba.algo3.vistas.VerticeView;
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
        botonComercio.setOnAction(e -> controlador.abrirSeleccionComercio());

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
        VerticeView verticeVisual = tableroView.obtenerVerticeSeleccionadoVisual();
        AristaView aristaVisual = tableroView.obtenerAristaSeleccionadaVisual();

        if (verticeVisual == null && aristaVisual == null) {
            mostrarAlerta("Atención", "¡Debes seleccionar un lugar en el tablero primero!");
            return;
        }

        Jugador jugador = juego.getJugadorActivo();
        List<String> opciones = new ArrayList<>();

        if (verticeVisual != null) {
            Vertice verticeModelo = verticeVisual.getVerticeModelo();

            if (!verticeModelo.tieneConstruccion()) {
                opciones.add("Poblado");
            } else if (verticeModelo.tienePoblado() && verticeModelo.esDuenio(jugador)) {
                opciones.add("Ciudad");
            }
        }

        if (aristaVisual != null) {
            Arista aristaModelo = aristaVisual.getAristaModelo();
            if (!aristaModelo.tieneConstruccion()) {
                opciones.add("Carretera");
            }
        }

        if (opciones.isEmpty()) {
            mostrarAlerta("Construcción",
                    "No puedes construir nada en la selección actual (lugar ocupado o inválido).");
            return;
        }

        ChoiceDialog<String> dialogo = new ChoiceDialog<>(opciones.get(0), opciones);
        dialogo.setTitle("Construcción");
        dialogo.setHeaderText("Construcción");
        dialogo.setContentText("¿Qué deseas construir?");

        Optional<String> resultado = dialogo.showAndWait();

        if (resultado.isPresent()) {
            String nombreElegido = resultado.get();

            try {
                if (nombreElegido.equals("Carretera")) {
                    if (aristaVisual == null)
                        throw new RuntimeException("Error visual: Arista perdida");

                    Carretera carretera = new Carretera(new ReglaCostoConstruccion());
                    Arista aristaModelo = aristaVisual.getAristaModelo();

                    controlador.construir(carretera, aristaModelo);

                    aristaVisual.actualizarVisualizacion();
                    aristaVisual.deseleccionar();
                }

                else {
                    if (verticeVisual == null)
                        throw new RuntimeException("Error visual: Vértice perdido");

                    Construccion nuevaObra;
                    Vertice verticeModelo = verticeVisual.getVerticeModelo();

                    if (nombreElegido.equals("Poblado")) {
                        nuevaObra = new Poblado();
                        controlador.construir(nuevaObra, verticeModelo);
                    } else {
                        jugador.mejorarConstruccionUbicadoEn(verticeModelo);
                    }

                    verticeVisual.actualizarVisualizacion();
                    verticeVisual.deseleccionar();
                }

                System.out.println("Construcción de " + nombreElegido + " exitosa.");

            } catch (Exception e) {
                mostrarAlerta("Error de Construcción", e.getMessage()); // Maneja falta de recursos o reglas
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
