package edu.fiuba.algo3.modelo.Jugador;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Carta.CartaDesarrollo;
import edu.fiuba.algo3.modelo.Comercio.Comercio;
import edu.fiuba.algo3.modelo.Construccion.*;
import edu.fiuba.algo3.modelo.Costo.Costo;
import edu.fiuba.algo3.modelo.Excepciones.CartaNoDisponibleException;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Construccion.ReglaAdyacencia;
import edu.fiuba.algo3.modelo.Construccion.ReglaConstruccion;
import edu.fiuba.algo3.modelo.Construccion.ReglaDistancia;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Vertice;

/**
 * Jugador
 */
public class Jugador {

    private final List<CartaDesarrollo> cartasDesarrollo;
    private final ArrayList<Construccion> construcciones;
    private final String nombre;
    private int puntosVictoria;
    private final Inventario inventario;
    private String avatar;
    private int caballerosJugados;
    private boolean puedeJugarCartaDesarrollo;

    public Jugador(String nombre, Inventario inventario) {
        this.nombre = nombre;
        this.construcciones = new ArrayList<>();
        this.inventario = inventario;
        this.cartasDesarrollo = new ArrayList<>();
        this.caballerosJugados = 0;


    }

    public int cantidadCartas() {
        return this.inventario.total();
    }

    public int cantidadConstrucciones() {
        return this.construcciones.size();
    }

    public void mejorarConstruccion(Vertice vertice, Construccion nueva) {
        inventario.descontarPara(nueva);
        vertice.mejorarA(nueva);
        this.sumarPuntoVictoria();
    }

    public void compraCartaDesarrollo(CartaDesarrollo carta) {
        cartasDesarrollo.add(carta);
    }


    public void sumarPuntoVictoria() {
        this.puntosVictoria++;
    }

    public void sumarPuntoVictoria(int puntos) {
        this.puntosVictoria+=puntos;
    }

    public int cantidadCartasDesarrollo() {
        return this.cartasDesarrollo.size();
    }

    public void recibirRecurso(Recurso recurso) {
        if (recurso != null) {
            inventario.agregar(recurso);
        }
    }

    public void entregarRecursoA(Jugador jugadorQueRoba) {
        Recurso recurso = this.inventario.robarUno();
        if (recurso != null) {
            jugadorQueRoba.recibirRecurso(recurso);
        }
    }

    // no debería hacerlo jugador sino el hexagono tal vez
    public void generarRecursosPorConstrucciones(int dado) {
        for (Construccion c : construcciones) {
            inventario.agregarTodos(c.producirSegun(dado));
        }
    }

    public void descartarMitadSiCorresponde() {
        this.inventario.descartarMitadSiCorresponde();
    }

    public void construir(Construible construible, Construccion construccion, Object... ubicaciones) {
        construible.construir(this,construccion, ubicaciones);
        this.construcciones.add(construccion);
    }


    public void descontarPara(Construccion construccion) {
        this.inventario.descontarPara(construccion);
    }

    public int consultarRecursos() {
        return this.inventario.total();
    }

    public void entregarTipos(Jugador otroJugador, List<Class<? extends Recurso>> solicitud) {

        for (Class<? extends Recurso> tipo : solicitud) {

            Recurso recurso = inventario.remover(tipo);
            otroJugador.recibirRecurso(recurso);
        }

    }

    public void aceptarComercio(Comercio interaccion) {
        interaccion.aplicarSobre(this);
    }


    public ReglaConstruccion reglaDistancia() {
        return new ReglaDistancia();
    }

    public ReglaConstruccion reglaAdyacencia() {
        return new ReglaAdyacencia(this);
    }


    public void descartarTipo(List<Class<? extends Recurso>> descarte) {

        for (Class<? extends Recurso> class1 : descarte) {

            this.inventario.consumir(class1);
        }

    }

    public void setAvatar(String url) {
        this.avatar = url;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public int cantidadDe(Class<? extends Recurso> recurso) {
        return this.inventario.cantidadDeTipo(recurso);

    }

    public String getNombre() {
        return nombre;
    }


    public boolean puedePagar(Costo costo) {
        return costo.puedePagar(this.inventario);
    }

    public void pagar(Costo costo) {
        costo.pagar(this.inventario);
    }

    public void registrarCaballeroJugado() {
        this.caballerosJugados++;
    }

    public boolean superaEnCaballerosA(Jugador otro) {
        return this.caballerosJugados > otro.caballerosJugados;
    }
    public boolean puedeReclamarGranCaballeria() {
        return caballerosJugados >= 3;
    }

    public void agregarRecursos(Recurso tipo, int total) {
        for(int i=0; i<total; i++) {
            this.inventario.agregar(tipo);
        }
    }

    public int recolectarRecursosDelTipo(Recurso tipo) {
        return inventario.sacarTodos(tipo);
    }

    public int getPuntosVictoria() {
        return puntosVictoria;
    }

    public void restarPuntosVictoria(int puntos) {
        this.caballerosJugados -= puntos;
    }

    public void finalizarTurno() {
        this.puedeJugarCartaDesarrollo = true;
    }


    public void jugarCartaDesarrollo(CartaDesarrollo carta, Tablero tablero, Object...args) {

        Carta c = (Carta) carta;

        if (!c.estaDisponible()) {
            throw new CartaNoDisponibleException("");
        }

        carta.jugar(this, tablero, args);

        if (c.esDeUnSoloUso()) {
            this.cartasDesarrollo.remove(carta);
        }
    }

    public void habilitarCartasDesarrollo() {
        for (CartaDesarrollo c : cartasDesarrollo) {
            if (c instanceof Carta) {
                ((Carta) c).habilitar();
            }
        }
    }
}
