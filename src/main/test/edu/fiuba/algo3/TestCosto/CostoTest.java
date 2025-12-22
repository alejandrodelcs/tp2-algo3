package edu.fiuba.algo3.TestCosto;

import edu.fiuba.algo3.modelo.Construccion.*;
import edu.fiuba.algo3.modelo.Costo.ReglaCostoConstruccion;
import edu.fiuba.algo3.modelo.Tablero.Arista;
import edu.fiuba.algo3.modelo.Tablero.Vertice;
import edu.fiuba.algo3.modelo.Excepciones.NoHayRecursoDisponibleError;
import edu.fiuba.algo3.modelo.Excepciones.RecursosInsuficientesException;
import edu.fiuba.algo3.modelo.Jugador.Inventario;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Recurso.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CostoTest {

    @Test
    public void test01DeberiaDescontarmeElCostoDeUnaCarretera(){
        Jugador jugador = new Jugador("Ale", new Inventario(new Madera(), new Ladrillo(),
                                            new Madera(),new Ladrillo(), new Lana(), new Grano()));

        Vertice v1 = new Vertice();
        Vertice v2 = new Vertice();
        Arista a = new Arista(v1, v2);

        jugador.construir(new Poblado(), v1);

        jugador.construir(new Carretera(new ReglaCostoConstruccion()), a);

        assertEquals(0, jugador.cantidadCartas());
    }

    @Test
    public void test02DeberiaDescontarmeElCostoDeUnaCiudad(){
        Jugador jugador = new Jugador("Ale", new Inventario(new Grano(), new Grano(),
                                                    new Mineral(), new Mineral(), new Mineral(), new Madera(), new Lana(), new Ladrillo(), new Grano()));
        Vertice v = new Vertice();
        jugador.construir(new Poblado(), v);
        jugador.mejorarConstruccionUbicadoEn(v);

        assertEquals(0, jugador.cantidadCartas());
    }

    @Test
    public void test03DeberiaDescontarmeElCostoDeUnPoblado(){
        Jugador jugador = new Jugador("Ale", new Inventario(new Madera(), new Ladrillo(),
                                                                    new Lana(), new Grano()));
        jugador.construir(new Poblado(), new Vertice());

        assertEquals(0, jugador.cantidadCartas());
    }


    @Test
    public void test04NoTieneLosRecursosSuficienteParaConstruirDeberiaLanzarUnaExcepcion(){
        Jugador jugador = new Jugador("Ale", new Inventario(new Madera(), new Ladrillo()));
        assertThrows(RecursosInsuficientesException.class,
                () -> jugador.construir(new Poblado(), new Vertice()));
    }

    @Test
    public void test05TengoRecursosDeSobraParaUnaConstruccionDeberiaRetornarmeLoqueTengoActualmente(){
        Jugador jugador = new Jugador("Ale", new Inventario(new Madera(), new Ladrillo(),
                new Lana(), new Grano(), new Grano() , new Madera() , new Ladrillo()));

        jugador.construir(new Poblado(), new Vertice());

        assertEquals(3, jugador.cantidadCartas());
    }
}
