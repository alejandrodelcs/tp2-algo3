package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Material.*;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

public class MaterialTest {
    /*
    Cada hexágono produce un tipo de Recurso:
     Madera (bosque),
    Ladrillo (colina)
    Lana (pastizal)
    Grano (campo)
    Mineral
    (montaña).
     */
    @Test
    public void test01ElJugadorRecibe1DeMadera(){
        //Arrange
        Madera material = new Madera();
        int valorEsperado = 1;

        //Act

        int valorObtenido = material.recibir();

        //Assertion

        assertEquals(valorEsperado,valorObtenido);
    }

    @Test
    public void test01ElJugadorRecibe1DeLadrillo(){
        //Arrange
        Ladrillo material = new Ladrillo();
        int valorEsperado = 1;

        //Act

        int valorObtenido = material.recibir();

        //Assertion

        assertEquals(valorEsperado,valorObtenido);
    }


    @Test
    public void test01ElJugadorRecibe1DeLana(){
        //Arrange
        Lana material = new Lana();
        int valorEsperado = 1;

        //Act

        int valorObtenido = material.recibir();

        //Assertion

        assertEquals(valorEsperado,valorObtenido);
    }


    @Test
    public void test01ElJugadorRecibe1DeGrano(){
        //Arrange
        Grano material = new Grano();
        int valorEsperado = 1;

        //Act

        int valorObtenido = material.recibir();

        //Assertion

        assertEquals(valorEsperado,valorObtenido);
    }


    @Test
    public void test01ElJugadorRecibe1Mineral(){
        //Arrange
        Material material = new Mineral();
        int valorEsperado = 1;

        //Act

        int valorObtenido = material.recibir();

        //Assertion

        assertEquals(valorEsperado,valorObtenido);
    }


}
