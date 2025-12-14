package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ControladorJuego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Arista;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



import java.util.Optional;
import java.util.function.Consumer;

public class AristaView extends StackPane {

    private final Arista aristaModelo;
    private final Rectangle forma;
    private boolean seleccionada = false;
    private ControladorJuego controladorJuego;

    public AristaView(Arista arista, double largoHexagono, Consumer<AristaView> onSeleccionado, ControladorJuego controladorJuego) {
        this.aristaModelo = arista;
        this.controladorJuego = controladorJuego;

        this.setMaxSize(0, 0);
        this.setAlignment(Pos.CENTER);

        this.setPickOnBounds(false);

        double grosor = 6;
        double largo = largoHexagono * 0.8;

        this.forma = new Rectangle(largo, grosor);

        this.forma.setArcWidth(4);
        this.forma.setArcHeight(4);

        this.getChildren().add(forma);

        this.forma.setOnMouseClicked(e -> {
            onSeleccionado.accept(this);
            e.consume();
        });

        this.forma.setOnMouseEntered(e -> {
            if (!aristaModelo.getPropietario().isPresent() && !seleccionada) {
                forma.setStroke(Color.LIGHTBLUE);
                forma.setStrokeWidth(2);
            }
        });

        this.forma.setOnMouseExited(e -> {
            if (!aristaModelo.getPropietario().isPresent() && !seleccionada) {
                forma.setStroke(Color.LIGHTGRAY);
                forma.setStrokeWidth(1);
            }
        });

        actualizarVisualizacion();
    }

    public void actualizarVisualizacion() {
        Optional<Jugador> propietarioOpt = aristaModelo.getPropietario();

        if (propietarioOpt.isPresent()) {
            Jugador jugador = propietarioOpt.get();

            String color = controladorJuego.getColor(jugador);
            Color colorJugador = mapaDeColores(controladorJuego.getColor(jugador));

            forma.setFill(colorJugador);
            forma.setStroke(Color.BLACK);
            forma.setStrokeWidth(1);
            forma.setOpacity(1.0);

        } else {
            forma.setFill(Color.TRANSPARENT);

            forma.setStroke(Color.LIGHTGRAY);
            forma.setStrokeWidth(1);

            if (seleccionada) {
                forma.setStroke(Color.YELLOW);
                forma.setStrokeWidth(3);
            }
        }
    }

    public void seleccionar() {
        this.seleccionada = true;
        actualizarVisualizacion();
    }

    public void deseleccionar() {
        this.seleccionada = false;
        actualizarVisualizacion();
    }

    public Arista getAristaModelo() {
        return aristaModelo;
    }

    private Color mapaDeColores(String nombreColor) {
        if (nombreColor == null) return Color.WHITE;
        switch (nombreColor.toLowerCase()) {
            case "rojo": return Color.RED;
            case "celeste": return Color.LIGHTBLUE;
            case "amarillo": return Color.GOLD;
            case "verde": return Color.LIMEGREEN;
            case "negro": return Color.BLACK;
            default: return Color.GRAY;
        }
    }
}
