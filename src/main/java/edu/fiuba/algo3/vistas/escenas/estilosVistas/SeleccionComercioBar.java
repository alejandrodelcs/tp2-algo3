package edu.fiuba.algo3.vistas.escenas.estilosVistas;

import edu.fiuba.algo3.controllers.ControladorJuego;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.vistas.TableroView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

/**
 * SeleccionComercioBar
 */
public class SeleccionComercioBar extends VBox {

    private ControladorJuego controlador;

    public SeleccionComercioBar(ControladorJuego controlador) {
        this.controlador = controlador;

        configurarEstiloBase();
        refrescarContenido();

    }

    public void actualizar() {
        this.refrescarContenido();
    }

    private void refrescarContenido() {
        BotonesVista btnInterno = new BotonesVista("Interno");
        btnInterno.setOnAction(e -> controlador.abrirComercioInterno(1));

        BotonesVista btnBanca = new BotonesVista("Banca");
        btnBanca.setOnAction(e -> controlador.abrirComercioInterno(4));

        this.getChildren().addAll(btnInterno, btnBanca);

    }

    private void configurarEstiloBase() {

        this.setPrefWidth(300);
        this.setSpacing(20);
        this.setAlignment(Pos.CENTER_RIGHT);
        this.setPadding(new Insets(40, 20, 40, 20));
        this.setStyle("-fx-background-color: #4d3a35;");
    }
}
