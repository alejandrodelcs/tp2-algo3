package edu.fiuba.algo3.modelo.Material;

import javafx.scene.paint.Material;

import java.util.Collections;
import java.util.Map;

public class Costo {

    private final Map<TipoRecurso, Integer> requisitos;


    private Costo(Map<TipoRecurso, Integer> requisitos) {

        this.requisitos = Collections.unmodifiableMap(requisitos);
    }


    public Map<TipoRecurso, Integer> getRequisitos() {
        return this.requisitos;
    }



    public static Costo POBLADO() {
        return new Costo(Map.of(
                TipoRecurso.MADERA, 1,
                TipoRecurso.LADRILLO, 1,
                TipoRecurso.LANA, 1,
                TipoRecurso.GRANO, 1
        ));
    }


    public static Costo CIUDAD() {
        return new Costo(Map.of(
                TipoRecurso.GRANO, 2,
                TipoRecurso.MINERAL, 3
        ));
    }

    public static Costo CARRETERA() {
        return new Costo(Map.of(
                TipoRecurso.MADERA, 1,
                TipoRecurso.LADRILLO, 1
        ));
    }

    public static Costo CARTA_DESARROLLO() {
        return new Costo(Map.of(
                TipoRecurso.LANA, 1,
                TipoRecurso.GRANO, 1,
                TipoRecurso.MINERAL, 1
        ));
    }
}
