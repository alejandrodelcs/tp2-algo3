package edu.fiuba.algo3.modelo.ElementosTablero;

import java.util.ArrayList;

import edu.fiuba.algo3.modelo.Construcciones.*;
import edu.fiuba.algo3.modelo.Errores.ReglaDistanciaExeption;

/**
 * Vertice
 */
public class Vertice {
    private Construccion construccion;
    private ArrayList<Arista> aristas;

    public Vertice() {
        this.construccion = new ConstruccionNula();
        this.aristas = new ArrayList<>();
    }

    public void construir(Construccion construccion) {
        for (Arista arista : aristas) {
            if (arista.vecinoConstruido(this)) {
                throw new ReglaDistanciaExeption();
            }

        }

        this.construccion = construccion;
    }

    public void conectarArista(Arista arista) {
        this.aristas.add(arista);
    }

    public int puntoVictoria() {
        return this.construccion.getPuntosDeVictoria();
    }

    public boolean tieneConstruccion() {
        return !this.construccion.esNula();
    }

}
