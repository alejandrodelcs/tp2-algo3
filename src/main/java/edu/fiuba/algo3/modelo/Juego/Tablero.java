package edu.fiuba.algo3.modelo.Juego;


import edu.fiuba.algo3.modelo.Construccion.Carretera;
import edu.fiuba.algo3.modelo.Construccion.Ciudad;
import edu.fiuba.algo3.modelo.Construccion.Poblado;
import edu.fiuba.algo3.modelo.Juego.Terreno.*;
import edu.fiuba.algo3.modelo.Recurso.Costo;


import java.util.List;
import java.util.Map;

public class Tablero {
    private final Map<Integer, Cruce> cruces;
    private final List<Hexagono> hexagonos;
    //private final List<Arista> aristas;
    private final Hexagono desierto;


    public Tablero(Map<Integer, Cruce> cruces, List<Hexagono> hexagonos, Hexagono desierto) {
        this.cruces = cruces;
        this.hexagonos = hexagonos;
        this.desierto = desierto;
        //this.aristas = aristas;
    }

    public void distribuirProduccion(int numeroDado) {

        if (numeroDado == 7) return;

        for (Hexagono hex : this.hexagonos) {

            hex.generarProduccionSiCorresponde(numeroDado);
        }
    }

    public void construirCiudad(Jugador jugador, int cruceId) {

        Cruce cruce = this.cruces.get(cruceId);
        if (cruce == null) {
            throw new IllegalArgumentException("El ID del cruce no es valido.");
        }


        if (!cruce.esValidoParaConstruir()) {
            throw new RuntimeException("No se puede construir: Regla de distancia violada.");
        }


        Costo costoCiudad = Costo.CIUDAD();
        if (!jugador.puedePagar(costoCiudad)) {
            throw new RuntimeException("No tienes recursos suficientes para una Ciudad.");
        }


        jugador.pagar(costoCiudad);
        Ciudad nuevaCiudad = new Ciudad(jugador);
        cruce.setConstruccion(nuevaCiudad);


        jugador.agregarConstruccion(nuevaCiudad);
    }

    public void construirPoblado(Jugador jugador, int cruceId) {

        Cruce cruce = this.cruces.get(cruceId);
        if (cruce == null) {
            throw new IllegalArgumentException("El ID del cruce no es válido.");
        }


        if (!cruce.esValidoParaConstruir()) {
            throw new RuntimeException("No se puede construir: Regla de distancia violada.");
        }


        Costo costoPoblado = Costo.POBLADO();
        if (!jugador.puedePagar(costoPoblado)) {
            throw new RuntimeException("No tienes recursos suficientes para un Poblado.");
        }


        jugador.pagar(costoPoblado);
        Poblado nuevoPoblado = new Poblado(jugador);
        cruce.setConstruccion(nuevoPoblado);


        jugador.agregarConstruccion(nuevoPoblado);
    }

    public void construirCarretera(Jugador jugador, int cruceId) {

        Cruce cruce = this.cruces.get(cruceId);
        if (cruce == null) {
            throw new IllegalArgumentException("El ID del cruce no es válido.");
        }


        if (!cruce.esValidoParaConstruir()) {
            throw new RuntimeException("No se puede construir: Regla de distancia violada.");
        }


        Costo costoCarretera = Costo.CARRETERA();
        if (!jugador.puedePagar(costoCarretera)) {
            throw new RuntimeException("No tienes recursos suficientes para una Carretera.");
        }


        jugador.pagar(costoCarretera);
        Carretera nuevaCarretera = new Carretera(jugador);
        cruce.setConstruccion(nuevaCarretera);


        jugador.agregarConstruccion(nuevaCarretera);
    }

}
