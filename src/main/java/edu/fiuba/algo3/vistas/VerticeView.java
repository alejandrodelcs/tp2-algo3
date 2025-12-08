package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Tablero.Vertice;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class VerticeView extends StackPane {
    private Vertice verticeModelo;
    private Circle circulo;

    public VerticeView(Vertice vertice, double radioHexagono) {
        this.verticeModelo = vertice;

        double radioVertice = radioHexagono * 0.25;

        this.circulo = new Circle(radioVertice);
        this.circulo.setFill(Color.TRANSPARENT);
        this.circulo.setStroke(Color.RED);
        this.circulo.setStrokeWidth(4);

        this.setOnMouseClicked(e -> {
            System.out.println("¡Click en un Vértice!");
            // verticeModelo.construir();

            circulo.setFill(Color.BLUE);
        });

        this.setOnMouseEntered(e -> circulo.setStroke(Color.BLUE));
        this.setOnMouseExited(e -> circulo.setStroke(Color.RED));

        this.getChildren().add(circulo);

        this.setPickOnBounds(false);
    }
}
