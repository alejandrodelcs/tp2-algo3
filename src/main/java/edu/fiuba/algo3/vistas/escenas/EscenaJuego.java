package edu.fiuba.algo3.vistas.escenas;

import edu.fiuba.algo3.controllers.ControladorJuego;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Construccion.*;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import edu.fiuba.algo3.vistas.TableroView;
import edu.fiuba.algo3.vistas.escenas.estilosVistas.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;
import java.util.function.Supplier;

public class EscenaJuego extends EscenaGeneral {

    private TableroView tableroView;
    private JugadoresBar jugadoresBar;
    private CartasBar cartasBar;
    private Tablero tablero;
    private DadoBar dadoBar;
    private StackPane panelDerecho;
    private ComercioBar barraComercio;
    private AccionesBar panelAcciones;

    public EscenaJuego(Stage stage, Juego juego) {
        super(stage, juego);

    }

    @Override
    protected Pane crearLayout(Stage stage) {
        ControladorJuego controlador = new ControladorJuego(juego, this);
        StackPane root = new StackPane();

        GridPane gridLayout = new GridPane();

        this.jugadoresBar = new JugadoresBar(juego, controlador);

        this.tablero = juego.getTablero();
        TableroView tableroView = new TableroView(tablero);
        this.tableroView = tableroView;
        tableroView.setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

        this.aplicarSombra();// agregar algun efecto

        StackPane tableroContainer = new StackPane(this.tableroView);
        tableroContainer.setAlignment(Pos.CENTER);

        this.panelAcciones = new AccionesBar(this.juego, controlador, this.tableroView);
        this.barraComercio = new ComercioBar(this.juego, controlador, this.tableroView);

        barraComercio.setVisible(false);
        barraComercio.setManaged(false);

        this.panelDerecho = new StackPane(panelAcciones, barraComercio);
        this.panelDerecho.setAlignment(Pos.TOP_CENTER);

        this.cartasBar = new CartasBar(juego, controlador);

        this.dadoBar = new DadoBar(juego);

        gridLayout.add(jugadoresBar, 0, 0);
        gridLayout.add(tableroContainer, 1, 0);
        gridLayout.add(panelDerecho, 2, 0);
        gridLayout.add(cartasBar, 0, 1, 3, 1);
        gridLayout.add(dadoBar, 2, 1);

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

    private void ejecutarConstruccion(String tipoConstruccion) {
        Vertice verticeSeleccionado = tableroView.obtenerVerticeSeleccionado();

    }

    @Override
    protected void crearControladores(Stage stage) {
        // artasBar.getBotonTirarDado().setOnAction(e -> controlador.tirarDado());
        // jugadoresBar.getBotonPasarTurno().setOnAction(e -> controlador.pasarTurno());
    }

    @Override
    protected void generarEstilos() {

    }

    public void actualizarVista() {
        cartasBar.actualizar(juego);
        jugadoresBar.actualizar(juego);
        tableroView.actualizar(juego.getTablero());
        this.dadoBar.actualizar(juego);

    }

    private void aplicarSombra() {

    }

    public void mostrarBarraComercio() {
        barraComercio.setVisible(true);
        barraComercio.setManaged(true);
        barraComercio.toFront();
    }

    public void ocultarBarraComercio() {
        barraComercio.setVisible(false);
        barraComercio.setManaged(false);
    }

}
