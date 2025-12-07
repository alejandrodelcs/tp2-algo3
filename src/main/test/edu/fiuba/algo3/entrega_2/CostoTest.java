package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.Construccion.*;
import edu.fiuba.algo3.modelo.Tablero.Arista;
import edu.fiuba.algo3.modelo.Tablero.Vertice;
import edu.fiuba.algo3.modelo.Excepciones.NoHayRecursoDisponibleError;
import edu.fiuba.algo3.modelo.Jugador.Inventario;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CostoTest {

    @Test
    public void test01DeberiaDescontarmeElCostoDeUnaCarretera(){
        Jugador jugador = new Jugador("Ale", new Inventario(new Madera(), new Ladrillo()));
        Construible estrategia = new ConstruirCarretera();

        Vertice v1 = new Vertice();
        Vertice v2 = new Vertice();
        Arista a = new Arista(v1, v2);

        jugador.construir(estrategia, new Carretera(), a);

        assertEquals(0, jugador.consultarRecursos());
    }

    @Test
    public void test02DeberiaDescontarmeElCostoDeUnaCiudad(){
        Jugador jugador = new Jugador("Ale", new Inventario(new Grano(), new Grano(),
                                                    new Mineral(), new Mineral(), new Mineral()));
        Construible estrategia = new ConstruirAsentamiento();
        jugador.construir(estrategia, new Ciudad(), new Vertice());

        assertEquals(0, jugador.consultarRecursos());
    }

    @Test
    public void test03DeberiaDescontarmeElCostoDeUnPoblado(){
        Jugador jugador = new Jugador("Ale", new Inventario(new Madera(), new Ladrillo(),
                                                                    new Lana(), new Grano()));

        Construible estrategia = new ConstruirAsentamiento();
        jugador.construir(estrategia, new Poblado(), new Vertice());

        assertEquals(0, jugador.consultarRecursos());
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

        assertEquals(3, jugador.consultarRecursos());
    }
}
