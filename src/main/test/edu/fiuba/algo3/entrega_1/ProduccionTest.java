package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Construccion.Poblado;
import edu.fiuba.algo3.modelo.Juego.*;
import edu.fiuba.algo3.modelo.Juego.Terreno.*;
import edu.fiuba.algo3.modelo.Material.TipoRecurso;
import edu.fiuba.algo3.modelo.elementos.Ficha;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProduccionTest {

    @Test
    public void test01UnPobladoProduceUnRecursoCuandoElDadoCoincide() {

        // --- ARRANGE ---

        Jugador jugador1 = new Jugador("Azul");
        Hexagono bosque = new Hexagono(0);
        Cruce cruce = new Cruce(0);
        Poblado poblado = new Poblado(jugador1);

        bosque.setRecurso(TipoRecurso.MADERA);
        bosque.setFicha(new Ficha(9));

        bosque.agregarCruce(cruce);
        cruce.agregarHexagono(bosque);

        cruce.setConstruccion(poblado);
        jugador1.agregarConstruccion(poblado);

        List<Hexagono> hexagonos = List.of(bosque);
        Tablero tablero = new Tablero(Map.of(0, cruce), hexagonos, null);

        // --- ACT  ---

        tablero.distribuirProduccion(9);

        // --- ASSERT ---

        Inventario inventario = jugador1.getInventario();
        assertEquals(1, inventario.contar(TipoRecurso.MADERA));
        assertEquals(0, inventario.contar(TipoRecurso.LADRILLO));
    }
}
