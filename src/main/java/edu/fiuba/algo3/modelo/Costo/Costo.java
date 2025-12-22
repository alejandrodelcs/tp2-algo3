package edu.fiuba.algo3.modelo.Costo;

import edu.fiuba.algo3.modelo.Excepciones.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.Jugador.Inventario;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.Arrays;
import java.util.List;

public class Costo {
    private final List<Class<? extends Recurso>> recursos;

    @SafeVarargs
    public Costo(Class<? extends Recurso>... recursos) {
        this.recursos = Arrays.asList(recursos);
    }

    public int cantidadDe(Class<? extends Recurso> recurso) {

        int acumulador = 0;
        for (Class<? extends Recurso> rec : this.recursos) {
            if (rec.equals(recurso)) {
                acumulador++;

            }

        }
        return acumulador;
    }

    public boolean puedePagar(Inventario inventario) {

        for (Class<? extends Recurso> rec : this.recursos) {
            if (this.cantidadDe(rec) > inventario.cantidadDeTipo(rec)) {
                return false;
            }

        }
        return true;

    }

    public void aplicar(Inventario inventario) {
        pagar(inventario);
    }

    public void pagar(Inventario inventario) {
        if (!puedePagar(inventario))
            throw new RecursosInsuficientesException("no hay recursos");

        recursos.forEach(inventario::consumir);
    }
}
