package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Dado.Dado;
import edu.fiuba.algo3.modelo.LanzamientoDados;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

public class LanzamientoDadosTest {
    @Test
    public void test01LaSumaDeLosDadosSiempreEstaEntre2y12(){
        Dado dado = new Dado();

        for (int i=0; i < 100; i++){
            int resultado = dado.tirarDado() + dado.tirarDado();
            assertTrue(resultado >= 2 && resultado <= 12);
        }
    }
}
