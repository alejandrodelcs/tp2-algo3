package edu.fiuba.algo3.modelo.Construcciones;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.Costo.Costo;
import edu.fiuba.algo3.modelo.Hexagono;
import edu.fiuba.algo3.modelo.Recurso.*;

/**
 * Poblado
 */
public class Poblado extends Construccion {

    public Poblado(){
        this.costo = new Costo(Madera.class, Ladrillo.class,Lana.class, Grano.class);
    }


    public Poblado(Jugador dueño) {
        super(dueño);
    }

    public int getPuntosDeVictoria() {
        return 1;
    }

    @Override
    public Recurso generarSegunHexagono(Hexagono hexagono, int numDado) {
        return hexagono.obtenerRecurso(numDado);
    }


    @Override
    public void agregarDuenio(List<Jugador> listaVictimas) {
        if (!listaVictimas.contains(this.dueño)) {
            listaVictimas.add(this.dueño);
        }
    }

    @Override
    public boolean puedeSerMejorada() {
        return true;
    }

    @Override
    public List<Recurso> getCosto() {
        return List.of(new Madera(), new Ladrillo(), new Grano(), new Lana());
    }

    @Override
    public ArrayList<Recurso> generarSegunVertice(int dado) {

        return this.verticeAsignado.generarRecurso(dado);
    }


    @Override
    public void consumir(Inventario inventario) {
        inventario.consumir(Madera.class);
    }
}
