package edu.fiuba.algo3.modelo.Construcciones;

import edu.fiuba.algo3.modelo.Hexagono;
import edu.fiuba.algo3.modelo.Inventario;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso.*;

import java.util.ArrayList;
import java.util.List;

public class Carretera extends Construccion{


    @Override
    public int getPuntosDeVictoria() {
        return 0;
    }

    @Override
    public boolean puedeSerMejorada() {
        return false;
    }

    @Override
    public List<Recurso> getCosto() {
        return List.of(new Madera(), new Ladrillo());
    }


    @Override
    public void agregarDuenio(List<Jugador> listaVictimas) {
        if (!listaVictimas.contains(this.dueño)) {
            listaVictimas.add(this.dueño);
        }
    }

    @Override
    public Recurso generarSegunHexagono(Hexagono hexagono, int numDado) {
        return hexagono.obtenerRecurso(numDado);
    }

    @Override
    public ArrayList<Recurso> generarSegunVertice(int dado) {
        return null;
    }


    @Override
    public void consumir(Inventario inventario) {

    }
}
