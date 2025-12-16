package edu.fiuba.algo3.modelo.Comercio;

import java.util.List;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

/**
 * InteraccionComercioBanca
 */
public class ComercioBanca implements Comercio {

    private final Banca banca;
    private final List<Class<? extends Recurso>> entrega;
    private final List<Class<? extends Recurso>> recibe;

    public ComercioBanca(List<Class<? extends Recurso>> entrega,
            List<Class<? extends Recurso>> recibe, ReglaComercio reglaComercio,
            Banca banca) {
        this.entrega = entrega;
        this.recibe = recibe;
        this.banca = banca;

        reglaComercio.validar(entrega, recibe);
    }

    @Override
    public void aplicarSobre(Jugador receptor) {
        receptor.descartarTipo(entrega);
        banca.entregarTipos(receptor, recibe);
    }
}
