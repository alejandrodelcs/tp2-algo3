package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Construccion.*;
import edu.fiuba.algo3.modelo.Tablero.Vertice;
import edu.fiuba.algo3.modelo.Excepciones.NoHayRecursoDisponibleError;
import edu.fiuba.algo3.modelo.Inventario;
import edu.fiuba.algo3.modelo.Juego.Jugador;
import edu.fiuba.algo3.modelo.Recurso.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CostoTest {

    @Test
    public void test01DeberiaDescontarmeElCostoDeUnaCarretera(){
        Jugador jugador = new Jugador("Ale", new Inventario(new Madera(), new Ladrillo()));
        Construible estrategia = new ConstruirCarretera();
        jugador.construir(estrategia, new Carretera(),new Vertice(), new Vertice());

        Assertions.assertEquals(0, jugador.consultarRecursos());
    }

    @Test
    public void test02DeberiaDescontarmeElCostoDeUnaCiudad(){
        Jugador jugador = new Jugador("Ale", new Inventario(new Grano(), new Grano(),
                                                    new Mineral(), new Mineral(), new Mineral()));
        Construible estrategia = new ConstruirAsentamiento();
        jugador.construir(estrategia, new Ciudad(), new Vertice());

        Assertions.assertEquals(0, jugador.consultarRecursos());
    }

    @Test
    public void test03DeberiaDescontarmeElCostoDeUnPoblado(){
        Jugador jugador = new Jugador("Ale", new Inventario(new Madera(), new Ladrillo(),
                                                                    new Lana(), new Grano()));

        Construible estrategia = new ConstruirAsentamiento();
        jugador.construir(estrategia, new Poblado(), new Vertice());

        Assertions.assertEquals(0, jugador.consultarRecursos());
    }


    @Test
    public void test04NoTieneLosRecursosSuficienteParaConstruirDeberiaLanzarUnaExcepcion(){
        Jugador jugador = new Jugador("Ale", new Inventario(new Madera(), new Ladrillo()));
        Construible estrategia = new ConstruirAsentamiento();


        assertThrows(NoHayRecursoDisponibleError.class,
                () -> jugador.construir(estrategia, new Poblado(), new Vertice()));
    }

    @Test
    public void test05TengoRecursosDeSobraParaUnaConstruccionDeberiaRetornarmeLoqueTengoActualmente(){
        Jugador jugador = new Jugador("Ale", new Inventario(new Madera(), new Ladrillo(),
                new Lana(), new Grano(), new Grano() , new Madera() , new Ladrillo()));

        Construible estrategia = new ConstruirAsentamiento();
        jugador.construir(estrategia, new Poblado(), new Vertice());

        Assertions.assertEquals(3, jugador.consultarRecursos());
    }
}
