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

    public int numero() {
        return this.numero;
    }

    public boolean tieneNunero(int unNumero) {
        return this.numero == unNumero;
    }
}
