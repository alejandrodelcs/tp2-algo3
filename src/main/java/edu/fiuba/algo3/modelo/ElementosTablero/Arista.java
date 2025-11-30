package edu.fiuba.algo3.modelo.ElementosTablero;

import edu.fiuba.algo3.modelo.Construcciones.Carretera;
import edu.fiuba.algo3.modelo.Errores.AristaOcupadaError;

/**
 * Arista
 */
public class Arista {
    private final Vertice primerVertice;
    private final Vertice segundoVertice;
    private Carretera carretera;

    public Arista(Vertice v1, Vertice v2) {
        this.primerVertice = v1;
        this.segundoVertice = v2;
        conectarVertices();

    }

    private void conectarVertices() {
        this.primerVertice.conectarArista(this);
        this.segundoVertice.conectarArista(this);

    }

    public void colocarCarretera(Carretera carretera) {
        if (this.carretera != null) {
            throw new AristaOcupadaError();
        }
        this.carretera = carretera;
    }

    public boolean estaOcupada() {
        return this.carretera != null;
    }

    public boolean vecinoConstruido(Vertice vertice) {

        Vertice otro = this.getOtroVertice(vertice);
        return otro.tieneConstruccion();
    }

    public Vertice getOtroVertice(Vertice vertice) {
        if (this.primerVertice == vertice) {
            return this.segundoVertice;
        }
        if (this.segundoVertice == vertice) {
            return this.primerVertice;
        }
        return null;// modelar exepcion
    }


    public boolean esAdyacenteA(Arista otra) {
        return this.primerVertice == otra.primerVertice
                || this.primerVertice == otra.segundoVertice
                || this.segundoVertice == otra.primerVertice
                || this.segundoVertice == otra.segundoVertice;
    }
}
