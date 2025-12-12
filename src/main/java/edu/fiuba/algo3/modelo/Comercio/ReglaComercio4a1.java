package edu.fiuba.algo3.modelo.Comercio;

import edu.fiuba.algo3.modelo.Excepciones.ComercioInvalido4a1;
import edu.fiuba.algo3.modelo.Recurso.Recurso;
import java.util.List;

public class ReglaComercio4a1 implements ReglaComercio{
    @Override
    public void validar(List<Class<? extends Recurso>> entrega, List<Class<? extends Recurso>> recibe) {
        if (entrega.size() != recibe.size() * 4) {
            throw new ComercioInvalido4a1("Comercio 4:1 Invalido");

        }
    }
}
