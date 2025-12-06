package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.ElementosTablero.Hexagono;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.vistas.HexagonoView;
import javafx.scene.layout.Pane;
import edu.fiuba.algo3.vistas.escenas.*;

import java.util.Iterator;
import java.util.List;

public class TableroView extends Pane {
    private final double RADIO = 50;
    private final double ANCHO_HEX = Math.sqrt(3) * RADIO;
    private final double ALTO_HEX = 2 * RADIO;

    private final int[] FICHAS_POR_FILA = {3, 4, 5, 4, 3};

    private final String[] terrenos = {
            "/images/mar.png", "/images/mar.png", "/images/mar.png", "/images/mar.png",
            "/images/mar.png",
            "/images/montaña.png", "/images/bosque.png", "/images/pastizal.png",
            "/images/mar.png", "/images/mar.png",
            "/images/pastizal.png", "/images/campo.png", "/images/bosque.png", "/images/campo.png",
            "/images/mar.png", "/images/mar.png",
            "/images/bosque.png", "/images/colina.png", "/images/desierto.png", "/images/montaña.png",
            "/images/pastizal.png",
            "/images/mar.png", "/images/mar.png",
            "/images/campo.png", "/images/colina.png", "/images/bosque.png", "/images/pastizal.png",
            "/images/mar.png", "/images/mar.png",
            "/images/campo.png", "/images/campo.png", "/images/colina.png",
            "/images/mar.png",
            "/images/mar.png", "/images/mar.png", "/images/mar.png", "/images/mar.png",

    };

    private final String[] tokens = {
            "", "", "", "", "",
            "5", "2", "6", "",
            "", "3", "8", "10", "9", "",
            "", "12", "11", "4", "8", "",
            "", "10", "9", "4", "5", "",
            "", "6", "3", "11", "",
            "", "", "", "",
    };

    public TableroView(Tablero tableroModelo) {
        inicializarTablero(tableroModelo);
    }

    private void inicializarTablero(Tablero tableroModelo) {
        List<Hexagono> nodos = tableroModelo.getHexagonos();

        if (nodos.size() < 19) {
            System.err.println("Advertencia: El modelo trajo menos de 19 hexágonos");
        }

        Iterator<Hexagono> iterador = nodos.iterator();

        // Ajustar para centrar en tu pantalla
        double centroX = 350;
        double centroY = 100;

        for (int fila = 0; fila < FICHAS_POR_FILA.length; fila++) {

            int cantidadEnFila = FICHAS_POR_FILA[fila];

            double offsetFila = Math.abs(2 - fila) * (ANCHO_HEX / 2);

            for (int col = 0; col < cantidadEnFila; col++) {

                if (!iterador.hasNext()) break;

                Hexagono hexagonoReal = iterador.next();

                HexagonoView hexView = new HexagonoView(RADIO, hexagonoReal);

                double x = centroX + offsetFila + (col * ANCHO_HEX);
                double y = centroY + (fila * (ALTO_HEX * 0.75));

                hexView.setLayoutX(x);
                hexView.setLayoutY(y);

                this.getChildren().add(hexView);
            }
        }

        this.setPrefSize(800, 600);
    }
}
