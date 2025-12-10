package edu.fiuba.algo3.modelo.Carta;

import edu.fiuba.algo3.modelo.Costo.Costo;
import edu.fiuba.algo3.modelo.Excepciones.MazoVacioException;
import edu.fiuba.algo3.modelo.Excepciones.NoHayRecursoDisponibleError;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Grano;
import edu.fiuba.algo3.modelo.Recurso.Lana;
import edu.fiuba.algo3.modelo.Recurso.Mineral;

import java.util.Collections;
import java.util.Stack;

public class MazoDesarrollo {

    private final Stack<CartaDesarrollo> cartas;
    private final Costo costo ;

    public MazoDesarrollo() {
        this.cartas = new Stack<>();
        this.costo = new Costo(Lana.class, Grano.class, Mineral.class);
        this.inicializarMazo();
    }
    /*
         14 Caballeros, 5 PV, 2 Descubrimiento, 2 Const Carretera, 2 Monopolio
     */
    private void inicializarMazo() {

        for (int i = 0; i < 14; i++)
            cartas.push(new CartaCaballero());

        for (int i = 0; i < 5; i++)
            cartas.push(new CartaPuntoVictoria());


        for (int i = 0; i < 2; i++)
            cartas.push(new CartaDescubrimiento());


        for (int i = 0; i < 2; i++)
            cartas.push(new CartaConstruccionCarreteras());

        for (int i = 0; i < 2; i++)
            cartas.push(new CartaMonopolio());

        Collections.shuffle(this.cartas);
    }

    public CartaDesarrollo entregarCarta() {
        if (this.cartas.isEmpty()) throw new MazoVacioException("El mazo esta vacio");
        return this.cartas.pop();
    }

    public CartaDesarrollo comprarCarta(Jugador jugador){
        if(!jugador.puedePagar(costo)){
            throw new NoHayRecursoDisponibleError();
        }
        jugador.pagar(costo);
        CartaDesarrollo carta = this.entregarCarta();
        jugador.compraCartaDesarrollo(carta);


        if(carta instanceof CartaPuntoVictoria){
            jugador.sumarPuntoVictoria();
        }

        return carta;
    }

}

