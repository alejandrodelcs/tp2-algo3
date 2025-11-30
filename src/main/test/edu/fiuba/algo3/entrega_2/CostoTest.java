package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Construcciones.Carretera;
import edu.fiuba.algo3.modelo.Construcciones.Ciudad;
import edu.fiuba.algo3.modelo.Construcciones.Poblado;
import edu.fiuba.algo3.modelo.ElementosTablero.Vertice;
import edu.fiuba.algo3.modelo.Errores.NoHayRecursoDisponibleError;
import edu.fiuba.algo3.modelo.Inventario;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recurso.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CostoTest {

    @Test
    public void test01DeberiaDescontarmeElCostoDeUnaCarretera(){
        Jugador jugador = new Jugador("Ale", new Inventario(new Madera(), new Ladrillo()));

        jugador.construirCarretera(new Vertice(), new Vertice(), new Carretera());

        Assertions.assertEquals(0, jugador.consultarRecursos());
    }

    @Test
    public void test02DeberiaDescontarmeElCostoDeUnaCiudad(){
        Jugador jugador = new Jugador("Ale", new Inventario(new Grano(), new Grano(),
                                                    new Mineral(), new Mineral(), new Mineral()));

        jugador.construirAsentamiento(new Vertice(), new Ciudad());

        Assertions.assertEquals(0, jugador.consultarRecursos());
    }

    @Test
    public void test03DeberiaDescontarmeElCostoDeUnPoblado(){
        Jugador jugador = new Jugador("Ale", new Inventario(new Madera(), new Ladrillo(),
                                                                    new Lana(), new Grano()));

        jugador.construirAsentamiento(new Vertice(), new Poblado());

        Assertions.assertEquals(0, jugador.consultarRecursos());
    }


    @Test
    public void test04NoTieneLosRecursosSuficienteParaConstruirDeberiaLanzarUnaExcepcion(){
        Jugador jugador = new Jugador("Ale", new Inventario(new Madera(), new Ladrillo()));

        assertThrows(NoHayRecursoDisponibleError.class, () -> {
            jugador.construirAsentamiento(new Vertice(), new Poblado());
        });
    }

    @Test
    public void test05TengoRecursosDeSobraParaUnaConstruccionDeberiaRetornarmeLoqueTengoActualmente(){
        Jugador jugador = new Jugador("Ale", new Inventario(new Madera(), new Ladrillo(),
                new Lana(), new Grano(), new Grano() , new Madera() , new Ladrillo()));

        jugador.construirAsentamiento(new Vertice(), new Poblado());

        Assertions.assertEquals(3, jugador.consultarRecursos());
    }
}
