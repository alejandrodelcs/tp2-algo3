package edu.fiuba.algo3.modelo.Juego.Terreno;

import edu.fiuba.algo3.modelo.Construccion.Construccion;
import edu.fiuba.algo3.modelo.Material.TipoRecurso;

import java.util.ArrayList;
import java.util.List;

public class Cruce {

    private int id;
    private Construccion construccion = null;
    private List<Cruce> vecinos = new ArrayList<>(3);
    private List<Arista> aristas = new ArrayList<>(3);
    private List<Hexagono> hexagonos = new ArrayList<>(3);

    public Cruce(int id) {
        this.id = id;
    }

    public void agregarVecino(Cruce cruce) { this.vecinos.add(cruce); }
    public void agregarArista(Arista arista) { this.aristas.add(arista); }
    public void agregarHexagono(Hexagono hexagono) { this.hexagonos.add(hexagono); }


    public void setConstruccion(Construccion construccion) { this.construccion = construccion; }

    public boolean esValidoParaConstruir() {
        if (this.construccion != null) return false;
        for (Cruce v : vecinos) {
            if (v.estaOcupado()) return false;
        }
        return true;
    }

    public void generarProduccion(TipoRecurso recurso) {
        if (this.construccion != null) {
            this.construccion.reclamarProduccion(recurso);
        }
    }

    public boolean estaOcupado() {
        return (this.construccion != null);
    }
}
