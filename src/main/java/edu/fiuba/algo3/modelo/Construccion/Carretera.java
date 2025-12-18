package edu.fiuba.algo3.modelo.Construccion;

import edu.fiuba.algo3.modelo.Costo.Costo;
import edu.fiuba.algo3.modelo.Costo.ReglaCosto;
import edu.fiuba.algo3.modelo.Costo.ReglaCostoGratis;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Arista;
import edu.fiuba.algo3.modelo.Recurso.Ladrillo;
import edu.fiuba.algo3.modelo.Recurso.Madera;
import edu.fiuba.algo3.modelo.Recurso.Recurso;



public class Carretera extends Construccion{

    Arista arista;


    public Carretera(ReglaCosto reglaCosto) {
        super(new ConstruirCarretera(new ReglaAdyacencia()), reglaCosto);
        if (reglaCosto instanceof ReglaCostoGratis) {
            this.costo = new Costo();
        } else {
            this.costo = new Costo(Madera.class, Ladrillo.class);
        }
    }


    @Override
    public int getPuntosDeVictoria() {
        return 0;
    }

    @Override
    public String getNombre() {
        return "carretera";
    }


    @Override
    public void producirSegun(Recurso recurso) {
    }



    /*@Override
    public boolean esAdyacenteA(Arista otra) {
        return this.arista != null && this.arista.esAdyacenteA(otra);
    }*/

    public boolean carreteraEsPropietarioDe(Jugador j){
        return this.propietario == j;
    }

}
