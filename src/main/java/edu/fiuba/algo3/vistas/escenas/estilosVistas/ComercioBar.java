package edu.fiuba.algo3.vistas.escenas.estilosVistas;

import java.util.List;
import edu.fiuba.algo3.controllers.ControladorJuego;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.vistas.TableroView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

/**
 * ComercioBar
 */
public class ComercioBar extends VBox {

    private ControladorJuego controlador;
    private TableroView tableroView;
    private Juego juego;

    public ComercioBar(Juego juego, ControladorJuego controlador, TableroView tablero) {

        this.juego = juego;
        this.controlador = controlador;
        this.tableroView = tablero;

        configurarEstiloBase();
        refrescarContenido();
    }

    private void refrescarContenido() {
        this.getChildren().clear();

        List<Recurso> recursos = juego.getTerrenos();

        for (Recurso recurso : recursos) {
            ComercioRecursoBox box = new ComercioRecursoBox(recurso);
            box.setStyle(
                    "-fx-background-color: #6d524c;" +
                            "-fx-background-radius: 12;" +
                            "-fx-border-radius: 12;" +
                            "-fx-padding: 10;");
            this.getChildren().add(box);

        }

        BotonesVista botonSalir = new BotonesVista("Volver");
        botonSalir.setOnAction(e -> controlador.cerrarComercio());

        botonSalir.setMaxWidth(Double.MAX_VALUE);

        this.getChildren().addAll(botonSalir);

    }

    private void configurarEstiloBase() {

        this.setPrefWidth(300);
        this.setSpacing(20);
        this.setAlignment(Pos.CENTER_RIGHT);
        this.setPadding(new Insets(40, 20, 40, 20));
        this.setStyle("-fx-background-color: #4d3a35;");
    }

}
