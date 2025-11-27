package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Recurso.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Construcciones.*;
import edu.fiuba.algo3.modelo.ElementosTablero.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * JugadorTest
 */
public class JugadorTest {
    @Test
    public void test01CreoUnJugadorSinCartasNiConstrucciones() {
        Jugador jugador = new Jugador("Adrian");

        assertEquals(0, jugador.cantidadCartas());
        assertEquals(0, jugador.cantidadConstrucciones());

    }

    @Test
    public void test02UnaConstruccionGeneraUnCartaRecursoParaJugador() {
        Jugador jugador1 = new Jugador("Alberto");

        Hexagono hexMadera = new Hexagono(Terreno.BOSQUE, 6);
        Vertice vertice = new Vertice();
        vertice.asignarHexagonos(hexMadera);

        jugador1.construir(vertice, new Poblado());

        assertEquals(1, jugador1.cantidadConstrucciones());

    }

    @Test
    public void test03UnJugadorRecibeRecursosDeSuConstruccion() {

        Jugador jugador = new Jugador("Julia");

        int dado = 6;
        Hexagono hexMadera = new Hexagono(Terreno.BOSQUE, 6);
        Vertice vertice = new Vertice();
        vertice.asignarHexagonos(hexMadera);

        jugador.construir(vertice, new Poblado());

        for (int i = 0; i < 10; i++) {
            jugador.generarSegunDado(dado);
        }
        assertEquals(10, jugador.cantidadCartas());

    }


    /*
        Verificar que si un jugador tiene más de 7 cartas, descarte correctamente la mitad,
        redondeando hacia abajo, al lanzar un 7.
        [1,2,3,4,5,6,7,8]
     */

    @Test
    public void test03(){

        //Arrange
        Jugador jugador = new Jugador("Ale");
        List<Recurso> p = Arrays.asList(new Madera(), new Ladrillo(),
                                            new Lana(), new Grano(), new Lana(),
                                           new Ladrillo(), new Grano());

        //Act
        List<Recurso> a = jugador.validarCartas(7);

        //Assert
        Assertions.assertNotEquals(p,a);


    }



}
