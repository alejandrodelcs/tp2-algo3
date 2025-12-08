package edu.fiuba.algo3.modelo.Tablero;

import java.util.*;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Excepciones.NoExisteFichaError;
import edu.fiuba.algo3.modelo.Ladron.Ladron;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

public class Tablero {

    private final List<Hexagono> hexagonos;
    private static final List<Integer> DISTRIBUCION = new ArrayList<>(
            List.of(2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12));
    private static final List<Terreno> TERRENOS = new ArrayList<>(List.of(new Terreno[] { Terreno.BOSQUE,
            Terreno.CAMPO, Terreno.COLINA, Terreno.MONTANA, Terreno.PASTIZAL }));
    private Ladron ladron;

    public Tablero() {
        this.hexagonos = new ArrayList<>();
    }

    public void construir() {

        for (int i = 0; i < DISTRIBUCION.size(); i++) {
            Terreno terreno = TERRENOS.get(i % TERRENOS.size());
            Hexagono hexagono = new Hexagono(terreno, DISTRIBUCION.get(i));
            hexagonos.add(hexagono);
        }

        Random random = new Random();
        int posicionAleatoria = random.nextInt(hexagonos.size() + 1);
        Hexagono desierto = new Hexagono(Terreno.DESIERTO, -1);
        hexagonos.add(posicionAleatoria, desierto);
        this.ladron = new Ladron(desierto);

        this.generarVertices();
    }

    private void generarVertices() {
        Map<String, Vertice> mapaVertices = new HashMap<>();

        Iterator<Hexagono> iterador = hexagonos.iterator();

        int[] fichasPorFila = { 3, 4, 5, 4, 3 };

        double radio = 10.0;
        double ancho = Math.sqrt(3) * radio;
        double alto = 2 * radio;

        double centroY = 0;

        for (int fila = 0; fila < fichasPorFila.length; fila++) {
            int cantidad = fichasPorFila[fila];

            double offsetX = Math.abs(2 - fila) * (ancho / 2.0);

            for (int col = 0; col < cantidad; col++) {
                if (!iterador.hasNext())
                    break;

                Hexagono hex = iterador.next();
                double centroX = offsetX + (col * ancho);

                asignarVerticesAHexagono(hex, centroX, centroY, radio, mapaVertices);
            }

            centroY += alto * 0.75;
        }
    }

    private void asignarVerticesAHexagono(Hexagono hex, double cx, double cy, double radio, Map<String, Vertice> mapa) {
        for (int i = 0; i < 6; i++) {
            double angulo = Math.toRadians(30 + (60 * i));

            double vx = cx + radio * Math.cos(angulo);
            double vy = cy + radio * Math.sin(angulo);

            String clave = String.format("%.2f_%.2f", vx, vy);

            mapa.putIfAbsent(clave, new Vertice());

            Vertice vertice = mapa.get(clave);

            vertice.agregarHexagono(hex);
        }
    }

    public int obtenerRecursosDe(int valorFicha) {
        int totalRecursos = 0;
        // boolean fichaEncontrada = false;
        int indice = 0;

        while (indice < hexagonos.size()) {
            Hexagono hexagono = hexagonos.get(indice);
            Recurso recurso = hexagono.obtenerRecurso(valorFicha);

            if (recurso != null) {
                totalRecursos++;
            }

            // fichaEncontrada = true;
            indice++;
        }

        if (totalRecursos == 0) {
            throw new NoExisteFichaError();
        }

        return totalRecursos;
    }

    public void moverLadronA(Hexagono destino) {
        this.ladron.moverA(destino);
    }

    public int cantidadHexagonos() {
        return hexagonos.size();
    }

    public void robarConLadronA(Jugador victima) {
        this.ladron.robar(victima);
    }

    public void agregarHexagono(Hexagono origen) {
        hexagonos.add(origen);
    }

    public void colocarLadronEn(Hexagono origen) {
        this.ladron = new Ladron(origen);
    }

    public List<Hexagono> getHexagonos() {
        return hexagonos;
    }

    public List<Terreno> getTerrenos() {
        return TERRENOS;
    }
}
