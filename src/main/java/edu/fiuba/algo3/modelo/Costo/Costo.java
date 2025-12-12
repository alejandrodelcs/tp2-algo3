package edu.fiuba.algo3.modelo.Costo;

import edu.fiuba.algo3.modelo.Excepciones.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.Jugador.Inventario;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.Arrays;
import java.util.List;

public class Costo {
    private final List<Class<? extends Recurso>> recursos;

    @SafeVarargs
    public Costo(Class<? extends Recurso>...recursos) {
        this.recursos = Arrays.asList(recursos);
    }


    public boolean puedePagar(Inventario inventario) {
        return recursos.stream().allMatch(inventario::tiene);
    }

    public void aplicar(Inventario inventario) {
        recursos.forEach(inventario::consumir);
    }

    public void pagar(Inventario inventario) {
        if (!puedePagar(inventario))
            throw new RecursosInsuficientesException("no hay recursos");

        recursos.forEach(inventario::consumir);
    }
}
