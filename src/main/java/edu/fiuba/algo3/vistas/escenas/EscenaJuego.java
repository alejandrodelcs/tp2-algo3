package edu.fiuba.algo3.vistas.escenas;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.ElementosDeJuego.Juego;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import edu.fiuba.algo3.vistas.TableroView;
import edu.fiuba.algo3.vistas.escenas.estilosVistas.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class EscenaJuego extends EscenaGeneral {
    public EscenaJuego(Stage stage) {
        super(stage);
    }

    @Override
    protected Pane crearLayout(Stage stage) {
        HBox root = new HBox();
        Juego juego = new Juego(new Jugador("hola", new Inventario()));// ver como setear jugadores
        Tablero tablero = new Tablero();
        tablero.construir();

        TableroView tableroView = new TableroView(tablero);
        tableroView.setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

        JugadoresBar jugadroesBar = new JugadoresBar(juego);
        CartasBar cartasBar = new CartasBar(juego);

        DropShadow sombra = new DropShadow();
        sombra.setRadius(40);
        sombra.setOffsetY(20);
        sombra.setColor(Color.rgb(0, 0, 0, 0.6));

        tableroView.setEffect(sombra);

        StackPane tableroContainer = new StackPane(tableroView);
        tableroContainer.setAlignment(Pos.CENTER);

        HBox contendorTablero = new HBox(tableroContainer);
        HBox.setHgrow(contendorTablero, Priority.ALWAYS);

        root.getChildren().addAll(jugadroesBar, contendorTablero, cartasBar);

        return root;
    }

    @Override
    protected void crearControladores(Stage stage) {
    }

    @Override
    protected void generarEstilos() {
    }
}
