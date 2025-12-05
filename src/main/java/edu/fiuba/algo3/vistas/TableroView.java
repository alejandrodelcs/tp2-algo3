package edu.fiuba.algo3.vistas;

import javafx.scene.layout.Pane;

public class TableroView extends Pane {
    private final double RADIO = 50;
    private final double ANCHO_HEX = Math.sqrt(3) * RADIO;
    private final double ALTO_HEX = 2 * RADIO;

    private final int[] cantidadPorFila = {3, 4, 5, 4, 3};

    private final String[] terrenos = {
            "/images/montaña.png", "/images/bosque.png", "/images/pastizal.png",
            "/images/pastizal.png", "/images/campo.png", "/images/bosque.png", "/images/campo.png",
            "/images/bosque.png", "/images/colina.png", "/images/desierto.png", "/images/montaña.png", "/images/pastizal.png",
            "/images/campo.png", "/images/piedra.png", "/images/bosque.png", "/images/oveja.png",
            "/images/campo.png", "/images/campo.png", "/images/colina.png"
    };

    private final String[] tokens = {"5", "2", "6", "3", "8", "10", "9", "12", "11", "4", "8", "10", "9", "4", "5", "6", "3", "11"};

    public TableroView() {
        inicializarTablero();
    }

    private void inicializarTablero() {
        double centroX = 400;
        double centroY = 100;

        int contadorFichas = 0;
        int contadorTokens = 0;

        for (int fila = 0; fila < cantidadPorFila.length; fila++) {

            int cantidadEnFila = cantidadPorFila[fila];

            double desfaseX = (5 - cantidadEnFila) * (ANCHO_HEX / 2);

            for (int col = 0; col < cantidadEnFila; col++) {

                if (contadorFichas >= terrenos.length) break;
                String recurso = terrenos[contadorFichas];

                String numero = "";
                if (!recurso.contains("desierto") && contadorTokens < tokens.length) {
                    numero = tokens[contadorTokens++];
                }

                HexagonoView hexagono = new HexagonoView(RADIO, recurso, numero);

                double posX = centroX + desfaseX + (col * ANCHO_HEX);
                double posY = centroY + (fila * (ALTO_HEX * 0.75));

                hexagono.setLayoutX(posX);
                hexagono.setLayoutY(posY);

                this.getChildren().add(hexagono);
                contadorFichas++;
            }
        }

        this.setPrefSize(800, 600);
    }
}
