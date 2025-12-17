package edu.fiuba.algo3.vistas.escenas.estilosVistas;

import edu.fiuba.algo3.controllers.ControladorJuego;
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
        BotonesVista btnMostrarManoCartas = new BotonesVista("Ver cartas");
        btnMostrarManoCartas.setOnAction(e -> controlador.mostrarManoCartas());

        BotonesVista btnOcultar = new BotonesVista("volver");
        btnOcultar.setOnAction(e -> controlador.cerrarManoCartas());

        this.getChildren().addAll(btnMostrarManoCartas, btnOcultar);

    }

    public void configurarEstilos() {

    }

}
