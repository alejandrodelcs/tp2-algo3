package edu.fiuba.algo3.modelo.Comercio;

import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.List;

public class ComercioInterior implements Comercio {
    private final Jugador oferente;
    private final List<Class<? extends Recurso>> entrega;
    private final List<Class<? extends Recurso>> recibe;

    public ComercioInterior(List<Class<? extends Recurso>> entrega,
                            List<Class<? extends Recurso>> recibe,
                            Jugador oferente) {
        this.entrega = entrega;
        this.recibe = recibe;
        this.oferente = oferente;
    }

    @Override
    public void aplicarSobre(Jugador receptor) {
        receptor.entregarTipos(oferente, recibe);
        oferente.entregarTipos(receptor, entrega);
    }
}
