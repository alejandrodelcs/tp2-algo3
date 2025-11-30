package edu.fiuba.algo3.modelo.ElementosTablero;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.Hexagono;
import edu.fiuba.algo3.modelo.Construcciones.*;
import edu.fiuba.algo3.modelo.Errores.ReglaDistanciaExeption;
import edu.fiuba.algo3.modelo.Inventario;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso.*;

/**
 * Vertice
 */
public class Vertice {
    private Construccion construccion;
    private ArrayList<Arista> aristas;
    private ArrayList<Hexagono> hexagonos;

    public Vertice() {
        this.construccion = new ConstruccionNula();
        this.aristas = new ArrayList<>();
        this.hexagonos = new ArrayList<>();
    }

    public void construir(Construccion construccion) {
        for (Arista arista : aristas) {
            if (arista.vecinoConstruido(this)) {
                throw new ReglaDistanciaExeption();
            }

        }

        this.construccion = construccion;
        construccion.asignarVertice(this);
    }

    public void asignarHexagonos(Hexagono hexagono) {
        this.hexagonos.add(hexagono);

        hexagono.conectarVertice(this);
    }

    public void agregarVictimaPotencial(List<Jugador> listaVictimas) {
        this.construccion.agregarDuenio(listaVictimas);
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

    public ArrayList<Recurso> generarRecurso(int numDado) {
        ArrayList<Recurso> recursosGenerados = new ArrayList<>();

        for (Hexagono hexagono : this.hexagonos) {
            Recurso recursoAux = construccion.generarSegunHexagono(hexagono, numDado);

            if (recursoAux != null) {
                recursosGenerados.add(recursoAux);
            }
        }
        return recursosGenerados;
    }

    public Inventario entregarRecursosIniciales() {
        ArrayList<Recurso> recursos = new ArrayList<>();

        if (!this.tieneConstruccion()) {
            return null; // Crear una excepcion y testear
        }

        for (Hexagono hex : this.hexagonos) {
            Recurso r = hex.obtenerRecursoBase();
            if (r != null) {
                recursos.add(r);
            }
        }
        return new Inventario(recursos.toArray(new Recurso[0]));
    }

}
