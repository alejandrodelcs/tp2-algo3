package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Recurso.*;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

public class RecursoTest {
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
        Madera recurso = new Madera();
        int valorEsperado = 1;

        //Act

        int valorObtenido = recurso.obtenerRecurso();

        //Assertion

        assertEquals(valorEsperado,valorObtenido);
    }

    @Test
    public void test01ElJugadorRecibe1DeLadrillo(){
        //Arrange
        Ladrillo recurso = new Ladrillo();
        int valorEsperado = 1;

        //Act

        int valorObtenido = recurso.obtenerRecurso();

        //Assertion

        assertEquals(valorEsperado,valorObtenido);
    }


    @Test
    public void test01ElJugadorRecibe1DeLana(){
        //Arrange
        Lana recurso = new Lana();
        int valorEsperado = 1;

        //Act

        int valorObtenido = recurso.obtenerRecurso();

        //Assertion

        assertEquals(valorEsperado,valorObtenido);
    }


    @Test
    public void test01ElJugadorRecibe1DeGrano(){
        //Arrange
        Grano recurso = new Grano();
        int valorEsperado = 1;

        //Act

        int valorObtenido = recurso.obtenerRecurso();

        //Assertion

        assertEquals(valorEsperado,valorObtenido);
    }


    @Test
    public void test01ElJugadorRecibe1Mineral(){
        //Arrange
        Recurso recurso = new Mineral();
        int valorEsperado = 1;

        //Act

        int valorObtenido = recurso.obtenerRecurso();

        //Assertion

        assertEquals(valorEsperado,valorObtenido);
    }


}
