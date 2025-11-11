package edu.fiuba.algo3.modelo.Juego;

import edu.fiuba.algo3.modelo.Material.Costo;
import edu.fiuba.algo3.modelo.Material.TipoRecurso;
import javafx.scene.paint.Material;

import java.util.HashMap;
import java.util.Map;

public class Inventario {

    private Map<TipoRecurso, Integer> recursos;


    public Inventario() {
        this.recursos = new HashMap<>();
    }


    public void agregarRecurso(TipoRecurso tipo, int cantidad) {

        int cantidadActual = this.recursos.getOrDefault(tipo, 0);
        this.recursos.put(tipo, cantidadActual + cantidad);
    }


    public boolean tieneSuficiente(Costo costo) {

        for (Map.Entry<TipoRecurso, Integer> requisito : costo.getRequisitos().entrySet()) {

            TipoRecurso tipoRequerido = requisito.getKey();
            int cantidadRequerida = requisito.getValue();


            if (this.contar(tipoRequerido) < cantidadRequerida) {
                return false;
            }
        }
        return true;
    }

    public void consumirRecursos(Costo costo) {
        for (Map.Entry<TipoRecurso, Integer> requisito : costo.getRequisitos().entrySet()) {

            TipoRecurso tipo = requisito.getKey();
            int cantidadAConsumir = requisito.getValue();


            int cantidadActual = this.recursos.get(tipo);
            this.recursos.put(tipo, cantidadActual - cantidadAConsumir);
        }
    }


    public int contar(TipoRecurso tipo) {
        return this.recursos.getOrDefault(tipo, 0);
    }
}
