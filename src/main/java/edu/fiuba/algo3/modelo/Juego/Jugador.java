package edu.fiuba.algo3.modelo.Juego;
import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.CartaDesarrollo;
import edu.fiuba.algo3.modelo.Construcciones.*;
import edu.fiuba.algo3.modelo.Dado.AccionDado;
import edu.fiuba.algo3.modelo.Dado.Dado;
import edu.fiuba.algo3.modelo.Excepciones.CartaNoDisponibleException;
import edu.fiuba.algo3.modelo.Inventario;
import edu.fiuba.algo3.modelo.MazoDesarrollo;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.ReglaConstruccion.ReglaAdyacencia;
import edu.fiuba.algo3.modelo.ReglaConstruccion.ReglaConstruccion;
import edu.fiuba.algo3.modelo.ReglaConstruccion.ReglaDistancia;
import edu.fiuba.algo3.modelo.Tablero.Arista;
import edu.fiuba.algo3.modelo.Tablero.Vertice;

/**
 * Jugador
 */
public class Jugador {

    private List<CartaDesarrollo> cartasDesarrollo;
    private final ArrayList<Construccion> construcciones;
    private String nombre;
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

    public void mejorarConstruccion(Vertice vertice, Construccion nueva) {
        inventario.descontarPara(nueva);
        vertice.mejorarA(nueva);
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
            this.descartarMitadSiCorresponde();
        }

        for (Construccion c: this.construcciones) {

            ArrayList<Recurso> recursos = c.generarSegunVertice(dado);
            this.inventario.agregarTodos(recursos);

        }
    }

    public void accionSegunDado(Dado dado) {
        AccionDado accion = dado.lanzar();
        accion.aplicar(this);

    }


    public void generarRecursosPorConstrucciones(int dado){
        for (Construccion c: this.construcciones) {
            this.inventario.agregarTodos(c.generarSegunVertice(dado));

        }
    }


    public void descartarMitadSiCorresponde() {
        if (this.inventario.excedeLimite()) {
            this.inventario.descartarMitad();
        }
    }


    public boolean esAdyacenteA(Arista nueva) {
        return construcciones.isEmpty() ||
                construcciones.stream().anyMatch(c -> c.esAdyacenteA(nueva));
    }


    public void construir(Construible construible, Construccion construccion, Object... ubicaciones) {
        construible.construir(this, construccion, ubicaciones);
    }

    public void agregarConstruccion(Construccion construccion) {
        this.construcciones.add(construccion);
    }

    public void descontarPara(Construccion construccion) {
        this.inventario.descontarPara(construccion);
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


    public ReglaConstruccion reglaDistancia() {
        return new ReglaDistancia(construcciones);
    }

    public ReglaConstruccion reglaAdyacencia() {
        return new ReglaAdyacencia(construcciones);
    }
}
