package edu.fiuba.algo3.modelo.Comercio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.fiuba.algo3.modelo.Juego;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

/**
 * SecionDeComercio
 */
public class SesionDeComercio {

    private final ModoDeComercio modo;
    private Map<Class<? extends Recurso>, Integer> oferta;
    private Map<Class<? extends Recurso>, Integer> demanda;

    public SesionDeComercio(ModoDeComercio modo) {
        this.modo = modo;
        this.oferta = new HashMap<>();
        this.demanda = new HashMap<>();
    }

    public void setOferta(Map<Class<? extends Recurso>, Integer> oferta) {
        this.oferta = oferta;
    }

    public void setDemanda(Map<Class<? extends Recurso>, Integer> demanda) {
        this.demanda = demanda;
    }

    public void ejecutar(Juego juego, Jugador jugador) {
        modo.ejecutar(
                juego,
                jugador,
                expandir(oferta),
                expandir(demanda));
        oferta.clear();
        demanda.clear();
    }

    private List<Class<? extends Recurso>> expandir(
            Map<Class<? extends Recurso>, Integer> paquete) {

        List<Class<? extends Recurso>> resultado = new ArrayList<>();
        paquete.forEach((tipo, cantidad) -> {
            for (int i = 0; i < cantidad; i++) {
                resultado.add(tipo);
            }
        });
        return resultado;
    }
}
