package edu.fiuba.algo3.vistas.escenas.estilosVistas;

import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

/**
 * BotonTurnoDado
 */
public class BotonTurnoDado extends HBox {
    private final Button btnPasarTurno;
    private final Button btnTirarDado;

    public BotonTurnoDado() {

        this.setSpacing(20);
        this.setAlignment(Pos.TOP_CENTER);
        this.setStyle(
                "-fx-background-color: #4d3a35;" +
                        "-fx-background-radius: 20;" +
                        "-fx-padding: 20;");

        btnPasarTurno = new Button("Pasar Turno");
        btnPasarTurno.setPrefWidth(140);

        btnTirarDado = new Button("Tirar Dado");
        btnTirarDado.setPrefWidth(140);

        this.getChildren().addAll(btnTirarDado, btnPasarTurno);
    }
}
