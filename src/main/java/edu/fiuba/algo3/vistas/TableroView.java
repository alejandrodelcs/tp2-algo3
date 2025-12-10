package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Tablero.Hexagono;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Terreno;
import edu.fiuba.algo3.modelo.Tablero.Vertice;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import edu.fiuba.algo3.vistas.escenas.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TableroView extends Pane {
    private final double RADIO = 70;
    private final double RADIO_TABLERO = 3;
    private final double ANCHO_HEX = Math.sqrt(3) * RADIO;
    private final double ALTO_HEX = 2 * RADIO;

    private final Map<Vertice, VerticeView> verticesVisuales = new HashMap<>();

    private final Group grupoHexagonos = new Group();
    private final Group grupoVertices = new Group();

    private final int[] FICHAS_POR_FILA = { 0, 3, 4, 5, 4, 3, 0 };

    public TableroView(Tablero tableroModelo) {
        this.getChildren().addAll(grupoHexagonos, grupoVertices);

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

            agregarVerticesDelHexagono(hex, x, y);
        }

        double xDerecha = centroX + offsetFila + ((cantidad + 1) * ANCHO_HEX);
        agregarHexagonoMar(xDerecha, y);
    }

    private void agregarHexagono(Hexagono hex, double x, double y) {
        HexagonoView hexView = new HexagonoView(RADIO, hex);
        hexView.setLayoutX(x);
        hexView.setLayoutY(y);
        grupoHexagonos.getChildren().add(hexView);
    }

    private void agregarVerticesDelHexagono(Hexagono hex, double hexX, double hexY) {
        List<Vertice> verticesModelo = hex.getVertices();

        if (verticesModelo == null || verticesModelo.isEmpty()) return;

        double w = ANCHO_HEX;
        double h = ALTO_HEX;

        double[][] offsets = {
                {w, h * 0.75},       // 0: Abajo Derecha (30°)
                {w / 2, h},          // 1: Abajo (90°)
                {0.0, h * 0.75},     // 2: Abajo Izquierda (150°)
                {0.0, h / 4},        // 3: Arriba Izquierda (210°)
                {w / 2, 0.0},        // 4: Arriba (270°)
                {w, h / 4}           // 5: Arriba Derecha (330°)
        };

        for (int i = 0; i < 6; i++) {
            if (i >= verticesModelo.size()) break;

            Vertice verticeReal = verticesModelo.get(i);

            if (verticesVisuales.containsKey(verticeReal)) {
                continue;
            }

            VerticeView vView = new VerticeView(verticeReal, RADIO);

            double correccionCentro = RADIO * 0.2;

            double vx = hexX + offsets[i][0] - correccionCentro;
            double vy = hexY + offsets[i][1] - correccionCentro;

            vView.setLayoutX(vx);
            vView.setLayoutY(vy);

//            vView.setViewOrder(-1.0);

            verticesVisuales.put(verticeReal, vView);
            grupoVertices.getChildren().add(vView);
        }
    }

    private void agregarHexagonoMar(double x, double y) {
        agregarHexagono(new Hexagono(Terreno.MAR, 0), x, y);
    }

    public void actualizar(Tablero tablero) {

    }
}
