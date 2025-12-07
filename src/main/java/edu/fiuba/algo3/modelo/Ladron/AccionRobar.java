package edu.fiuba.algo3.modelo.Ladron;

import edu.fiuba.algo3.modelo.Jugador.Jugador;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AccionRobar {
    private Jugador ladron;
    private List<Jugador> victimasPotenciales;

    public AccionRobar(Jugador ladron, List<Jugador> victimasPotenciales) {
        this.ladron = ladron;
        this.victimasPotenciales = victimasPotenciales;
    }

    public void agregarPosibleVictima(Jugador victima) {
        if (victima != ladron && !victimasPotenciales.contains(victima)) {
            victimasPotenciales.add(victima);
        }
    }

    public void ejecutar() {
        if (victimasPotenciales.isEmpty()) return;

        Random random = new Random();
        Jugador victimaElegida = victimasPotenciales.get(random.nextInt(victimasPotenciales.size()));

        victimaElegida.serRobadoPor(ladron);
    }
}
