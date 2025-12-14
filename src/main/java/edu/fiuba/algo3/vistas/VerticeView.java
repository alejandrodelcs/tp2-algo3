package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ControladorJuego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Vertice;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.function.Consumer;

public class VerticeView extends StackPane {
    private final Vertice verticeModelo;
    private final Circle circulo;
    private ImageView imagenConstruccion;
    private ControladorJuego controlador;

    public VerticeView(Vertice vertice, double radioHexagono, Consumer<VerticeView> onSeleccionado,
            ControladorJuego controlador) {
        this.controlador = controlador;
        this.verticeModelo = vertice;

        this.setMaxSize(0, 0);
        this.setAlignment(Pos.CENTER);

        this.setPickOnBounds(false);

        double altoDeLaImagen = radioHexagono * 2;
        double tamanoVisual = radioHexagono * 0.4;

        this.circulo = new Circle((tamanoVisual / 2) * 1.2);
        this.circulo.setFill(Color.TRANSPARENT);
        this.circulo.setStroke(Color.TRANSPARENT);
        this.circulo.setStrokeWidth(4);
        this.circulo.setMouseTransparent(false);

        this.imagenConstruccion = new ImageView();
        this.imagenConstruccion.setFitHeight(altoDeLaImagen);
        this.imagenConstruccion.setPreserveRatio(true);
        this.imagenConstruccion.setMouseTransparent(true);

        this.imagenConstruccion.setManaged(false);

        this.imagenConstruccion.setTranslateY(-altoDeLaImagen * 0.4);
        this.imagenConstruccion.setTranslateX(-35);

        this.getChildren().addAll(circulo, imagenConstruccion);

        this.setOnMouseClicked(e -> onSeleccionado.accept(this));

        actualizarVisualizacion();
    }

    public void actualizarVisualizacion() {

        if (!verticeModelo.tieneConstruccion()) {
            imagenConstruccion.setImage(null);
            return;
        }

        Jugador propietario = verticeModelo.getPropietario();
        String color = this.controlador.getColor(propietario).toLowerCase();
        String nombre = verticeModelo.getConstruccion().getNombre().toLowerCase();

        String ruta = "/images/" + nombre + "_" + color + ".png";

        var inputStream = getClass().getResourceAsStream(ruta);

        Image imgConstruccion = new Image(inputStream);

        imagenConstruccion.setImage(imgConstruccion);

    }

    public void seleccionar() {
        circulo.setFill(Color.GREEN);
        circulo.setStroke(Color.WHITE);
    }

    public void deseleccionar() {
        circulo.setFill(Color.TRANSPARENT);
        circulo.setStroke(Color.TRANSPARENT);
    }

    public Vertice getVerticeModelo() {
        return verticeModelo;
    }
}
