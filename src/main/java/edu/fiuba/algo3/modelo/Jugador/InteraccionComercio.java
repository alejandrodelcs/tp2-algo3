package edu.fiuba.algo3.modelo.Jugador;

import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.List;

public class InteraccionComercio implements InteraccionJugador{
    private Jugador  oferente;
    private List<Class<? extends Recurso>> entrega;
    private List<Class<? extends Recurso>> recibe;

    public InteraccionComercio(List<Class<? extends Recurso>> entrega,
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
