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
    VBox columnaLateral;
    VBox contenedorBotones;
    VBox vboxPrincipal;
    VBox boxVerticalPrincipal;

    Button botonSalir;
    Button botonJugar;

    Region regionCentral;

    public MenuInicio(Stage stage) {
        super(stage);
    }

    protected Pane crearLayout() {

        StackPane root = new StackPane();

        // Título y botones en columna lateral
        this.titulo = new TituloVista("CATAN");
        this.botonJugar = new BotonesVista("Juego nuevo");
        Button botonConfig = new BotonesVista("Configuración");
        Button botonVolumen = new BotonesVista("Volumen");
        this.botonSalir = new BotonesVista("Salir");

        this.contenedorBotones = new VBox(15, botonJugar, botonConfig, botonVolumen, botonSalir);

        this.columnaLateral = new VBox(30, titulo, contenedorBotones);

        Region regionCentral = new Region();
        HBox.setHgrow(regionCentral, Priority.ALWAYS);

        HBox layoutPrincipal = new HBox(columnaLateral, regionCentral);

        root.getChildren().add(layoutPrincipal);
        return root;
    }

    protected void generarEstilos() {
        // Estilos de la columna lateral (barra marrón)
        this.columnaLateral.setStyle(
                "-fx-background-color: #8B7265; " + // Color marrón rosado
                        "-fx-padding: 40 30 40 30; " +
                        "-fx-background-radius: 20;");
        this.columnaLateral.setPrefWidth(300);
        this.columnaLateral.setAlignment(Pos.TOP_CENTER);

        // Estilos de los botones
        this.contenedorBotones.setAlignment(Pos.CENTER_LEFT);
        this.contenedorBotones.setPadding(new Insets(20, 0, 0, 0));

        // Fondo general gris claro
        this.layout.setStyle("-fx-background-color: #E8E8E8;");
    }

}
