package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView extends Application {
    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        root.getChildren().add(new Label("Portal de Innovación - Vista principal"));

        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Portal de Innovación");
        stage.show();
    }
}