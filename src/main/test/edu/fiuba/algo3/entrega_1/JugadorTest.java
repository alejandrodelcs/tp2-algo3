package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Construccion.Carretera;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Construccion.Poblado;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Recurso.Lana;
import edu.fiuba.algo3.modelo.Recurso.Madera;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import edu.fiuba.algo3.modelo.Arista;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;



public class JugadorTest {

   /* ● Verificar que los jugadores reciban los recursos iniciales correctos según el
    segundo poblado colocado.
    ● Verificar que el lanzamiento de dados genere un número válido (2-12).*/

    @Test
    public void test01ElJugadorDebeRecibir1LanaLuegoDeHaberColocadoSuSegundoPoblado(){
        Mapa mapa = new Mapa();
        Jugador jugador = new Jugador("alejandro", mapa);
        Poblado poblado1 = new Poblado( new Arista(1,-1,0));
        Poblado poblado2 = new Poblado(new Arista(-1,1,0));
        Carretera carretera1 = new Carretera(new Arista(0,-1,-1));
        Carretera carretera2 = new Carretera(new Arista(-1,0,1));

        jugador.construir(poblado1);
        jugador.construir(carretera1);

        jugador.construir(poblado2);


        ArrayList<Recurso> recursosEsperados = new ArrayList<>();
        recursosEsperados.add(new Lana());
        recursosEsperados.add(new Madera());

        ArrayList<Recurso> recursosObtenidos = jugador.construir(carretera2);

        Assertions.assertEquals(recursosEsperados, recursosObtenidos);
    }
}
