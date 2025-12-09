package edu.fiuba.algo3.modelo.Tablero;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import edu.fiuba.algo3.modelo.Excepciones.AccionNoPermitidaException;
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
    private ArrayList<Arista> aristas;
    private ArrayList<Hexagono> hexagonos;

    public Vertice() {
        this.construccion = new ConstruccionNula();
        this.aristas = new ArrayList<>();
        this.hexagonos = new ArrayList<>();
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

    public void agregarVictimaPotencial(List<Jugador> listaVictimas) {
        this.construccion.agregarPropietario(listaVictimas);
    }


    public void conectarArista(Arista arista) {
        this.aristas.add(arista);
    }

    public int puntoVictoria() {
        return this.construccion.getPuntosDeVictoria();
    }

    public boolean tieneConstruccion() {
        return !this.construccion.esNula();
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
            return null;  //Crear una excepcion y testear
        }

        for (Hexagono hex : this.hexagonos) {
            Recurso r = hex.obtenerRecursoBase();
            if (r != null) {
                recursos.add(r);
            }
        }

        return new Inventario(recursos.toArray(new Recurso[0]));
    }

    public void mejorarA(Construccion nueva){
        if(!this.tieneConstruccion()){
            throw new NoHayConstruccionParaMejorar();
        }
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
        return construccion.esPropietarioElJugador(j);
    }

    public boolean tieneCarreteraDel(Jugador j) {
        return aristas.stream()
                .anyMatch(a -> a.tieneCarreteraDel(j));
    }


    public Optional<Jugador> jugadorPropietario() {
        if (this.construccion == null) return Optional.empty();
        return construccion.propietario();
    }

}
