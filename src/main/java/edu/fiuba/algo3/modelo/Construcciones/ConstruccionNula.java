package edu.fiuba.algo3.modelo.Construcciones;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.Hexagono;
import edu.fiuba.algo3.modelo.Inventario;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso.*;

/**
 * ConstruccionNula
 */
public class ConstruccionNula extends Construccion {


    public int getPuntosDeVictoria() {
        return 0;
    }

    @Override
    public void agregarDuenio(List<Jugador> listaVictimas) {

    }

    @Override
    public boolean esNula() {
        return true;
    }

    @Override
    public List<Recurso> getCosto() {
        return List.of(new Madera(), new Ladrillo(), new Grano(), new Lana());
    }

    @Override
    public Recurso generarSegunHexagono(Hexagono hexagono, int numDado) {
        return null;
    }

    @Override
    public ArrayList<Recurso> generarSegunVertice(int dado) {
        return null;
    }


    @Override
    public void consumir(Inventario inventario) {
    }
}
