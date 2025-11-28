package edu.fiuba.algo3.modelo.Construcciones;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.Hexagono;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

/**
 * Poblado
 */
public class Poblado extends Construccion {

    public Poblado(Jugador duenio) {
        super(duenio);
    }
    public int getPuntosDeVictoria() {
        return 1;
    }

    @Override
    public Recurso generarSegunHexagono(Hexagono hexagono, int numDado) {
        Recurso recurso = hexagono.obtenerRecurso(numDado);
        return recurso;
    }

    @Override
    public void agregarDuenio(List<Jugador> listaVictimas) {
        if (!listaVictimas.contains(this.duenio)) {
            listaVictimas.add(this.duenio);
        }
    }

    @Override
    public ArrayList<Recurso> generarSegunVertice(int dado) {
        return this.verticeAsignado.generarRecurso(dado);
    }


}
