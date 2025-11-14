package edu.fiuba.algo3.modelo.Juego;

import edu.fiuba.algo3.modelo.Juego.Terreno.*;
import edu.fiuba.algo3.modelo.Material.TipoRecurso;
import edu.fiuba.algo3.modelo.elementos.Ficha;

import java.util.*;

public class TableroBuilder {
    private static final int[][] DATA_HEXAGONO_CRUCES = {
            {0, 1, 2, 10, 9, 8},            // 0
            {2, 3, 4, 12, 11, 10},          // 1
            {4, 5, 6, 14, 13 ,12},          // 2
            {7, 8, 9, 19, 18, 17},          // 3
            {9, 10, 11, 21, 20, 19},        // 4
            {11, 12, 13, 23, 22, 21},       // 5
            {13, 14, 15, 25, 24, 23},       // 6
            {16, 17, 18, 29, 28, 27},       // 7
            {18, 19, 20, 31, 32, 29},       // 8
            {20, 21, 22, 33, 32, 31},       // 9
            {22, 23, 24, 35, 34, 33},       // 10
            {24, 25, 26, 37, 36, 35},       // 11
            {28, 29, 30, 40, 39, 38},       // 12
            {30, 31, 32, 42, 41, 40},       // 13
            {32, 33, 34, 44, 43, 42},       // 14
            {34, 35, 36, 46, 45, 44},       // 15
            {39, 40, 41, 49, 48, 47},       // 16
            {41, 42, 43, 51, 50, 49},       // 17
            {42, 43, 44, 53, 52, 51}        // 18
    };

    private static final int[][] DATA_CRUCE_VECINOS = {
            {1, 8 , -1},
            {0, 2, -1},
            {1, 3, 10},
            {2, 4, -1},
            {3, 5, 12},
            {4, 6 ,-1},
            {5, 14, -1},
            {8, 17, -1},
            {0, 7, 9},
            {8, 10, 19},
            {9, 2, 11},
            {10, 21, 12},
            {11, 4, 13},
            {12, 23, 14},
            {13, 6, 15},
            {14, 25, -1},
            {17, 27, -1},
            {16, 7, 18},
            {17, 24, 19},
            {18, 9, 20},
            {19, 31, 21},
            {20, 11, 22},
            {21, 33, 23},
            {22, 13, 24},
            {23, 35, 25},
            {24, 15, 26},
            {25, 37, -1},
            {16, 28, -1},
            {27, 38, 29},
            {28, 18, 30},
            {29, 40, 31},
            {30, 20, 32},
            {31, 42, 33},
            {32, 22, 34},
            {33, 44, 35},
            {34, 24, 36},
            {35, 46, 37},
            {28, 34, -1},
            {38, 47, 40},
            {39, 30, 41},
            {40, 49, 42},
            {41, 32, 43},
            {42, 51, 44},
            {43, 34, 45},
            {44, 53, 46},
            {45, 36, -1},
            {34, 48, -1},
            {47, 49, -1},
            {48, 41, 50},
            {49, 51, -1},
            {50, 43, 52},
            {51, 53, -1},
            {52, 45, -1}
    };

    private Map<Integer, Cruce> mapaCruces;
    private List<Hexagono> listaHexagonos;
    private Hexagono hexDesierto;

    public Tablero construirTableroEstandar() {

        inicializarColecciones();

        conectarTopologia();

        asignarRecursosYFichas();

        return new Tablero(mapaCruces, listaHexagonos, hexDesierto);
    }

    private void inicializarColecciones() {
        this.mapaCruces = new HashMap<>();
        for (int i = 0; i < 54; i++) {
            this.mapaCruces.put(i, new Cruce(i));
        }

        this.listaHexagonos = new ArrayList<>();
        for (int i = 0; i < 19; i++) {
            this.listaHexagonos.add(new Hexagono());
        }
    }

    private void conectarTopologia() {
        for (int i = 0; i < 19; i++) {
            Hexagono hex = listaHexagonos.get(i);
            int[] idsCruces = DATA_HEXAGONO_CRUCES[i];

            for (int idCruce : idsCruces) {
                Cruce cruce = mapaCruces.get(idCruce);
                hex.agregarCruce(cruce);
                cruce.agregarHexagono(hex);
            }
        }

        for (int i = 0; i < 54; i++) {
            Cruce cruce = mapaCruces.get(i);
            int[] idsVecinos = DATA_CRUCE_VECINOS[i];

            for (int idVecino : idsVecinos) {
                if (idVecino != -1) { // -1 indica borde del mapa
                    Cruce vecino = mapaCruces.get(idVecino);
                    cruce.agregarVecino(vecino);
                }
            }
        }
    }

    private void asignarRecursosYFichas() {
        List<TipoRecurso> recursos = new ArrayList<>();
        recursos.add(TipoRecurso.DESIERTO); // 1 Desierto
        agregarVarios(recursos, TipoRecurso.MADERA, 4);
        agregarVarios(recursos, TipoRecurso.LANA, 4);
        agregarVarios(recursos, TipoRecurso.GRANO, 4);
        agregarVarios(recursos, TipoRecurso.LADRILLO, 3);
        agregarVarios(recursos, TipoRecurso.MINERAL, 3);
        Collections.shuffle(recursos);

        LinkedList<Integer> numeros = new LinkedList<>(Arrays.asList(
                5, 2, 6, 3, 8, 10, 9, 12, 11, 4, 8, 10, 9, 4, 5, 6, 3, 11
        ));

        int indiceFicha = 0;
        for (int i = 0; i < 19; i++) {
            Hexagono hex = listaHexagonos.get(i);
            TipoRecurso recurso = recursos.get(i);

            hex.setRecurso(recurso);

            if (recurso == TipoRecurso.DESIERTO) {
                this.hexDesierto = hex;
                hex.setFicha(null);
            } else {
                hex.setFicha(new Ficha(numeros.get(indiceFicha)));
                indiceFicha++;
            }
        }
    }

    private void agregarVarios(List<TipoRecurso> lista, TipoRecurso tipo, int cantidad) {
        for(int i=0; i<cantidad; i++) lista.add(tipo);
    }
}
