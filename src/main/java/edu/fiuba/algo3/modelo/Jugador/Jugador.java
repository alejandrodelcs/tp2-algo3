package edu.fiuba.algo3.modelo.Jugador;
import java.util.ArrayList;
import java.util.List;

import edu.fiuba.algo3.modelo.Carta.CartaDesarrollo;
import edu.fiuba.algo3.modelo.Construccion.*;
import edu.fiuba.algo3.modelo.Carta.MazoDesarrollo;
import edu.fiuba.algo3.modelo.Recurso.*;
import edu.fiuba.algo3.modelo.ReglaConstruccion.ReglaAdyacencia;
import edu.fiuba.algo3.modelo.ReglaConstruccion.ReglaConstruccion;
import edu.fiuba.algo3.modelo.ReglaConstruccion.ReglaDistancia;
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
        CartaDesarrollo carta = mazo.entregarCarta();
        carta.pagarCon(inventario);
        cartasDesarrollo.add(carta);
    }

    public void serRobadoPor(Jugador ladron){
        Recurso recurso = this.inventario.robarUno();
        if(recurso != null){
            ladron.recibirRecurso(recurso);
        }
    }

/*    public void usarCartaDesarrollo(int indice) {
        if (indice < 0 || indice >= this.cartasDesarrollo.size()) {
            throw new CartaNoDisponibleException("");
        }

        CartaDesarrollo carta = this.cartasDesarrollo.get(indice);

        carta.activar(this);

        if (carta.esDeUnSoloUso()) {
            this.cartasDesarrollo.remove(indice);
        }
    }*/
/*
    public void pasarTurno() {
        for (CartaDesarrollo carta : this.cartasDesarrollo) {
            carta.pasarTurno();
        }

        this.puedeMoverLadron = false;
    }*/

    public void sumarPuntoVictoria() {
        this.puntosVictoria++;
    }

   public void habilitarMovimientoLadron() {
        this.puedeMoverLadron = true;
    }

    public int cantidadCartasDesarrollo() {
        return this.cartasDesarrollo.size();
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


    // no debería hacerlo jugador sino el hexagono tal vez
    public void generarRecursosPorConstrucciones(int dado){
        for (Construccion c: construcciones) {
            inventario.agregarTodos(c.producirSegun(dado));
        }
    }


    public void descartarMitadSiCorresponde() {
        this.inventario.descartarMitadSiCorresponde();
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

    void entregarTipos(Jugador otroJugador, List<Class<? extends Recurso>> solicitud) {

        for (Class<? extends Recurso> tipo : solicitud) {

            Recurso recurso = inventario.remover(tipo);
            otroJugador.recibirRecurso(recurso);
        }

    }

    public void aceptarComercio(InteraccionJugador interaccion) {
        interaccion.aplicarSobre(this);
    }

    public void comerciarCon(Jugador otro,  List<Class<? extends Recurso>> entrega,
                             List<Class<? extends Recurso>> recibe){
        InteraccionComercio i = new InteraccionComercio(entrega, recibe, this);
        otro.aceptarComercio(i);
    }


    public ReglaConstruccion reglaDistancia() {
        return new ReglaDistancia(construcciones);
    }

    public ReglaConstruccion reglaAdyacencia() {
        return new ReglaAdyacencia(construcciones);
    }

    public void usarCarta(CartaDesarrollo cartaDesarrollo) {
    }

    public void pasarTurno() {
    }
}
