package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Tablero.Vertice;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.function.Consumer;

public class VerticeView extends StackPane {
    private final Vertice verticeModelo;
    private final Circle circulo;

    public VerticeView(Vertice vertice, double radioHexagono, Consumer<VerticeView> onSeleccionado) {
        this.verticeModelo = vertice;

        double radioVertice = radioHexagono * 0.2;

        this.circulo = new Circle(radioVertice);
        this.circulo.setFill(Color.TRANSPARENT);
        // this.circulo.setStroke(Color.RED);
        this.circulo.setStrokeWidth(3);

        this.getChildren().add(circulo);

        this.setOnMouseClicked(e -> {
            onSeleccionado.accept(this);
            // verticeModelo.contruir()
        });

        this.setOnMouseEntered(e -> circulo.setStrokeWidth(5));
        this.setOnMouseExited(e -> circulo.setStrokeWidth(3));

        this.setPickOnBounds(false);
    }

    public void seleccionar() {
        circulo.setFill(Color.GREEN);
        circulo.setStroke(Color.WHITE);
    }

    public void deseleccionar() {
        circulo.setFill(Color.TRANSPARENT);
        circulo.setStroke(Color.RED);
    }

    public Vertice getVerticeModelo() {
        return verticeModelo;
    }
}
