package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Errores.MazoVacioException;

import java.util.Collections;
import java.util.Stack;

public class MazoDesarrollo {

    private Stack<CartaDesarrollo> cartas;

    public MazoDesarrollo() {
        this.cartas = new Stack<>();
        this.inicializarMazo();
    }

    private void inicializarMazo() {
        for (int i = 0; i < 14; i++) cartas.push(new CartaCaballero());

        for (int i = 0; i < 5; i++) cartas.push(new CartaPuntoVictoria());

        Collections.shuffle(this.cartas);
    }

    public CartaDesarrollo sacarCarta() {
        if (this.cartas.isEmpty()) {
            throw new MazoVacioException("El mazo esta vacio");
        }
        return this.cartas.pop();
    }

    public int cantidadRestante() {
        return this.cartas.size();
    }
}

