package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Material.*;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

//Verificar que la Regla de Distancia se aplique al colocar poblados iniciales.

public class ReglaDeDistancia {

    @Test
    public void test01ElJugadorRecibe1DeMadera(){
        //Arrange
        Madera material = new Madera();
        int valorEsperado = 1;

        //Act

        int valorObtenido = material.obtenerRecurso();

        //Assertion

        assertEquals(valorEsperado,valorObtenido);
    }
}
