package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Colaborador;
import model.Investigador;
import model.Repositorio;

public class VistaColaboradores {

    // Atributo de la ventana principal
    private Stage primaryStage;

    /*
     * Constructor de la vista VistaColaboradores
     * Inicializa con la ventana principal
     */
    public VistaColaboradores(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /*
     * Metodo para mostrar la interfaz de gestion de colaboradores e investigadores
     */
    public void mostrar() {
        // Elemento de titulo
        Label lblTitulo = new Label("Anadir Colaboradores e Investigadores");

        // Campo para ingresar el nombre
        TextField txtNombre = new TextField();
        txtNombre.setPromptText("Nombre");

        // Campo para la pagina web (solo investigadores)
        TextField txtPaginaWeb = new TextField();
        txtPaginaWeb.setPromptText("Pagina web (solo para investigadores)");

        // Campo para el area de experiencia (solo investigadores)
        TextField txtArea = new TextField();
        txtArea.setPromptText("Area de experiencia (solo para investigadores)");

        // Botones para agregar colaboradores e investigadores
        Button btnAgregarColaborador = new Button("Agregar Colaborador");
        Button btnAgregarInvestigador = new Button("Agregar Investigador");

        // Lista visual para mostrar los nombres agregados
        ListView<String> lista = new ListView<>();

        // Evento al presionar agregar colaborador
        btnAgregarColaborador.setOnAction(e -> {
            String nombre = txtNombre.getText().trim();
            if (!nombre.isEmpty()) {
                Colaborador c = new Colaborador(nombre);
                Repositorio.colaboradores.add(c);
                lista.getItems().add(c.toString());
                txtNombre.clear();
            }
        });

        // Evento al presionar agregar investigador
        btnAgregarInvestigador.setOnAction(e -> {
            String nombre = txtNombre.getText().trim();
            String pagina = txtPaginaWeb.getText().trim();
            String area = txtArea.getText().trim();
            if (!nombre.isEmpty() && !pagina.isEmpty() && !area.isEmpty()) {
                Investigador i = new Investigador(nombre, pagina, area);
                Repositorio.investigadores.add(i);
                lista.getItems().add(i.toString());
                txtNombre.clear();
                txtPaginaWeb.clear();
                txtArea.clear();
            }
        });

        // Boton para volver al menu principal
        Button btnVolver = new Button("Volver");
        btnVolver.setOnAction(e -> new MenuPrincipal(primaryStage).mostrar());

        // Organizacion de los elementos en la interfaz
        VBox formulario = new VBox(10, txtNombre, txtPaginaWeb, txtArea, btnAgregarColaborador, btnAgregarInvestigador);
        VBox contenedor = new VBox(15, lblTitulo, formulario, new Label("Lista de Colaboradores e Investigadores:"), lista, btnVolver);
        contenedor.setPadding(new Insets(20));

        // Creacion y muestra de la escena
        Scene scene = new Scene(contenedor, 600, 550);
        primaryStage.setScene(scene);
    }
}
