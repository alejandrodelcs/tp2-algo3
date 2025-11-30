package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.Construcciones.*;
import edu.fiuba.algo3.modelo.ElementosTablero.*;
import edu.fiuba.algo3.modelo.Errores.CartaNoDisponibleException;
import edu.fiuba.algo3.modelo.Errores.CarreteraNoConectadaError;
import edu.fiuba.algo3.modelo.Recurso.*;

/**
 * Jugador
 */
public class Jugador {

    private List<CartaDesarrollo> cartasDesarrollo;
    private ArrayList<Construccion> construcciones;
    private final ArrayList<Construccion> construcciones;
    private String nombre;
    private Inventario inventario;
    private int puntosVictoria;
    private boolean puedeMoverLadron;
    private final Inventario inventario;

    public Jugador(String nombre, Inventario inventario) {
        this.nombre = nombre;
        this.construcciones = new ArrayList<>();
        this.inventario = inventario;
        this.cartasDesarrollo = new ArrayList<>();
    }

    public int cantidadCartas() {
        return this.inventario.total();
    }

    public int cantidadConstrucciones() {
        return this.construcciones.size();
    }

    public void mejorarConstruccion(Vertice vertice, Construccion nuevaConstruccion) {
        List<Recurso> costo = nuevaConstruccion.getCosto();

        this.inventario.gastar(costo);

        try {
            vertice.mejorar(nuevaConstruccion);

            this.construcciones.add(nuevaConstruccion);
            this.removerConstruccionVieja(vertice);

        } catch (Exception e) {
            this.inventario.agregar(costo);

            throw e;
        }
    }

    private void removerConstruccionVieja(Vertice vertice) {
        this.construcciones.removeIf(c -> c.estaEn(vertice));
    }

    public void comprarCartaDesarrollo(MazoDesarrollo mazo) {
        List<Recurso> costo = List.of(new Lana(), new Grano(), new Mineral());

        this.inventario.gastar(costo);

        CartaDesarrollo carta = mazo.sacarCarta();

        this.cartasDesarrollo.add(carta);
    }

    public void usarCartaDesarrollo(int indice) {
        if (indice < 0 || indice >= this.cartasDesarrollo.size()) {
            throw new CartaNoDisponibleException("");
        }

        CartaDesarrollo carta = this.cartasDesarrollo.get(indice);

        carta.activar(this);

        if (carta.esDeUnSoloUso()) {
            this.cartasDesarrollo.remove(indice);
        }
    }

    public void pasarTurno() {
        for (CartaDesarrollo carta : this.cartasDesarrollo) {
            carta.pasarTurno();
        }

        this.puedeMoverLadron = false;
    }

    public void sumarPuntoVictoria() {
        this.puntosVictoria++;
    }

    public void habilitarMovimientoLadron() {
        this.puedeMoverLadron = true;
    }

    public int cantidadCartasDesarrollo() {
        return this.cartasDesarrollo.size();
    }

    public void construir(Vertice vertice, Construccion construccion) {
        vertice.construir(construccion);
        this.construcciones.add(construccion);


        List<Recurso> costo = construccion.getCosto();

        this.inventario.gastar(costo);

        try {
            vertice.construir(construccion);
            this.construcciones.add(construccion);
        } catch (Exception e) {
            this.inventario.agregar(costo);
            throw e;
        }
    }


    public void robarA(Jugador victima) {
        if (victima != null && victima != this) {
            victima.entregarRecursoA(this);
        }
    }

    public void recibirRecurso(Recurso recurso) {
        if (recurso != null) {
            this.inventario.agregar(recurso);
        }
    }

    public void entregarRecursoA(Jugador ladron) {
        Recurso recurso = this.inventario.robarUno();

        if (recurso != null) {
            ladron.recibirRecurso(recurso);
        }
    }

    public void generarSegunDado(int dado) {
        if (dado == 7) {
            this.reducirALaMitadLosRecurosos();
            return;
        }

        for (Construccion construccion : this.construcciones) {

            ArrayList<Recurso> recursos = construccion.generarSegunVertice(dado);
            this.inventario.agregar(recursos);

        }
    }

    public void reducirALaMitadLosRecurosos() {
        if (this.inventario.excedeLimite()) {
            this.inventario.descartarMitad();
        }
    }


    private boolean esAdyacenteALaRed(Arista nueva) {

        if (construcciones.isEmpty()) return true;

    private boolean esAdyacenteA(Arista nueva) {
        return construcciones.isEmpty() ||
                construcciones.stream().anyMatch(c -> c.esAdyacenteA(nueva));
    }


    public void construirCarretera(Vertice inicio, Vertice fin, Carretera carretera) {
        Arista nueva = new Arista(inicio, fin);
        carretera.asignarArista(nueva);
        if (!esAdyacenteA(nueva)) {
            throw new CarreteraNoConectadaError();
        }
        inventario.descontarPara(carretera);
        nueva.colocarCarretera(carretera);
        this.construcciones.add(carretera);
    }




    public int consultarRecursos() {
        return this.inventario.total();
    }

    public boolean tieneEnInventario(List<Class<? extends Recurso>> solicitud) {

        for (Class<? extends Recurso> recurso : solicitud) {
            if (this.inventario.cantidadDeTipo(recurso) == 0) {
                return false;
            }

        }
        return true;
    }

    public void entregarTipos(Jugador otroJugador, List<Class<? extends Recurso>> solicitud) {

        for (Class<? extends Recurso> tipo : solicitud) {

            Recurso recurso = inventario.remover(tipo);
            otroJugador.recibirRecurso(recurso);
        }

    }

    public int cantidadDeRecursoTipo(Class<? extends Recurso> tipo) {
        return this.inventario.cantidadDeTipo(tipo);

    }

    public Jugador seleccionarVictima(List<Jugador> candidatas) {
        return candidatas.get(0);// ver como fx selecciona a la victima
    }


    public void construirAsentamiento(Vertice vertice, Construccion construccion) {
        vertice.construir(construccion);
        this.construcciones.add(construccion);
        inventario.descontarPara(construccion);
    }


    public int consultarRecursos() {
        return this.inventario.total();
    }

    public boolean tieneEnInventario(List<Class<? extends Recurso>> solicitud) {

        for (Class<? extends Recurso> recurso : solicitud) {
            if (this.inventario.cantidadDeTipo(recurso) == 0) {
                return false;
            }

        }
        return true;
    }

    public void entregarTipos(Jugador otroJugador, List<Class<? extends Recurso>> solicitud) {

        for (Class<? extends Recurso> tipo : solicitud) {

            Recurso recurso = inventario.remover(tipo);
            otroJugador.recibirRecurso(recurso);
        }

    }

    public int cantidadDeRecursoTipo(Class<? extends Recurso> tipo) {
        return this.inventario.cantidadDeTipo(tipo);

    }

    public Jugador seleccionarVictima(List<Jugador> candidatas) {
        return candidatas.get(0);// ver como fx selecciona a la victima
    }

}
