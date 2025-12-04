package edu.fiuba.algo3.modelo.Construcciones;

import java.util.ArrayList;
import edu.fiuba.algo3.modelo.Costo.Costo;
import edu.fiuba.algo3.modelo.Tablero.Hexagono;
import edu.fiuba.algo3.modelo.Juego.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Grano;
import edu.fiuba.algo3.modelo.Recurso.Mineral;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

public class Ciudad extends Construccion {

    public Ciudad(Jugador duenio) {
        super(duenio);
    }

    public Ciudad() {
        this.costo = new Costo(Grano.class, Grano.class, Mineral.class, Mineral.class, Mineral.class);
    }

    @Override
    public int getPuntosDeVictoria() {
        return 2;
    }


    @Override
    public ArrayList<Recurso> generarSegunVertice(int dado) {

        return this.verticeAsignado.generarRecurso(dado,2);
    }




}
