package edu.fiuba.algo3.modelo.Comercio;

import edu.fiuba.algo3.modelo.Excepciones.ComercioInvalido3a1;
import edu.fiuba.algo3.modelo.Recurso.Recurso;

import java.util.List;

public class ReglaComercio3a1 implements ReglaComercio{
    @Override
    public void validar(List<Class<? extends Recurso>> entrega, List<Class<? extends Recurso>> recibe) {
        if (entrega.size() != recibe.size() * 3) {
            throw new ComercioInvalido3a1("Comercio 3:1 Invalido");

        }

    }
}
