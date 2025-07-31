package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Colaborador;
import model.Investigador;
import model.Repositorio;

/**
 * Vista para la gestion de investigadores en la aplicacion.
 * Permite agregar nuevos investigadores, que tambien se registran como colaboradores.
 */
public class VistaInvestigadores {

    // Atributo para mantener una referencia a la ventana principal
    private Stage primaryStage;

    // Constructor que recibe la ventana principal como parametro
    public VistaInvestigadores(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Metodo que muestra la interfaz grafica para gestionar investigadores.
     * Permite ingresar nombre, pagina web y area, y agregar el investigador.
     * Tambien lo registra como colaborador.
     */
    public void mostrar() {
        // Titulo de la vista
        Label lblTitulo = new Label("Gestion de Investigadores");

        // Campos de texto para ingresar los datos del investigador
        TextField txtNombre = new TextField();
        txtNombre.setPromptText("Nombre");

        TextField txtPaginaWeb = new TextField();
        txtPaginaWeb.setPromptText("Pagina web");

        TextField txtArea = new TextField();
        txtArea.setPromptText("Area de experiencia");

        // Boton para agregar el investigador
        Button btnAgregar = new Button("Agregar Investigador");

        // Lista donde se muestran los investigadores agregados
        ListView<String> lista = new ListView<>();

        // Evento del boton que agrega el investigador y lo registra como colaborador
        btnAgregar.setOnAction(e -> {
            String nombre = txtNombre.getText().trim();
            String pagina = txtPaginaWeb.getText().trim();
            String area = txtArea.getText().trim();

            if (!nombre.isEmpty() && !pagina.isEmpty() && !area.isEmpty()) {
                Investigador i = new Investigador(nombre, pagina, area);
                Repositorio.investigadores.add(i);

                // Tambien se agrega como colaborador
                Colaborador colaborador = new Colaborador(nombre);
                Repositorio.colaboradores.add(colaborador);

                // Se muestra en la lista visual
                lista.getItems().add(i.toString());

                // Se limpian los campos
                txtNombre.clear();
                txtPaginaWeb.clear();
                txtArea.clear();
            }
        });

        // Boton para volver al menu principal
        Button btnVolver = new Button("Volver");
        btnVolver.setOnAction(e -> new MenuPrincipal(primaryStage).mostrar());

        // Contenedor para los campos de entrada
        VBox formulario = new VBox(10, txtNombre, txtPaginaWeb, txtArea, btnAgregar);

        // Contenedor principal con el titulo, formulario, lista y boton de volver
        VBox contenedor = new VBox(15, lblTitulo, formulario, new Label("Lista de Investigadores:"), lista, btnVolver);
        contenedor.setPadding(new Insets(20));

        // Se crea la escena y se asigna a la ventana principal
        Scene scene = new Scene(contenedor, 600, 550);
        primaryStage.setScene(scene);
    }
}
