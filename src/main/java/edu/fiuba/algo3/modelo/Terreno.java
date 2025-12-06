package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Recurso.*;

public enum Terreno {
    BOSQUE(new Madera()),
    MAR(new Mar()),
    COLINA(new Ladrillo()),
    PASTIZAL(new Lana()),
    CAMPO(new Grano()),
    MONTANA(new Mineral()),
    DESIERTO(null);

    private final Recurso recurso;

    Terreno(Recurso recurso) {
        this.recurso = recurso;
    }

    public int obtenerRecurso() {
        if (recurso != null) {
            return recurso.obtenerRecurso();
        }
        return 0;
    }

    public Recurso retornarRecurso() {
        return recurso.clonar();
    }

}
