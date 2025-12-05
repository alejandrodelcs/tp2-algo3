package edu.fiuba.algo3.vistas.escenas;

import edu.fiuba.algo3.vistas.TableroView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class EscenaJuego extends EscenaGeneral{
    public EscenaJuego(Stage stage) {
        super(stage);
    }

    @Override
    protected Pane crearLayout(Stage stage) {
        StackPane root = new StackPane();

        ImageView fondoView = new ImageView(new Image(getClass().getResourceAsStream("/images/fondo_mar.png")));

        fondoView.fitWidthProperty().bind(stage.widthProperty());
        fondoView.fitHeightProperty().bind(stage.heightProperty());

        TableroView tableroView = new TableroView();
        tableroView.setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

        root.getChildren().addAll(fondoView, tableroView);

        return root;
    }

    @Override
    protected void crearControladores(Stage stage) {
    }

    @Override
    protected void generarEstilos() {
    }
}
