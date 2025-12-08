package edu.fiuba.algo3.modelo.Ladron;

import edu.fiuba.algo3.modelo.Jugador.Jugador;

import java.util.List;
import java.util.Random;

public class AccionRobar {
    private Jugador ladron;
    private List<Jugador> victimas;

    public AccionRobar(Jugador ladron, List<Jugador> victimas) {
        this.ladron = ladron;
        this.victimas = victimas;
    }

    public void agregarPosibleVictima(Jugador victima) {
        if (victima != ladron && !victimas.contains(victima)) {
            victimas.add(victima);
        }
    }

    private Jugador seleccionarVictimaAleatoria(List<Jugador> victimas) {
        Random random = new Random();
        return victimas.get(random.nextInt(victimas.size()));
    }

    public void ejecutar() {
        if (victimas.isEmpty()) return;

        Jugador victimaElegida = seleccionarVictimaAleatoria(victimas);

        victimaElegida.entregarRecursoA(ladron);
    }
}
