package edu.fiuba.algo3.modelo.Comercio;

import java.util.List;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import java.lang.reflect.InvocationTargetException;

/**
 * Banca
 */
public class Banca {

    public void entregarTipos(Jugador jugador, List<Class<? extends Recurso>> recibe) {

        for (Class<? extends Recurso> tipo : recibe) {
            Recurso recurso = instanciarRecurso(tipo);
            jugador.recibirRecurso(recurso);
        }
    }

    private Recurso instanciarRecurso(Class<? extends Recurso> tipo) {
        try {
            return tipo.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException
                | NoSuchMethodException e) {

            throw new RuntimeException(
                    "No se pudo instanciar el recurso: " + tipo.getSimpleName(),
                    e);
        }
    }
}
