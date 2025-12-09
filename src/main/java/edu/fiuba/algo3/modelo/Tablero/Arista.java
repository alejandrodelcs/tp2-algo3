package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Construccion.Carretera;
import edu.fiuba.algo3.modelo.Excepciones.AristaOcupadaError;
import edu.fiuba.algo3.modelo.Jugador.Jugador;

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
        this.conectarVertices();
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


    public boolean conectaA(Vertice v1, Vertice v2) {
        return (this.primerVertice == v1 && this.segundoVertice == v2)
                || (this.primerVertice == v2 && this.segundoVertice == v1);
    }

    public boolean tieneCarreteraDel(Jugador j) {
        return carretera != null &&  carretera.carreteraEsPropietarioDe(j);
    }

    public boolean consultarConexionCon(Jugador jugador) {
       return  this.primerVertice.tieneConstruccionDel(jugador) ||
               this.segundoVertice.tieneConstruccionDel(jugador) ||
               this.primerVertice.tieneCarreteraDel(jugador) ||
               this.segundoVertice.tieneCarreteraDel(jugador);
    }
}
