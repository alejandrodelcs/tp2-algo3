package edu.fiuba.algo3.vistas.escenas;

import edu.fiuba.algo3.controllers.ControladorJuego;
import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import edu.fiuba.algo3.vistas.TableroView;
import edu.fiuba.algo3.vistas.escenas.estilosVistas.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class EscenaJuego extends EscenaGeneral {

    private final ControladorJuego controlador;
    private TableroView tableroView;
    private JugadoresBar jugadoresBar;
    private CartasBar cartasBar;
    private BotonTurnoDado botonTunroDado;
    private CajaJugador cajaJugador;

    public EscenaJuego(Stage stage, Juego juego) {
        super(stage, juego);
        this.controlador = new ControladorJuego(juego, this);
    }

    @Override
    protected Pane crearLayout(Stage stage) {
        GridPane root = new GridPane();

        Tablero tablero = juego.getTablero();

        this.tableroView = new TableroView(tablero); // OJO ARREGLAR
        this.tableroView.setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

        this.aplicarSombra();// agregar algun efecto

        StackPane tableroContainer = new StackPane(this.tableroView);
        tableroContainer.setAlignment(Pos.CENTER);

        this.jugadoresBar = new JugadoresBar(juego);
        HBox.setHgrow(this.jugadoresBar, Priority.ALWAYS);

        this.cartasBar = new CartasBar(juego);

        this.botonTunroDado = new BotonTurnoDado();

        this.cajaJugador = new CajaJugador();

        root.add(this.jugadoresBar, 0, 0);
        root.add(tableroContainer, 1, 0);

        root.add(this.cajaJugador, 0, 1);
        root.add(this.cartasBar, 1, 1);
        root.add(this.botonTunroDado, 2, 1);

        ColumnConstraints colIzq = new ColumnConstraints();
        colIzq.setMinWidth(200);

        ColumnConstraints colCentro = new ColumnConstraints();
        colCentro.setHgrow(Priority.ALWAYS);

        ColumnConstraints colDer = new ColumnConstraints();
        colDer.setMinWidth(200);

        root.getColumnConstraints().addAll(colIzq, colCentro, colDer);

        RowConstraints filaSuperior = new RowConstraints();
        filaSuperior.setVgrow(Priority.ALWAYS);

        RowConstraints filaInferior = new RowConstraints();
        filaInferior.setVgrow(Priority.ALWAYS);

        root.getRowConstraints().addAll(filaSuperior, filaInferior);

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
        cartasBar.actualizar(juego);
        jugadoresBar.actualizar(juego);
        tableroView.actualizar(juego.getTablero());
    }

    private void aplicarSombra() {

    }

}
