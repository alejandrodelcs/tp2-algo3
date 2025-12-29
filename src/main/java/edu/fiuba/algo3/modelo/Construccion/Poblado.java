package edu.fiuba.algo3.modelo.Construccion;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.Costo.Costo;
import edu.fiuba.algo3.modelo.Costo.ReglaCosto;
import edu.fiuba.algo3.modelo.Costo.ReglaCostoConstruccion;
import edu.fiuba.algo3.modelo.Recurso.*;

/**
 * Poblado
 */
public class Poblado extends Construccion {

    @Override
    public String getNombre() {
        return "poblado";
    }

    public Poblado(ReglaConstruccion... regla) {
        super(new ConstruirAsentamiento(List.of(regla)), new ReglaCostoConstruccion());
        this.costo = new Costo(Madera.class, Ladrillo.class, Lana.class, Grano.class);
    }

    public int getPuntosDeVictoria() {
        return 1;
    }

    @Override
    public void producirSegun(Recurso recurso) {
        propietario.recibirRecurso(recurso);
    }

    @Override
    public Construccion mejorar() {
        return new Ciudad();
    }

}
