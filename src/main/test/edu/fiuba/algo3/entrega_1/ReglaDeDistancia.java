package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Construccion.Poblado;
import edu.fiuba.algo3.modelo.Juego.Jugador;
import edu.fiuba.algo3.modelo.Juego.Terreno.Cruce;
import org.junit.Test;

import static junit.framework.Assert.*;

//Verificar  que la Regla de Distancia se aplique al colocar poblados iniciales.

public class ReglaDeDistancia {

    @Test
    public void test01NoSePuedeConstruirEnUnCruceYaOcupado() {
        Jugador jugador = new Jugador("Rojo");
        Cruce cruce = new Cruce(0);
        Poblado poblado = new Poblado(jugador);

        cruce.setConstruccion(poblado);

        assertFalse(cruce.esValidoParaConstruir());
    }

    @Test
    public void test02NoSePuedeConstruirEnUnCruceVecinoAOtroOcupado() {
        Jugador jugador = new Jugador("Rojo");
        Poblado poblado = new Poblado(jugador);

        Cruce cruceA = new Cruce(0);
        Cruce cruceB = new Cruce(1);
        Cruce cruceC = new Cruce(2);

        cruceA.agregarVecino(cruceB);
        cruceB.agregarVecino(cruceA);

        cruceB.agregarVecino(cruceC);
        cruceC.agregarVecino(cruceB);

        cruceA.setConstruccion(poblado);

        assertFalse(cruceB.esValidoParaConstruir());


        assertTrue(cruceC.esValidoParaConstruir());
    }
}
