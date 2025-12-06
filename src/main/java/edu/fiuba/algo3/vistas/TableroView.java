package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.ElementosTablero.Hexagono;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.Terreno;
import edu.fiuba.algo3.vistas.HexagonoView;
import javafx.scene.layout.Pane;
import edu.fiuba.algo3.vistas.escenas.*;

import java.util.Iterator;
import java.util.List;

public class TableroView extends Pane {
    private final double RADIO = 70;
    private final double RADIO_TABLERO = 3;
    private final double ANCHO_HEX = Math.sqrt(3) * RADIO;
    private final double ALTO_HEX = 2 * RADIO;

    private final int[] FICHAS_POR_FILA = { 0, 3, 4, 5, 4, 3, 0 };

    public TableroView(Tablero tableroModelo) {
        inicializarTablero(tableroModelo);
    }

    private void inicializarTablero(Tablero tableroModelo) {
        List<Hexagono> nodos = tableroModelo.getHexagonos();
        Iterator<Hexagono> iterador = nodos.iterator();

        double centroX = 0;
        double centroY = 0;

        for (int fila = 0; fila < FICHAS_POR_FILA.length; fila++) {
            int cantidad = FICHAS_POR_FILA[fila];

            boolean usarModelo = (cantidad > 0);
            crearFila(fila, cantidad, usarModelo, iterador, centroX, centroY);
        }

        this.setPrefSize(800, 600);
    }

    private void crearFila(int fila, int cantidad, boolean usarModelo,
            Iterator<Hexagono> iterador, double centroX, double centroY) {

        double offsetFila = Math.abs(RADIO_TABLERO - fila) * (ANCHO_HEX / 2);
        double y = centroY + (fila * (ALTO_HEX * 0.75));

        if (!usarModelo) {
            for (int col = 0; col < 4; col++) {
                double x = centroX + offsetFila + (col * ANCHO_HEX);
                agregarHexagonoMar(x, y);
            }
            return;
        }
        agregarHexagonoMar(centroX + offsetFila, y);

        for (int col = 1; col <= cantidad; col++) {

            Hexagono hex;
            double x = centroX + offsetFila + (col * ANCHO_HEX);
            hex = iterador.next();

            agregarHexagono(hex, x, y);
        }

        double xDerecha = centroX + offsetFila + ((cantidad + 1) * ANCHO_HEX);
        agregarHexagonoMar(xDerecha, y);
    }

    private void agregarHexagono(Hexagono hex, double x, double y) {
        HexagonoView hexView = new HexagonoView(RADIO, hex);
        hexView.setLayoutX(x);
        hexView.setLayoutY(y);
        this.getChildren().add(hexView);
    }

    private void agregarHexagonoMar(double x, double y) {
        agregarHexagono(new Hexagono(Terreno.MAR, 0), x, y);
    }
}
