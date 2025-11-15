package edu.fiuba.algo3.entrega_1;

//import com.sun.scenario.effect.Crop;
import edu.fiuba.algo3.modelo.Construccion.Carretera;
import edu.fiuba.algo3.modelo.Juego.Inventario;
import edu.fiuba.algo3.modelo.Juego.Jugador;
import edu.fiuba.algo3.modelo.Construccion.Poblado;
import edu.fiuba.algo3.modelo.Juego.Terreno.Cruce;
import edu.fiuba.algo3.modelo.Juego.Terreno.Hexagono;
import edu.fiuba.algo3.modelo.Recurso.TipoRecurso;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Arista;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class JugadorTest {

    /*
     * ● Verificar que los jugadores reciban los recursos iniciales correctos según
     * el
     * segundo poblado colocado.
     * ● Verificar que el lanzamiento de dados genere un número válido (2-12).
     */

    @Test
    public void test01JugadorRecibeRecursosInicialesAlColocarSegundoPoblado() {

        Jugador jugador = new Jugador("Alejandro");
        Inventario inventario = jugador.getInventario();

        Cruce cruce = new Cruce(0);
        Poblado segundoPoblado = new Poblado(jugador);

        Hexagono bosque = new Hexagono();
        bosque.setRecurso(TipoRecurso.MADERA);

        Hexagono colina = new Hexagono();
        colina.setRecurso(TipoRecurso.LADRILLO);

        Hexagono campo = new Hexagono();
        campo.setRecurso(TipoRecurso.GRANO);

        cruce.agregarHexagono(bosque);
        cruce.agregarHexagono(colina);
        cruce.agregarHexagono(campo);
        cruce.setConstruccion(segundoPoblado);

        cruce.distribuirRecursosIniciales();

        Assertions.assertEquals(1, inventario.contar(TipoRecurso.MADERA));
        Assertions.assertEquals(1, inventario.contar(TipoRecurso.LADRILLO));
        Assertions.assertEquals(1, inventario.contar(TipoRecurso.GRANO));

        Assertions.assertEquals(0, inventario.contar(TipoRecurso.LANA));
        Assertions.assertEquals(0, inventario.contar(TipoRecurso.MINERAL));
    }

    @Test
    public void test02JugadorNoRecibeRecursosAlColocarPrimerPoblado() {

        Jugador jugador = new Jugador("Alejandro");
        Inventario inventario = jugador.getInventario();
        Cruce cruce = new Cruce(0);
        Poblado primerPoblado = new Poblado(jugador);

        Hexagono bosque = new Hexagono();
        bosque.setRecurso(TipoRecurso.MADERA);
        cruce.agregarHexagono(bosque);

        cruce.setConstruccion(primerPoblado);

        Assertions.assertEquals(0, inventario.contar(TipoRecurso.MADERA));
        Assertions.assertEquals(0, inventario.contar(TipoRecurso.LADRILLO));
        Assertions.assertEquals(0, inventario.contar(TipoRecurso.GRANO));
    }
}
