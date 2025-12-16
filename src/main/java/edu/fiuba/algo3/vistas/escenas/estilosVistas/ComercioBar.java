package edu.fiuba.algo3.vistas.escenas.estilosVistas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import edu.fiuba.algo3.controllers.ControladorJuego;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * ComercioBar
 */
public class ComercioBar extends VBox {

    private ControladorJuego controlador;
    private List<ComercioRecursoBox> cajasRecurso = new ArrayList<>();

    public ComercioBar(ControladorJuego controlador) {

        this.controlador = controlador;

        this.getStylesheets().add(
                getClass().getResource("/styles/estilos.css").toExternalForm());
        getStyleClass().add("principal-bar");
        configurarEstiloBase();
        refrescarContenido();
    }

    public void actualizar() {
        this.refrescarContenido();
    }

    private void refrescarContenido() {
        this.getChildren().clear();
        cajasRecurso.clear();

        this.getChildren().add(crearEncabezadoColumnas());

        List<Recurso> recursos = controlador.getTerrenos();

        for (Recurso recurso : recursos) {
            ComercioRecursoBox box = new ComercioRecursoBox(recurso);

            this.cajasRecurso.add(box);
            this.getChildren().add(box);

        }

        ToggleButton btnAceptarOferta = crearBotonAceptar();
        ToggleButton btnAceptarDemanda = crearBotonAceptar();

        BotonesVista botonSalir = new BotonesVista("Volver");
        botonSalir.setOnAction(e -> controlador.cerrarComercioInterno());

        btnAceptarOferta.setOnAction(e -> {
            controlador.armarPaqueteOferta(this.construirPaqueteOferta());
        });
        btnAceptarDemanda.setOnAction(e -> {
            controlador.armarPaqueteDemanda(this.construirPaqueteDemanda());
        });

        BotonesVista botonEjecutar = new BotonesVista("Ejecutar");
        botonEjecutar.setMaxWidth(Double.MAX_VALUE);
        botonEjecutar.setDisable(true);
        botonEjecutar.setOnAction(e -> controlador.confirmarComercio());

        bindearEjecucion(btnAceptarOferta, btnAceptarDemanda, botonEjecutar);

        HBox cajaAceptar = new HBox(15);
        cajaAceptar.setAlignment(Pos.CENTER);
        cajaAceptar.getChildren().addAll(btnAceptarOferta, btnAceptarDemanda);

        this.getChildren().addAll(cajaAceptar, botonEjecutar, botonSalir);

    }

    private void bindearEjecucion(
            ToggleButton aceptarOferta,
            ToggleButton aceptarDemanda,
            Button ejecutar) {

        ejecutar.disableProperty().bind(
                aceptarOferta.selectedProperty().not()
                        .or(aceptarDemanda.selectedProperty().not()));
    }

    private ToggleButton crearBotonAceptar() {
        ToggleButton boton = new ToggleButton("✔");

        boton.setPrefSize(56, 56);

        boton.getStyleClass().add("boton-aceptar");
        boton.selectedProperty().addListener((obs, oldVal, seleccionado) -> {
            boton.setStyle(
                    (seleccionado ? "#6fcf97" : "#dddddd"));
        });

        return boton;
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

    private HBox crearEncabezadoColumnas() {
        Label lblYo = new Label("Ofrezco");
        Label lblOtro = new Label("Recibo");

        Region separador = new Region();
        HBox.setHgrow(separador, Priority.ALWAYS);

        HBox encabezado = new HBox(20, lblYo, separador, lblOtro);
        encabezado.getStyleClass().add("encabezado-comercio");

        return encabezado;
    }

}
