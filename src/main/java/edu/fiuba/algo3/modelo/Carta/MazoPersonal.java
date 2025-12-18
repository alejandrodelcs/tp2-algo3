package edu.fiuba.algo3.modelo.Carta;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.fiuba.algo3.modelo.Excepciones.NoTieneCartaError;

/**
 * MazoPersonal
 */
public class MazoPersonal {
    private List<Carta> cartas;

    public MazoPersonal() {
        this.cartas = new ArrayList<>();

    }

    public void agregar(CartaDesarrollo carta) {
        this.cartas.add((Carta) carta);
    }

    public int cantidadDeTipo(CartaDesarrollo tipo) {
        int cantidad = 0;

        for (Carta carta : this.cartas) {
            if (carta.getClass().equals(tipo.getClass())) {
                cantidad++;
            }
        }

        return cantidad;
    }

    public int total() {
        int total = 0;
        for (Carta carta : cartas) {
            total++;

        }
        return total;
    }

    public void consumir(Carta tipo) {
        Iterator<Carta> it = this.cartas.iterator();

        while (it.hasNext()) {
            Carta carta = it.next();
            System.out.println(carta.toString() + " " + carta.estaDisponible());
            if (carta.esDelMismoTipoQue(tipo) && carta.estaDisponible()) {
                it.remove();
                return;

            }
        }
        throw new NoTieneCartaError();

    }

    public void deshabilitar() {
        for (Carta carta : cartas) {
            carta.deshabilitar();

        }

    }

    public void habilitar() {
        for (Carta carta : cartas) {
            carta.habilitar();

        }
    }

}
