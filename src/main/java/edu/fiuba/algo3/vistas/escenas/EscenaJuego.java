package edu.fiuba.algo3.vistas.escenas;

import javafx.scene.effect.DropShadow;
import edu.fiuba.algo3.vistas.TableroView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class EscenaJuego extends EscenaGeneral {
    public EscenaJuego(Stage stage) {
        super(stage);
    }

    @Override
    protected Pane crearLayout(Stage stage) {
        StackPane root = new StackPane();

        // ImageView fondoView = new ImageView(new
        // Image(getClass().getResourceAsStream("/images/marProfundo.png")));

        // fondoView.fitWidthProperty().bind(stage.widthProperty());
        // fondoView.fitHeightProperty().bind(stage.heightProperty());

        TableroView tableroView = new TableroView();
        tableroView.setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

        DropShadow sombra = new DropShadow();
        sombra.setRadius(40);
        sombra.setOffsetY(20);
        sombra.setColor(Color.rgb(0, 0, 0, 0.6));

        tableroView.setEffect(sombra);

        root.getChildren().addAll(tableroView);

        return root;
    }

    @Override
    protected void crearControladores(Stage stage) {
    }

    @Override
    protected void generarEstilos() {
    }
}
