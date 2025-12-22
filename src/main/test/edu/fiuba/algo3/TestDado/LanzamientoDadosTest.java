package edu.fiuba.algo3.TestDado;

import edu.fiuba.algo3.modelo.Dado.Dado;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LanzamientoDadosTest {
    @Test
    public void test01LaSumaDeLosDadosSiempreEstaEntre2y12(){
        Dado dado = new Dado();

        for (int i=0; i < 100; i++){
            int resultado = dado.lanzar();
            assertTrue(resultado >= 2 && resultado <= 12);
        }
    }
}
