package edu.fiuba.algo3.modelo.Tablero;

import edu.fiuba.algo3.modelo.Construccion.Carretera;
import edu.fiuba.algo3.modelo.Excepciones.AristaOcupadaError;
import edu.fiuba.algo3.modelo.Jugador.Jugador;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
            throw new AristaOcupadaError("Esta arista ya esta ocupada.");
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

    public boolean conectaConConstruccionDe(Jugador jugador) {
        return primerVertice.tieneConstruccionDel(jugador)
                || segundoVertice.tieneConstruccionDel(jugador);
    }

    public boolean tieneCarreteraDel(Jugador j) {
        return carretera != null && carretera.carreteraEsPropietarioDe(j);
    }

    public boolean conectaConCarreteraDe(Jugador jugador) {
        return this.aristasAdyacentes()
                .stream()
                .anyMatch(a -> a.tieneCarreteraDel(jugador));
    }

    private Set<Arista> aristasAdyacentes() {
        Set<Arista> adyacentes = new HashSet<>();

        adyacentes.addAll(primerVertice.getAristas());
        adyacentes.addAll(segundoVertice.getAristas());

        adyacentes.remove(this);

        return adyacentes;
    }

    public Carretera getCarretera() {
        return carretera;
    }

    public boolean consultarConexionCon(Jugador jugador) {
        return conectaConConstruccionDe(jugador)
                || conectaConCarreteraDe(jugador);
    }

    public boolean conectaA(Vertice v1, Vertice v2) {
        return (this.primerVertice == v1 && this.segundoVertice == v2)
                || (this.primerVertice == v2 && this.segundoVertice == v1);
    }

    public Optional<Jugador> getPropietario() {
        if (this.carretera == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(this.carretera.getPropietario());
    }
}
