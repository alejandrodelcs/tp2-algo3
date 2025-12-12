package edu.fiuba.algo3.modelo.Recurso;

import java.util.Objects;

public abstract class Recurso {
    public int cantidad;

    public Recurso() {
        this.cantidad = 1;
    }

    public abstract int obtenerRecurso();

    public int acumular(int acumulador) {
        return acumulador + this.cantidad;
    }

    public abstract Recurso clonar();

    public void aumentar(int valor) {
        this.cantidad += valor;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        return obj != null && getClass() == obj.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass());
    }

    public boolean esDelMismoTipoQue(Class<? extends Recurso> tipo) {
        return tipo.isAssignableFrom(this.getClass());
    }

    public boolean mismoTipoQue(Recurso otro) {
        return this.getClass() == otro.getClass();
    }

    @Override
    public abstract String toString();
}
