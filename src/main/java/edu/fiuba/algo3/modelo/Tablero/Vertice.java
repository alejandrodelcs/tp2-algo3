package edu.fiuba.algo3.modelo.Tablero;

import java.util.*;

import edu.fiuba.algo3.modelo.Excepciones.NoHayConstruccionParaMejorar;
import edu.fiuba.algo3.modelo.Construccion.*;
import edu.fiuba.algo3.modelo.Jugador.Inventario;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.*;

/**
 * Vertice
 */
public class Vertice {
    private Construccion construccion;
    private final ArrayList<Arista> aristas;
    private final ArrayList<Hexagono> hexagonos;

    public Vertice() {
        this.aristas = new ArrayList<>();
        this.hexagonos = new ArrayList<>();
    }

    public boolean tienePoblado() {
        return this.construccion instanceof Poblado;
    }

    public boolean esDuenio(Jugador jugador) {
        return construccion.esPropietarioElJugador(jugador);
    }

    public void construir(Construccion construccion) {
        this.construccion = construccion;
        construccion.asignarVertice(this);
    }

    public void agregarHexagono(Hexagono hexagono) {
        if (!hexagonos.contains(hexagono)) {
            hexagonos.add(hexagono);
            hexagono.agregarVertice(this);
        }
    }

    public void conectarArista(Arista arista) {
        this.aristas.add(arista);
    }

    public int puntoVictoria() {
        return this.construccion.getPuntosDeVictoria();
    }

    public boolean tieneConstruccion() {
        return (this.construccion != null);
    }

    public ArrayList<Recurso> generarRecurso(int dado, int cantidad) {
        ArrayList<Recurso> resultado = new ArrayList<>();

        for (Hexagono h : hexagonos) {
            resultado.addAll(h.generarRecursos(dado, cantidad));
        }

        return resultado;
    }

    public Inventario entregarRecursosIniciales() {
        List<Recurso> recursos = new ArrayList<>();

        if (!this.tieneConstruccion()) {
            return null; // Crear una excepcion y testear
        }

        for (Hexagono hex : this.hexagonos) {
            Recurso r = hex.obtenerRecursoBase();
            if (r != null) {
                recursos.add(r);
            }
        }

        return new Inventario(recursos.toArray(new Recurso[0]));
    }

    public void mejorar(Jugador jugador) {
        if (!this.tieneConstruccion()) {
            throw new NoHayConstruccionParaMejorar("No hay construccion para mejorar.");
        }

        Construccion nueva = construccion.mejorar();
        nueva.cobrarA(jugador);
        nueva.asignarJugador(jugador);
        this.construccion = nueva;
    }

    public List<Vertice> verticesVecinos() {
        List<Vertice> vecinos = new ArrayList<>();
        for (Arista a : aristas) {
            vecinos.add(a.getOtroVertice(this));
        }
        return vecinos;
    }

    public boolean tieneConstruccionDel(Jugador j) {
        return construccion != null && construccion.esPropietarioElJugador(j);
    }

    public boolean tieneCarreteraDel(Jugador j) {
        return aristas.stream()
                .anyMatch(a -> a.tieneCarreteraDel(j));
    }

    public Optional<Jugador> jugadorPropietario() {
        if (this.construccion == null)
            return Optional.empty();
        return construccion.propietario();
    }

    public Jugador getPropietario() {
        return construccion.getPropietario();
    }

    public Collection<? extends Arista> getAristas() {
        return aristas;
    }

    public Construccion getConstruccion() {
        return construccion;
    }

    public void producir(Recurso recurso) {
        if (!tieneConstruccion())
            return;
        construccion.producirSegun(recurso);
    }
}
