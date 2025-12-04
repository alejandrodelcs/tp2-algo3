package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Juego.Jugador;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AccionRobar {
    private Jugador ladron;
    private List<Jugador> victimasPotenciales;

    public AccionRobar(Jugador ladron) {
        this.ladron = ladron;
        this.victimasPotenciales = new ArrayList<>();
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

        victimaElegida.entregarRecursoA(ladron);
    }
}
