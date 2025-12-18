package edu.fiuba.algo3.vistas.escenas.estilosVistas;

import edu.fiuba.algo3.controllers.ControladorJuego;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * SeleccionMazoBar
 */
public class SeleccionMazoBar extends HBox {
    ControladorJuego controlador;

    public SeleccionMazoBar(ControladorJuego controlador) {

        this.getStylesheets().add(
                getClass().getResource("/styles/estilos.css").toExternalForm());

        this.controlador = controlador;

        configurarEstilos();
        refrescarContenido();
    }

    public void refrescarContenido() {
        GridPane contendeor = new GridPane();
        BotonesVista btnMostrarManoCartas = new BotonesVista("Ver cartas");
        btnMostrarManoCartas.setOnAction(e -> controlador.mostrarManoCartas());

        BotonesVista btnOcultar = new BotonesVista("volver");
        btnOcultar.setOnAction(e -> controlador.cerrarManoCartas());

        BotonesVista btnComprarMazo = new BotonesVista("comprar");
        btnComprarMazo.setOnAction(e -> controlador.comprarCartaAMazo());

        contendeor.add(btnMostrarManoCartas, 0, 0);
        contendeor.add(btnOcultar, 1, 0);
        contendeor.add(btnComprarMazo, 0, 1, 2, 1);

        this.getChildren().add(contendeor);

    }

    public void configurarEstilos() {

    }

}
