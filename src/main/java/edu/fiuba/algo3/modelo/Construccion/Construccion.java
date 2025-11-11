package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Juego.Inventario;
import edu.fiuba.algo3.modelo.Juego.Jugador;
import edu.fiuba.algo3.modelo.Material.*;
import javafx.scene.paint.Material;

public abstract class Construccion {
    Costo costo;
    Jugador dueño;

    public Construccion(Costo costo, Jugador dueño) {
        this.costo = costo;
        this.dueño = dueño;
    }

    public abstract void reclamarProduccion(TipoRecurso material);

    public abstract int getPuntosDeVictoria();
}
