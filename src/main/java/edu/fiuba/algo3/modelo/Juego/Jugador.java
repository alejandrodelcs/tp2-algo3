package edu.fiuba.algo3.modelo.Juego;

import edu.fiuba.algo3.modelo.Construccion.Construccion;
import edu.fiuba.algo3.modelo.Material.Costo;
import edu.fiuba.algo3.modelo.Material.TipoRecurso;
import javafx.scene.paint.Material;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private final String nombre;
    private final Inventario inventario;

    private final List<Construccion> construcciones;


    public Jugador(String nombre) {
        this.nombre = nombre;
        this.inventario = new Inventario();
        this.construcciones = new ArrayList<>();
    }


    public void recibirRecurso(TipoRecurso tipo, int cantidad) {
        this.inventario.agregarRecurso(tipo, cantidad);
    }


    public boolean puedePagar(Costo costo) {
        return this.inventario.tieneSuficiente(costo);
    }


    public void pagar(Costo costo) {
        this.inventario.consumirRecursos(costo);
    }


    public void agregarConstruccion(Construccion nuevaConstruccion) {
        this.construcciones.add(nuevaConstruccion);
    }


    public int calcularPuntosDeVictoria() {
        int puntos = 0;


        for (Construccion c : this.construcciones) {
            puntos += c.getPuntosDeVictoria();
        }

        // 2. (Futuro) Suma puntos de Cartas de PV
        // for (CartaDesarrollo carta : this.cartas) {
        //     puntos += carta.getPuntosDeVictoria();
        // }

        // 3. (Futuro) Suma puntos de Bonificaciones (Gran Caballería, etc.)
        // ...

        return puntos;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Inventario getInventario() {
        return this.inventario;
    }
}
