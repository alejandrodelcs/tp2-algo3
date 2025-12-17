package edu.fiuba.algo3.modelo.Turno;

public interface ObservadorTurno {
    void onEstadoCambio(EstadoTurno nuevoEstado);
}
