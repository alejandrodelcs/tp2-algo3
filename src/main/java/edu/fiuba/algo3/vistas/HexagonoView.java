package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.ElementosTablero.Hexagono;
import javafx.geometry.Pos;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.Group;

import java.net.URL;

public class HexagonoView extends StackPane {

    private static final double ANCHO_RADIO = Math.sqrt(3);

    public HexagonoView(double radio, Hexagono hexagonoModelo) {

        double anchoHex = ANCHO_RADIO * radio;
        double altoHex = 2 * radio;

        this.setMinSize(anchoHex, altoHex);
        this.setMaxSize(anchoHex, altoHex);
        this.setPrefSize(anchoHex, altoHex);

        String tipoTerreno = hexagonoModelo.getTerreno().toString();
        int numeroToken = (hexagonoModelo.getFicha() != 0) ? hexagonoModelo.getFicha() : 0;

        Double[] puntos = new Double[]{
                anchoHex / 2, 0.0,
                anchoHex, altoHex / 4,
                anchoHex, altoHex * 0.75,
                anchoHex / 2, altoHex,
                0.0, altoHex * 0.75,
                0.0, altoHex / 4
        };

        StackPane containerImagen = new StackPane();
        containerImagen.setMaxSize(anchoHex, altoHex);

        String rutaImagen = obtenerRutaImagen(tipoTerreno);

        try {
            URL imgUrl = getClass().getResource(rutaImagen);
            if (imgUrl != null) {
                Image imgOriginal = new Image(imgUrl.toExternalForm());

                Image imgEnderezada = preRotarImagen(imgOriginal, 90);

                ImageView imagenView = new ImageView(imgEnderezada);
                imagenView.setPreserveRatio(true);

                double scaleWidth = anchoHex / imgEnderezada.getWidth();
                double scaleHeight = altoHex / imgEnderezada.getHeight();
                double scale = Math.max(scaleWidth, scaleHeight);

                double factorZoom = 0.9;

                imagenView.setFitWidth(imgEnderezada.getWidth() * scale * factorZoom);
                imagenView.setFitHeight(imgEnderezada.getHeight() * scale * factorZoom);

                imagenView.setTranslateX(0);
                imagenView.setTranslateY(0);



                StackPane.setAlignment(imagenView, Pos.CENTER);
                containerImagen.getChildren().add(imagenView);

                Polygon clip = new Polygon();
                clip.getPoints().addAll(puntos);
                containerImagen.setClip(clip);
            }
        } catch (Exception e) {
            System.err.println("Error imagen: " + tipoTerreno);
        }

        Polygon borde = new Polygon();
        borde.getPoints().addAll(puntos);
        borde.setStroke(Color.BLACK);
        borde.setStrokeWidth(3);
        borde.setStrokeType(StrokeType.INSIDE);
        borde.setFill(Color.TRANSPARENT);
        borde.setMouseTransparent(true);

        StackPane tokenView = new StackPane();
        if (numeroToken > 0) {
            Circle circulo = new Circle(radio * 0.35);
            circulo.setFill(Color.BEIGE);
            circulo.setStroke(Color.BLACK);
            Text texto = new Text(String.valueOf(numeroToken));
            texto.setFont(Font.font("Arial", FontWeight.BOLD, radio * 0.3));
            texto.setFill((numeroToken == 6 || numeroToken == 8) ? Color.RED : Color.BLACK);
            tokenView.getChildren().addAll(circulo, texto);
        }
        tokenView.setMouseTransparent(true);

        this.getChildren().addAll(containerImagen, borde, tokenView);

        this.setOnMouseEntered(e -> this.setEffect(new DropShadow(10, Color.GOLD)));
        this.setOnMouseExited(e -> this.setEffect(null));
        this.setPickOnBounds(false);
    }

    private Image preRotarImagen(Image imagenOriginal, double grados) {
        ImageView tempView = new ImageView(imagenOriginal);
        tempView.setRotate(grados);

        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        return tempView.snapshot(params, null);
    }

    private String obtenerRutaImagen(String terreno) {
        switch (terreno.toUpperCase()) {
            case "BOSQUE": return "/images/bosque.png";
            case "COLINA": return "/images/colina.png";
            case "PASTIZAL": return "/images/pastizal.png";
            case "CAMPO": case "TRIGO": return "/images/campo.png";
            case "MONTANA": return "/images/montaña.png";
            case "DESIERTO": return "/images/desierto.png";
            default: return "/images/mar.png";
        }
    }
}