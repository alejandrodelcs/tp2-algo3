package edu.fiuba.algo3.modelo.Comercio;

import edu.fiuba.algo3.modelo.Excepciones.ComercioInvalido2a1;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import java.util.List;

public class ReglaComercio2a1 implements ReglaComercio{

    Class<? extends Recurso> tipo;

    public ReglaComercio2a1(Class<? extends Recurso> tipo){
        this.tipo = tipo;
    }

    @Override
    public void validar(List<Class<? extends Recurso>> entrega, List<Class<? extends Recurso>> recibe) {
        boolean correcto = entrega.stream().allMatch(r -> r == tipo);
        if (!correcto || entrega.size() !=  recibe.size()*2) {
            throw new ComercioInvalido2a1("Comercio 2:1 Invalido");

        }
    }
}
