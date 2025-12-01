package edu.fiuba.algo3.modelo;

import java.util.*;

import edu.fiuba.algo3.modelo.Errores.NoExisteFichaError;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

public class Tablero {

    private final List<Hexagono> hexagonos;
    private static final List<Integer> DISTRIBUCION = new ArrayList<>(
            List.of(2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12));
    private static final List<Terreno> TERRENOS = new ArrayList<>(List.of(new Terreno[] { Terreno.BOSQUE,
            Terreno.CAMPO, Terreno.COLINA, Terreno.DESIERTO, Terreno.MONTANA, Terreno.PASTIZAL }));

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

    public void moverLadron(Hexagono hexagonoDestino, Jugador ladron) {

        hexagonoDestino.colocarLadron();

        List<Jugador> posiblesVictimas = hexagonoDestino.obtenerVictimas();

        posiblesVictimas.remove(ladron);

        if (!posiblesVictimas.isEmpty()) {
            Random random = new Random();
            Jugador victima = posiblesVictimas.get(random.nextInt(posiblesVictimas.size()));

            ladron.robarA(victima);
        }
    }

    public int cantidadHexagonos() {
        return hexagonos.size();
    }
}
