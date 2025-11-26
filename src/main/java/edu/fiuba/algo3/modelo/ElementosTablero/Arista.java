package edu.fiuba.algo3.modelo.ElementosTablero;

/**
 * Arista
 */
public class Arista {
    private Vertice primerVertice;
    private Vertice segundoVertice;

    public Arista(Vertice v1, Vertice v2) {
        this.primerVertice = v1;
        this.segundoVertice = v2;
        this.conectarVertices();
    }

    private void conectarVertices() {
        this.primerVertice.conectarArista(this);
        this.segundoVertice.conectarArista(this);

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

}
