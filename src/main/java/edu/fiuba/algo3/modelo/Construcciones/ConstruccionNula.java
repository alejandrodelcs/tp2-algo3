package edu.fiuba.algo3.modelo.Construcciones;

import java.util.ArrayList;

import edu.fiuba.algo3.modelo.Costo.Costo;
import edu.fiuba.algo3.modelo.Hexagono;
import edu.fiuba.algo3.modelo.Inventario;
import edu.fiuba.algo3.modelo.Recurso.Ladrillo;
import edu.fiuba.algo3.modelo.Recurso.Madera;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso.*;

/**
 * ConstruccionNula
 */
public class ConstruccionNula extends Construccion {

    public ConstruccionNula() {
        this.costo = null;
    }

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
    public void pagarCon(Inventario inventario) {

    }
}
