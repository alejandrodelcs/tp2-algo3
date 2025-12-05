package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.vistas.escenas.*;
import edu.fiuba.algo3.vistas.escenas.estilosVistas.BotonesVista;
import edu.fiuba.algo3.vistas.escenas.estilosVistas.TituloVista;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

/**
 * MenuScene
 */
public class MenuInicio extends EscenaGeneral {
    HBox titulo;
    HBox barra;
    HBox hboxMadre;
    HBox layoutPrincipal;

    VBox columnaLateral;
    VBox columnaLateral2;
    VBox contenedorBotones;
    VBox vboxPrincipal;
    VBox boxVerticalPrincipal;

    Button botonSalir;
    Button botonJugar;

    Region regionCentral;

    public MenuInicio(Stage stage) {
        super(stage);
        this.crearControladores(stage);
    }

    protected Pane crearLayout(Stage stage) {

        StackPane root = new StackPane();

        this.titulo = new TituloVista("CATAN");
        this.botonJugar = new BotonesVista("Juego nuevo");
        Button botonConfig = new BotonesVista("Configuración");
        Button botonVolumen = new BotonesVista("Volumen");
        this.botonSalir = new BotonesVista("Salir");

        this.contenedorBotones = new VBox(15, botonJugar, botonConfig, botonVolumen, botonSalir);

        this.columnaLateral = new VBox(30, contenedorBotones);
        this.columnaLateral2 = new VBox(30);

        // Region regionCentral = new Region();
        VBox regionCentral = new VBox(titulo);
        regionCentral.setPadding(new Insets(150, 0, 0, 0));// buscar la forma de centrar bien esto
        HBox.setHgrow(regionCentral, Priority.ALWAYS);

        this.layoutPrincipal = new HBox(columnaLateral, regionCentral, columnaLateral2);

        root.getChildren().add(layoutPrincipal);

        return root;
    }

    protected void crearControladores(Stage stage) {
        this.botonJugar.setOnAction(e -> {
            try {
                EscenaJuego escenaJuego = new EscenaJuego(stage);
                stage.setScene(escenaJuego.getScene());
                stage.setFullScreenExitHint("");
                stage.setFullScreen(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        this.botonSalir.setOnAction(e -> {
            stage.close();
        });
    }

    protected void generarEstilos() {

        this.columnaLateral.setStyle(
                "-fx-background-color: #4d3a35; " +
                        "-fx-padding: 40 30 40 30; " +
                        "-fx-background-radius: 20;");
        this.columnaLateral.setPrefWidth(300);
        this.columnaLateral.setAlignment(Pos.CENTER);

        this.columnaLateral2.setPrefWidth(300);

        this.contenedorBotones.setAlignment(Pos.CENTER);
        this.contenedorBotones.setPadding(new Insets(20, 0, 0, 0));

        StackPane.setMargin(this.layoutPrincipal, new Insets(20));

        this.layout.setStyle("-fx-background-color: #E8E8E8;");
        this.titulo.setStyle("-fx-font-size: 120;");
    }

}
