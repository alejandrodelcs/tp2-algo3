package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Construcciones.Carretera;
import edu.fiuba.algo3.modelo.ElementosTablero.Vertice;
import edu.fiuba.algo3.modelo.Inventario;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso.Ladrillo;
import edu.fiuba.algo3.modelo.Recurso.Madera;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

public class TestEntrega2 {

    @Test
    public void test01VerificarElConsumoDeRecursosYLaCorrectaColocacionDeUnaCarretera(){

        Jugador jugador = new Jugador("Ale", new Inventario(new Madera(), new Ladrillo()));

        jugador.contruirCarretera(new Vertice(), new Vertice(), new Carretera());

        Assertions.assertEquals(0, jugador.consultarRecursos());

    }


}
