package app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.PantallaPrincipal;

/*
 * Clase principal que lanza la aplicacion JavaFX
 */
public class MainApp extends Application {

    /*
     * Metodo principal de inicio de la aplicacion
     * Crea una ventana con la pantalla principal y la muestra
     */
    @Override
    public void start(Stage primaryStage) {
        // Se crea una instancia de la pantalla principal (es un contenedor)
        PantallaPrincipal root = new PantallaPrincipal();

        // Se crea una escena con el contenedor y se define su tamanio
        Scene scene = new Scene(root, 800, 600);

        // Se configura la ventana principal
        primaryStage.setTitle("Sistema de Proyectos");
        primaryStage.setScene(scene);
        primaryStage.show(); // Se muestra la ventana
    }

    /*
     * Metodo main que lanza la aplicacion
     */
    public static void main(String[] args) {
        launch(args);
    }
}
