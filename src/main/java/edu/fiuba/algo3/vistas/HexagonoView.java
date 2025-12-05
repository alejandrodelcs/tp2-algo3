package edu.fiuba.algo3.vistas;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.FontWeight;

import javafx.scene.text.Text;
import javafx.scene.text.Font;

import java.awt.*;
import java.net.URL;

public class HexagonoView extends StackPane {
    private static final double ANCHO_RADIO = Math.sqrt(3);

    public HexagonoView(double radio, String rutaImagen, String numeroToken) {
        double anchoHex = ANCHO_RADIO * radio;
        double altoHex = 2 * radio;

        Double[] puntos = new Double[] {
                anchoHex / 2, 0.0,
                anchoHex, altoHex / 4,
                anchoHex, altoHex * 0.75,
                anchoHex / 2, altoHex,
                0.0, altoHex * 0.75,
                0.0, altoHex / 4
        };

        Polygon bordeHexagono = new Polygon();
        bordeHexagono.getPoints().addAll(puntos);
        bordeHexagono.setStroke(Color.BLACK);
        bordeHexagono.setStrokeWidth(3);
        bordeHexagono.setStrokeType(StrokeType.INSIDE);
        bordeHexagono.setFill(Color.TRANSPARENT);

        StackPane containerImagen = new StackPane();
        containerImagen.setMaxSize(anchoHex, altoHex);
        containerImagen.setMinSize(anchoHex, altoHex);

        try {
            URL imgUrl = getClass().getResource(rutaImagen);
            if (imgUrl != null) {
                Image img = new Image(imgUrl.toExternalForm());
                ImageView imagenView = new ImageView(img);
                imagenView.setPreserveRatio(true);

                double scaleWidth = anchoHex / img.getWidth();
                double scaleHeight = altoHex / img.getHeight();
                double scale = Math.max(scaleWidth, scaleHeight);

                double factorSeguridad = 1.3;

                imagenView.setFitWidth(img.getWidth() * scale * factorSeguridad);
                imagenView.setFitHeight(img.getHeight() * scale * factorSeguridad);

                StackPane.setAlignment(imagenView, Pos.CENTER);
                containerImagen.getChildren().add(imagenView);

                Polygon clip = new Polygon();
                clip.getPoints().addAll(puntos);
                containerImagen.setClip(clip);

            } else {
                bordeHexagono.setFill(Color.LIGHTGRAY);
            }

        } catch (Exception e) {
            bordeHexagono.setFill(Color.RED);
            System.err.println("Error cargando imagen hexagono: " + rutaImagen);
        }

        StackPane token = new StackPane();
        if (numeroToken != null && !numeroToken.isEmpty()) {
            javafx.scene.shape.Circle circulo = new javafx.scene.shape.Circle(radio * 0.3, Color.BEIGE);
            circulo.setStroke(Color.BLACK);
            circulo.setStrokeWidth(1);

            Text texto = new Text(numeroToken);
            texto.setFont(Font.font("Arial", FontWeight.BOLD, radio * 0.3));
            if (numeroToken.equals("6") || numeroToken.equals("8"))
                texto.setFill(Color.RED);

            token.getChildren().addAll(circulo, texto);
        }

        this.getChildren().addAll(containerImagen, bordeHexagono, token);

        this.setMaxSize(anchoHex, altoHex);
        this.setOnMouseEntered(e -> this.setEffect(new DropShadow(15, Color.GOLD)));
        this.setOnMouseExited(e -> this.setEffect(null));
    }
}
