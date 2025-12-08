package edu.fiuba.algo3.vistas.escenas;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.ElementosDeJuego.Juego;
import edu.fiuba.algo3.modelo.Construcciones.*;
import edu.fiuba.algo3.modelo.ElementosTablero.Vertice;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.effect.DropShadow;
import edu.fiuba.algo3.vistas.TableroView;
import edu.fiuba.algo3.vistas.escenas.estilosVistas.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.function.Supplier;

public class EscenaJuego extends EscenaGeneral {

    public TableroView tableroView;

    public EscenaJuego(Stage stage, Juego juego) {
        super(stage, juego);

    }

    @Override
    protected Pane crearLayout(Stage stage) {
        StackPane root = new StackPane();

        GridPane gridLayout = new GridPane();

        JugadoresBar jugadoresBar = new JugadoresBar(juego);
        gridLayout.add(jugadoresBar, 0, 0);

        Tablero tablero = juego.getTablero();
        TableroView tableroView = new TableroView(tablero);
        this.tableroView = tableroView;
        tableroView.setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

        DropShadow sombra = new DropShadow();
        sombra.setRadius(40);
        sombra.setOffsetY(20);
        sombra.setColor(Color.rgb(0, 0, 0, 0.6));
        tableroView.setEffect(sombra);

        StackPane tableroContainer = new StackPane(tableroView);
        tableroContainer.setAlignment(Pos.CENTER);

        gridLayout.add(tableroContainer, 1, 0);

        VBox panelAcciones = new VBox(20);
        panelAcciones.setAlignment(Pos.CENTER);
        panelAcciones.setPadding(new Insets(15));

        panelAcciones.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-background-radius: 10;");
        panelAcciones.setMaxWidth(300);

        BotonesVista btnDado = new BotonesVista("Tirar Dado");
        btnDado.setOnAction(e -> System.out.println("Tirando dados..."));

        BotonesVista btnConstruir = new BotonesVista("Construir");
        btnConstruir.setOnAction(e -> manejarClickConstruir());

        BotonesVista btnPasar = new BotonesVista("Pasar Turno");
        btnPasar.setOnAction(e -> System.out.println("Pasando turno..."));

        btnDado.setMaxWidth(Double.MAX_VALUE);
        btnConstruir.setMaxWidth(Double.MAX_VALUE);
        btnPasar.setMaxWidth(Double.MAX_VALUE);

        panelAcciones.getChildren().addAll(btnDado, btnConstruir, btnPasar);

        gridLayout.add(panelAcciones, 2, 0);


        CartasBar cartasBar = new CartasBar(juego);
        gridLayout.add(cartasBar, 0, 1, 3, 1);


        ColumnConstraints colIzq = new ColumnConstraints();
        colIzq.setMinWidth(200);

        ColumnConstraints colCentro = new ColumnConstraints();
        colCentro.setHgrow(Priority.ALWAYS);

        ColumnConstraints colDer = new ColumnConstraints();
        colDer.setMinWidth(160);

        gridLayout.getColumnConstraints().addAll(colIzq, colCentro, colDer);

        RowConstraints filaSuperior = new RowConstraints();
        filaSuperior.setVgrow(Priority.ALWAYS);

        RowConstraints filaInferior = new RowConstraints();
        filaInferior.setVgrow(Priority.NEVER);

        gridLayout.getRowConstraints().addAll(filaSuperior, filaInferior);

        root.getChildren().add(gridLayout);

        return root;
    }

    private void manejarClickConstruir() {
        Vertice verticeSeleccionado = tableroView.obtenerVerticeSeleccionado();

        if (verticeSeleccionado == null) {
            mostrarAlerta("Atención", "¡Debes seleccionar un Vértice primero!");
            return;
        }

        Map<String, Supplier<Construccion>> opcionesDeConstruccion = new HashMap<>();

        opcionesDeConstruccion.put("Poblado", () -> new Poblado());
        opcionesDeConstruccion.put("Ciudad",  () -> new Ciudad());

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

                // tableroView.actualizarVisualizacion();

            } catch (Exception e) {
                mostrarAlerta("Error de Construcción", e.getMessage());
            }
        }
    }

    private void ejecutarConstruccion(String tipoConstruccion) {
        Vertice verticeSeleccionado = tableroView.obtenerVerticeSeleccionado();

    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @Override
    protected void crearControladores(Stage stage) {
    }

    @Override
    protected void generarEstilos() {
    }

}
