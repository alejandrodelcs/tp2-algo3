package edu.fiuba.algo3.vistas.escenas;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import edu.fiuba.algo3.vistas.TableroView;
import edu.fiuba.algo3.vistas.escenas.estilosVistas.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class EscenaJuego extends EscenaGeneral {

    public EscenaJuego(Stage stage, Juego juego) {
        super(stage, juego);

    }

    @Override
    protected Pane crearLayout(Stage stage) {
        GridPane root = new GridPane();

        Tablero tablero = juego.getTablero();

        TableroView tableroView = new TableroView(tablero); // OJO ARREGLAR
        tableroView.setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

        DropShadow sombra = new DropShadow();
        sombra.setRadius(40);
        sombra.setOffsetY(20);
        sombra.setColor(Color.rgb(0, 0, 0, 0.6));
        tableroView.setEffect(sombra);

        StackPane tableroContainer = new StackPane(tableroView);
        tableroContainer.setAlignment(Pos.CENTER);

        JugadoresBar jugadroesBar = new JugadoresBar(juego);
        HBox.setHgrow(jugadroesBar, Priority.ALWAYS);

        CartasBar cartasBar = new CartasBar(juego);

        root.add(jugadroesBar, 0, 0);
        root.add(tableroContainer, 1, 0);
        root.add(cartasBar, 0, 1, 2, 1);

        ColumnConstraints colIzq = new ColumnConstraints();
        colIzq.setMinWidth(200);

        ColumnConstraints colCentro = new ColumnConstraints();
        colCentro.setHgrow(Priority.ALWAYS);

        root.getColumnConstraints().addAll(colIzq, colCentro);

        RowConstraints filaSuperior = new RowConstraints();
        filaSuperior.setVgrow(Priority.ALWAYS);

        RowConstraints filaInferior = new RowConstraints();
        filaInferior.setVgrow(Priority.ALWAYS);

        root.getRowConstraints().addAll(filaSuperior, filaInferior);

        return root;
    }

    @Override
    protected void crearControladores(Stage stage) {
    }

    @Override
    protected void generarEstilos() {
    }

}
