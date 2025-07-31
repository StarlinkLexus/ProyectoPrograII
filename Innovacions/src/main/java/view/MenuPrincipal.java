package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuPrincipal {

    // Atributo: ventana principal de la aplicacion
    private Stage primaryStage;

    /*
     * Constructor del menu principal.
     * Recibe el escenario principal de la aplicacion.
     */
    public MenuPrincipal(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Muestra la interfaz grafica del menu principal con tres opciones:
     * - Crear nuevo proyecto
     * - Ver investigadores
     * - Ver colaboradores/donadores
     */
    public void mostrar() {
        // Boton para crear un nuevo proyecto
        Button btnProyecto = new Button("Crear nuevo proyecto");

        // Boton para acceder a la vista de investigadores
        Button btnInvestigadores = new Button("Investigadores");

        // Boton para acceder a la vista de colaboradores
        Button btnColaboradores = new Button("Donadores / Colaboradores");

        // Accion del boton de proyecto
        btnProyecto.setOnAction(e -> {
            new VistaCrearProyecto(primaryStage).mostrar();
        });

        // Accion del boton de investigadores
        btnInvestigadores.setOnAction(e -> {
            new VistaInvestigadores(primaryStage).mostrar();
        });

        // Accion del boton de colaboradores
        btnColaboradores.setOnAction(e -> {
            new VistaColaboradores(primaryStage).mostrar();
        });

        // Contenedor vertical con los botones
        VBox root = new VBox(20, btnProyecto, btnInvestigadores, btnColaboradores);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(50));

        // Crear y configurar la escena principal
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Menu Principal");
        primaryStage.show();
    }
}
