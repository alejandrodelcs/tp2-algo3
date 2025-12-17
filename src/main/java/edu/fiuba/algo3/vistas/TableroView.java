package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.ControladorJuego;
import edu.fiuba.algo3.modelo.Recurso.Mar;
import edu.fiuba.algo3.modelo.Tablero.Arista;
import edu.fiuba.algo3.modelo.Tablero.Hexagono;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Vertice;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import edu.fiuba.algo3.vistas.escenas.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class TableroView extends Pane {
    private final double RADIO = 70;
    private final double RADIO_TABLERO = 3;
    private final double ANCHO_HEX = Math.sqrt(3) * RADIO;
    private final double ALTO_HEX = 2 * RADIO;
    private ControladorJuego controlador;

    private final Map<Vertice, VerticeView> verticesVisuales = new HashMap<>();
    private VerticeView verticeSeleccionadoVisual = null;
    private AristaView aristaSeleccionadoVisual = null;

    private boolean modoSeleccionHexagono = false;
    private Consumer<Hexagono> hexagonoSeleccionado;

    private Map<Arista, AristaView> aristasMap;
    private final Group grupoHexagonos = new Group();
    private final Group grupoVertices = new Group();
    private Group grupoAristas;

    private final int[] FICHAS_POR_FILA = { 0, 3, 4, 5, 4, 3, 0 };

    public TableroView(Tablero tableroModelo, ControladorJuego controlador) {
        this.controlador = controlador;
        this.grupoAristas = new Group();
        this.aristasMap = new HashMap<>();

        this.getChildren().addAll(grupoHexagonos, grupoAristas ,grupoVertices);



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

        agregarHexagonoMar(centroX + offsetFila, y); // Mar izquierdo

        for (int col = 1; col <= cantidad; col++) {

            Hexagono hex;
            double x = centroX + offsetFila + (col * ANCHO_HEX);
            hex = iterador.next();

            agregarHexagono(hex, x, y);

            double radio = ALTO_HEX / 2;
            agregarAristasDelHexagono(hex, x, y, radio);

            agregarVerticesDelHexagono(hex, x, y);
        }

        double xDerecha = centroX + offsetFila + ((cantidad + 1) * ANCHO_HEX);
        agregarHexagonoMar(xDerecha, y); // Mar derecho
    }

    private void agregarHexagono(Hexagono hex, double x, double y) {
        HexagonoView hexView = new HexagonoView(RADIO, hex);
        hexView.setLayoutX(x);
        hexView.setLayoutY(y);

        hexView.setOnMouseClicked(e -> {
            if (modoSeleccionHexagono && hexagonoSeleccionado != null) {
                hexagonoSeleccionado.accept(hex);
                e.consume();
            }
        });

        grupoHexagonos.getChildren().add(hexView);
    }

    public void activarSelectorHexagono(Consumer<Hexagono> handler) {
        this.modoSeleccionHexagono = true;
        this.hexagonoSeleccionado = handler;
        this.setCursor(Cursor.HAND);
    }

    public void desactivarSelectorHexagono() {
        this.modoSeleccionHexagono = false;
        this.hexagonoSeleccionado = null;
        this.setCursor(Cursor.DEFAULT);
    }

    public void actualizarPosicionLadron() {
        for (Node nodo : grupoHexagonos.getChildren()) {
            if (nodo instanceof HexagonoView) {
                ((HexagonoView) nodo).actualizarVisualizacion();
            }
        }
    }

    public void actualizarVisualizacionDelSeleccionado() {
        if (verticeSeleccionadoVisual != null) {
            verticeSeleccionadoVisual.actualizarVisualizacion();
            verticeSeleccionadoVisual.deseleccionar();
            verticeSeleccionadoVisual = null;
        }
    }

    private void agregarVerticesDelHexagono(Hexagono hex, double hexX, double hexY) {
        List<Vertice> verticesModelo = hex.getVertices();

        if (verticesModelo == null || verticesModelo.isEmpty())
            return;

        double w = ANCHO_HEX;
        double h = ALTO_HEX;

        double[][] offsets = {
                { w, h * 0.75 }, // 0: Abajo Derecha (30°)
                { w / 2, h }, // 1: Abajo (90°)
                { 0.0, h * 0.75 }, // 2: Abajo Izquierda (150°)
                { 0.0, h / 4 }, // 3: Arriba Izquierda (210°)
                { w / 2, 0.0 }, // 4: Arriba (270°)
                { w, h / 4 } // 5: Arriba Derecha (330°)
        };

        for (int i = 0; i < 6; i++) {
            if (i >= verticesModelo.size())
                break;

            Vertice verticeReal = verticesModelo.get(i);

            if (verticesVisuales.containsKey(verticeReal)) {
                continue;
            }

            VerticeView vView = new VerticeView(verticeReal, RADIO, this::manejarClickVertice, this.controlador);

            double correccionCentro = RADIO * 0.2;

            double vx = hexX + offsets[i][0] - correccionCentro;
            double vy = hexY + offsets[i][1] - correccionCentro;

            vView.setLayoutX(vx);
            vView.setLayoutY(vy);


            verticesVisuales.put(verticeReal, vView);
            grupoVertices.getChildren().add(vView);
        }
    }

    private void agregarAristasDelHexagono(Hexagono hex, double centroX, double centroY, double radio) {
        List<Arista> aristas = hex.getAristas();

        double apotema = radio * (Math.sqrt(3) / 2);

        double[] angulos = { 60, 120, 180, 240, 300, 0 };

        for (int i = 0; i < 6; i++) {
            if (i >= aristas.size()) break;

            Arista aristaModelo = aristas.get(i);

            if (aristasMap.containsKey(aristaModelo)) {
                continue;
            }

            AristaView aristaView = new AristaView(aristaModelo, radio, this::manejarClickArista, this.controlador);

            double rad = Math.toRadians(angulos[i]);

            double x = centroX + apotema * Math.cos(rad);
            double y = centroY + apotema * Math.sin(rad);

            x += 35;
            y += 65;

            aristaView.setLayoutX(x);
            aristaView.setLayoutY(y);

            aristaView.setRotate(angulos[i] + 90);

            aristasMap.put(aristaModelo, aristaView);
            grupoAristas.getChildren().add(aristaView);
        }
    }

    private void manejarClickArista(AristaView vista) {
        System.out.println("Arista clickeada");

        if (aristaSeleccionadoVisual != null) {
            aristaSeleccionadoVisual.deseleccionar();
        }

        aristaSeleccionadoVisual = vista;
        aristaSeleccionadoVisual.seleccionar();

        if (verticeSeleccionadoVisual != null) {
            verticeSeleccionadoVisual.deseleccionar();
            verticeSeleccionadoVisual = null;
        }
    }

    private void manejarClickVertice(VerticeView nuevoVerticeClickeado) {
        if (verticeSeleccionadoVisual != null) {
            verticeSeleccionadoVisual.deseleccionar();
        }

        if (verticeSeleccionadoVisual == nuevoVerticeClickeado) {
            verticeSeleccionadoVisual = null;
            return;
        }

        verticeSeleccionadoVisual = nuevoVerticeClickeado;
        verticeSeleccionadoVisual.seleccionar();

        if (aristaSeleccionadoVisual != null) {
            aristaSeleccionadoVisual.deseleccionar();
            aristaSeleccionadoVisual = null;
        }

        System.out.println("Tablero: Vertice seleccionado guardado.");
    }

    public VerticeView obtenerVerticeSeleccionadoVisual() {
        return this.verticeSeleccionadoVisual;
    }

    public AristaView obtenerAristaSeleccionadaVisual() {
        return this.aristaSeleccionadoVisual;
    }

    private void agregarHexagonoMar(double x, double y) {
        agregarHexagono(new Hexagono(new Mar(), 0), x, y);
    }

    public void actualizar(Tablero tablero) {

    }
}
