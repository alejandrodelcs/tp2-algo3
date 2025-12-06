package edu.fiuba.algo3.vistas.escenas.estilosVistas;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * TituloVista
 */
public class TituloVista extends HBox {
    public TituloVista(String titulo) {
        char[] letras = titulo.toCharArray();

        for (int i = 0; i < letras.length; i++) {

            char letra = letras[i];
            Text label = new Text(Character.toString(letra));

            label.setStroke(Color.BLACK);
            label.setFill(Color.DARKKHAKI);
            label.setStrokeWidth(2);
            label.setStyle("-fx-font-size: 150;");

            label.setFill(Color.web("#F4E4C1"));
            label.setStroke(Color.web("#6B4423"));
            label.setStrokeWidth(3);

            DropShadow dropShadow = new DropShadow();
            dropShadow.setColor(Color.web("#3D2817"));
            dropShadow.setRadius(15);
            dropShadow.setOffsetY(3);
            getChildren().add(label);

        }

        setAlignment(Pos.CENTER);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BROWN);
        dropShadow.setRadius(10);
        setEffect(dropShadow);
    }

}
