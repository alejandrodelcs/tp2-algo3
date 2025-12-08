package edu.fiuba.algo3.modelo.Jugador;

import java.util.List;

import edu.fiuba.algo3.modelo.Banca.Banca;
import edu.fiuba.algo3.modelo.Excepciones.NoHayRecursoDisponibleError;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

/**
 * InteraccionComercioBanca
 */
public class InteraccionComercioBanca implements InteraccionJugador {

    private Banca banca;

    private List<Class<? extends Recurso>> entrega;
    private List<Class<? extends Recurso>> recibe;

    public InteraccionComercioBanca(List<Class<? extends Recurso>> entrega,
            List<Class<? extends Recurso>> recibe,
            Banca banca) {

        if (entrega.size() != recibe.size() * 4) {
            throw new NoHayRecursoDisponibleError();

        }
        this.entrega = entrega;
        this.recibe = recibe;
        this.banca = banca;
    }

    @Override
    public void aplicarSobre(Jugador receptor) {
        receptor.descartarTipo(entrega);
        banca.entregarTipos(receptor, recibe);
    }
}
