package app;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox; // Un contenedor simple por ahora
import javafx.scene.control.Label; // Un elemento simple para mostrar texto

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Configuración básica de la ventana principal
            primaryStage.setTitle("Portal de Innovación - Proyectos");

            // Crear un contenedor raíz (VBox es simple para empezar)
            VBox root = new VBox(10); // Espaciado de 10px entre elementos
            root.setPrefSize(800, 600); // Tamaño preferido de la ventana

            // Añadir un mensaje de bienvenida simple por ahora
            Label welcomeLabel = new Label("¡Bienvenido al Portal de Innovación!");
            root.getChildren().add(welcomeLabel);

            // Crear una escena y asignarla al Stage
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

            // Mostrar la ventana
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace(); // Imprime cualquier error que ocurra durante la inicialización
        }
    }

    public static void main(String[] args) {
        launch(args); // Método que lanza la aplicación JavaFX
    }
}