package edu.fiuba.algo3.modelo.Comercio;

import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.List;

public interface ReglaComercio {
    void validar(List<Class<? extends Recurso>> entrega, List<Class<? extends Recurso>> recibe);
}
