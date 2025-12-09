package edu.fiuba.algo3.vistas.Alertas;

import javafx.application.Platform;
import javafx.scene.control.Alert;

public class AlertaWarning {

    public void mostrarWarning(String titulo, String cabecera, String contenido){
        Platform.runLater(()->{
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle(titulo);
            alerta.setHeaderText(cabecera);
            alerta.setContentText(contenido);
            alerta.showAndWait();
        });


    }
}
