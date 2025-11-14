package edu.fiuba.algo3.modelo.elementos;

import java.util.Random;

/**
 * Ficha
 */
public class Ficha {
    private int numero;

    public Ficha() {
        this.numero = this.generarNumeroAleatorio();
    }

    public Ficha(int numero) {
        if (numero < 2 || numero > 12 || numero == 7) {
            throw new IllegalArgumentException("El numero de ficha debe estar entre 2-12 y no ser 7.");
        }
        this.numero = numero;
    }
    private int generarNumeroAleatorio() {

        Random numeroAleatorio = new Random();
        int numero;

        do {
            numero = numeroAleatorio.nextInt(11) + 2;
        } while (numero == 7);

        return numero;
    }

    public int getNumero() {
        return this.numero;
    }

    public boolean tieneNumero(int unNumero) {
        return this.numero == unNumero;
    }
}
