package edu.fiuba.algo3.TestDado;

import edu.fiuba.algo3.modelo.Dado.ReglaDado;
import edu.fiuba.algo3.modelo.Dado.ReglaDadoNormal;
import edu.fiuba.algo3.modelo.Dado.ReglaDadoSiete;
import edu.fiuba.algo3.modelo.Juego;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ReglaDadoTest {
    @Test
    public void test01ReglaDadoNormalLlamaARepartirRecursos(){
        Juego juegoMock = mock(Juego.class);
        
        ReglaDado regla = new ReglaDadoNormal(6);
        regla.aplicar(6, juegoMock);

        verify(juegoMock).repartirRecursosPorDado(6);
    }

    @Test
    public void test02ReglaDadoSieteActivaLadronYAplicaPenalidad(){
        Juego juegoMock = mock(Juego.class);
        
        ReglaDado regla = new ReglaDadoSiete(7);
        regla.aplicar(7, juegoMock);

        verify(juegoMock).aplicarPenalidadPorSiete();
        verify(juegoMock).activarLadron();
    }
}
