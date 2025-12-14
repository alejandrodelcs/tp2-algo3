package edu.fiuba.algo3.vistas.escenas.estilosVistas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import edu.fiuba.algo3.controllers.ControladorJuego;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.vistas.TableroView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * ComercioBar
 */
public class ComercioBar extends VBox {

    private ControladorJuego controlador;
    private TableroView tableroView;
    private Juego juego;
    private List<ComercioRecursoBox> cajasRecurso = new ArrayList<>();

    public ComercioBar(Juego juego, ControladorJuego controlador, TableroView tablero) {

        this.juego = juego;
        this.controlador = controlador;
        this.tableroView = tablero;

        configurarEstiloBase();
        refrescarContenido();
    }

    private void refrescarContenido() {
        this.getChildren().clear();
        cajasRecurso.clear();

        List<Recurso> recursos = juego.getTerrenos();

        for (Recurso recurso : recursos) {
            ComercioRecursoBox box = new ComercioRecursoBox(recurso);
            box.setStyle(
                    "-fx-background-color: #6d524c;" +
                            "-fx-background-radius: 12;" +
                            "-fx-border-radius: 12;" +
                            "-fx-padding: 10;");

            this.cajasRecurso.add(box);
            this.getChildren().add(box);

        }

        BotonesVista btnAceptar1 = new BotonesVista("btnAceptar1");
        BotonesVista btnAceptar2 = new BotonesVista("btnAceptar2");
        BotonesVista botonSalir = new BotonesVista("Volver");
        botonSalir.setOnAction(e -> controlador.cerrarComercio());
        btnAceptar1.setOnAction(e -> controlador.armarPaqueteOferta(this.construirPaqueteOferta()));
        btnAceptar2.setOnAction(e -> controlador.armarPaqueteDemanda(this.construirPaqueteDemanda()));

        BotonesVista botonEjecutar = new BotonesVista("Ejecutar");
        botonEjecutar.setOnAction(e -> controlador.confirmarComercio());

        botonSalir.setMaxWidth(Double.MAX_VALUE);

        this.getChildren().addAll(btnAceptar1, btnAceptar2, botonSalir, botonEjecutar);

    }

    private Map<Class<? extends Recurso>, Integer> construirPaqueteOferta() {
        Map<Class<? extends Recurso>, Integer> paquete = new HashMap<>();

        for (ComercioRecursoBox box : cajasRecurso) {
            int cantidad = box.getCantidadOfrecida();
            if (cantidad > 0) {
                paquete.put(box.getTipoRecurso(), cantidad);
            }
        }
        return paquete;

    }

    private Map<Class<? extends Recurso>, Integer> construirPaqueteDemanda() {
        Map<Class<? extends Recurso>, Integer> paquete = new HashMap<>();

        for (ComercioRecursoBox box : cajasRecurso) {
            int cantidad = box.getCantidadPedida();
            if (cantidad > 0) {
                paquete.put(box.getTipoRecurso(), cantidad);
            }
        }
        return paquete;
    }

    private void configurarEstiloBase() {

        this.setPrefWidth(300);
        this.setSpacing(20);
        this.setAlignment(Pos.CENTER_RIGHT);
        this.setPadding(new Insets(40, 20, 40, 20));
        this.setStyle("-fx-background-color: #4d3a35;");
    }

}
