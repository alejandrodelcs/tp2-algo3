package edu.fiuba.algo3.modelo.Construcciones;

/**
 * ConstruccionNula
 */
public class ConstruccionNula extends Construccion {
    public int getPuntosDeVictoria() {
        return 0;
    }

    @Override
    public boolean esNula() {
        return true;
    }

}
