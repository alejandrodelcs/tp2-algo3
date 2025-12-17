package edu.fiuba.algo3.vistas.escenas;

import edu.fiuba.algo3.controllers.ControladorJuego;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Tablero.*;
import javafx.geometry.Pos;
import edu.fiuba.algo3.vistas.TableroView;
import edu.fiuba.algo3.vistas.escenas.estilosVistas.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class EscenaJuego extends EscenaGeneral {

    private TableroView tableroView;
    private JugadoresBar jugadoresBar;
    private CartasBar cartasBarRecursos;
    private DesarrolloBar cartasBarDesarrollo;
    private Tablero tablero;
    private DadoBar dadoBar;
    private StackPane panelDerecho, panelInferior;
    private ComercioBar barraComercio;
    private SeleccionComercioBar barraSeleccionComercio;
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
        TableroView tableroView = new TableroView(tablero, controlador);
        this.tableroView = tableroView;
        tableroView.setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

        this.aplicarSombra();// agregar algun efecto

        StackPane tableroContainer = new StackPane(this.tableroView);
        tableroContainer.setAlignment(Pos.CENTER);

        this.panelAcciones = new AccionesBar(this.juego, controlador, this.tableroView);

        this.barraSeleccionComercio = new SeleccionComercioBar(controlador);
        this.barraComercio = new ComercioBar(controlador);

        barraSeleccionComercio.setVisible(false);
        barraSeleccionComercio.setManaged(false);
        barraComercio.setVisible(false);
        barraComercio.setManaged(false);

        this.panelDerecho = new StackPane(panelAcciones, barraComercio, barraSeleccionComercio);
        this.panelDerecho.setAlignment(Pos.TOP_CENTER);

        this.cartasBarRecursos = new CartasBar(controlador);
        this.cartasBarDesarrollo = new DesarrolloBar(controlador);

        HBox seleccionMazo = new SeleccionMazoBar(controlador);

        cartasBarDesarrollo.setVisible(false);
        cartasBarDesarrollo.setManaged(false);

        this.panelInferior = new StackPane(cartasBarRecursos, cartasBarDesarrollo);
        this.panelInferior.setAlignment(Pos.BOTTOM_CENTER);

        this.dadoBar = new DadoBar(juego);

        gridLayout.add(jugadoresBar, 0, 0);
        gridLayout.add(tableroContainer, 1, 0);
        gridLayout.add(panelDerecho, 2, 0);
        gridLayout.add(seleccionMazo, 0, 1);
        gridLayout.add(panelInferior, 1, 1, 1, 1);
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

    @Override
    protected void crearControladores(Stage stage) {
        // artasBar.getBotonTirarDado().setOnAction(e -> controlador.tirarDado());
        // jugadoresBar.getBotonPasarTurno().setOnAction(e -> controlador.pasarTurno());
    }

    @Override
    protected void generarEstilos() {

    }

    public void actualizarVista() {
        cartasBarRecursos.actualizar();
        jugadoresBar.actualizar(juego);
        tableroView.actualizar(juego.getTablero());
        barraComercio.actualizar();
        this.dadoBar.actualizar(juego);

    }

    private void aplicarSombra() {

    }

    public void mostrarBarraCartasMano() {
        this.cartasBarDesarrollo.setVisible(true);
        this.cartasBarDesarrollo.setManaged(true);
        this.cartasBarDesarrollo.toFront();
    }

    public void mostrarBarraSeleccionComercio() {

        barraSeleccionComercio.setVisible(true);
        barraSeleccionComercio.setManaged(true);
        barraSeleccionComercio.toFront();
    }

    public void mostrarBarraComercioInterno() {
        barraComercio.setVisible(true);
        barraComercio.setManaged(true);
        barraComercio.toFront();
    }

    public void ocultarBarraSeleccionComercio() {
        barraSeleccionComercio.setVisible(false);
        barraSeleccionComercio.setManaged(false);
    }

    public TableroView getTablero() {
        return tableroView;
    }

    public void ocultarBarraComercio() {
        barraComercio.setVisible(false);
        barraComercio.setManaged(false);
    }

    public void ocultarManoCartas() {
        cartasBarDesarrollo.setVisible(false);
        cartasBarDesarrollo.setManaged(false);
    }

}
