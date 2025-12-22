package edu.fiuba.algo3.modelo.Jugador;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import edu.fiuba.algo3.modelo.Carta.Carta;
import edu.fiuba.algo3.modelo.Carta.CartaDesarrollo;
import edu.fiuba.algo3.modelo.Carta.MazoPersonal;
import edu.fiuba.algo3.modelo.Comercio.Comercio;
import edu.fiuba.algo3.modelo.Construccion.*;
import edu.fiuba.algo3.modelo.Costo.Costo;
import edu.fiuba.algo3.modelo.Excepciones.CartaNoDisponibleException;
import edu.fiuba.algo3.modelo.Excepciones.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import edu.fiuba.algo3.modelo.Tablero.Vertice;

/**
 * Jugador
 */
public class Jugador {

    private final MazoPersonal cartasDesarrollo;
    private final ArrayList<Construccion> construcciones;
    private final String nombre;
    private int puntosVictoria;
    private Inventario inventario;
    private int caballerosJugados;
    private boolean puedeJugarCartaDesarrollo;

    public Jugador(String nombre, Inventario inventario) {
        this.nombre = nombre;
        this.construcciones = new ArrayList<>();
        this.inventario = inventario;
        this.cartasDesarrollo = new MazoPersonal();
        this.caballerosJugados = 0;
        this.puntosVictoria = 0;

    }

    public int cantidadCartas() {
        return this.inventario.total();
    }

    public int cantidadConstrucciones() {
        return this.construcciones.size();
    }

    public void mejorarConstruccionUbicadoEn(Vertice vertice) {
        vertice.mejorar(this);
        this.sumarPuntoVictoria();
    }

    public void compraCartaDesarrollo(CartaDesarrollo carta) {
        cartasDesarrollo.agregar(carta);
    }

    public void sumarPuntoVictoria() {
        this.puntosVictoria++;
    }

    public void sumarPuntoVictoria(int puntos) {
        this.puntosVictoria += puntos;
    }

    public int cantidadCartasDesarrollo() {
        return this.cartasDesarrollo.total();
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

    public void descartarMitadSiCorresponde() {
        this.inventario.descartarMitadSiCorresponde();
    }

    public void construir(Construccion construccion, Object... ubicaciones) {

        construccion.asignarJugador(this);
        construccion.construir(ubicaciones);
        this.construcciones.add(construccion);
    }

    public void descontarCon(Costo costo) {
        if (costo.puedePagar(this.inventario)) {
            costo.aplicar(this.inventario);
        } else {
            throw new RecursosInsuficientesException("no hay recursos");
        }
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

    public void descartarTipo(List<Class<? extends Recurso>> descarte) {

        for (Class<? extends Recurso> class1 : descarte) {

            this.inventario.consumir(class1);
        }

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
        for (int i = 0; i < total; i++) {
            this.inventario.agregar(tipo);
        }
    }

    public int recolectarRecursosDelTipo(Recurso tipo) {
        return inventario.sacarTodos(tipo);
    }

    public int getPuntosVictoria() {
        int cantidad = puntosVictoria;

        for (Construccion construccion : this.construcciones) {
            cantidad += construccion.getPuntosDeVictoria();

        }
        return cantidad;
    }

    public void restarPuntosVictoria(int puntos) {
        this.puntosVictoria -= puntos;
    }

    public void finalizarTurno() {
        this.puedeJugarCartaDesarrollo = true;
    }

    public void jugarCartaDesarrollo(CartaDesarrollo carta, Tablero tablero, Object... args) {

        Carta c = (Carta) carta;

        if (0 == cartasDesarrollo.cantidadDeTipo(carta)) {
            throw new CartaNoDisponibleException("No tiene esta carta");
        }

        this.cartasDesarrollo.consumir(c);
        carta.jugar(this, tablero, args);

        this.cartasDesarrollo.deshabilitar();
    }

    public void habilitarCartasDesarrollo() {
        this.cartasDesarrollo.habilitar();
    }

    public boolean puedeEntregar(List<Class<? extends Recurso>> solicitud) {

        Map<Class<? extends Recurso>, Long> requeridos = solicitud.stream()
                .collect(Collectors.groupingBy(
                        tipo -> tipo,
                        Collectors.counting()));

        for (var entry : requeridos.entrySet()) {
            if (inventario.cantidadDeTipo(entry.getKey()) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    public int cantidadCartasTipo(Carta tipo) {
        return this.cartasDesarrollo.cantidadDeTipo(tipo);

    }

    public void entregaInicial(Vertice vertice) {
        this.inventario = vertice.entregarRecursosIniciales();

    }
}
