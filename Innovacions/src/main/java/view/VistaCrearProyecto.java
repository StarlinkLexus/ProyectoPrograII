package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Vista que permite crear un nuevo proyecto de tipo Investigacion o Emprendimiento.
 * El usuario debe ingresar nombre, descripcion, URL, tipo, responsable,
 * y opcionalmente monto si es emprendimiento.
 * Ademas permite asignar hasta 4 colaboradores donantes y 4 colaboradores trabajadores.
 */
public class VistaCrearProyecto {

    // Ventana principal
    private Stage primaryStage;

    /**
     * Constructor que recibe la ventana principal
     * @param stage ventana principal de la aplicacion
     */
    public VistaCrearProyecto(Stage stage) {
        this.primaryStage = stage;
    }

    /**
     * Metodo que construye y muestra la interfaz de creacion de proyecto
     */
    public void mostrar() {
        VBox layout = new VBox(10);

        // Campo para nombre del proyecto
        TextField nombre = new TextField();
        nombre.setPromptText("Nombre del proyecto");

        // Campo para descripcion
        TextField descripcion = new TextField();
        descripcion.setPromptText("Descripcion");

        // Campo para URL del video
        TextField url = new TextField();
        url.setPromptText("URL del video");

        // ComboBox para seleccionar investigador responsable
        ComboBox<Investigador> comboResponsable = new ComboBox<>();
        comboResponsable.getItems().addAll(Repositorio.investigadores);
        comboResponsable.setPromptText("Responsable");

        // ComboBox para seleccionar tipo de proyecto
        ComboBox<String> tipoProyecto = new ComboBox<>();
        tipoProyecto.getItems().addAll("Investigacion", "Emprendimiento");
        tipoProyecto.setPromptText("Tipo de proyecto");

        // Campo para monto a financiar (solo si es emprendimiento)
        TextField monto = new TextField();
        monto.setPromptText("Monto a financiar (solo si es emprendimiento)");
        monto.disableProperty().bind(tipoProyecto.valueProperty().isNotEqualTo("Emprendimiento"));

        // ListView para colaboradores que van a donar
        ListView<Colaborador> listaColabsDonantes = new ListView<>();
        listaColabsDonantes.getItems().addAll(Repositorio.colaboradores);
        listaColabsDonantes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listaColabsDonantes.setPrefHeight(100);
        Label labelColabsDonantes = new Label("Seleccionar Colaboradores Donantes (max 4):");

        // ListView para colaboradores que van a trabajar
        ListView<Colaborador> listaColabsTrabajadores = new ListView<>();
        listaColabsTrabajadores.getItems().addAll(Repositorio.colaboradores);
        listaColabsTrabajadores.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listaColabsTrabajadores.setPrefHeight(100);
        Label labelColabsTrabajadores = new Label("Seleccionar Colaboradores Trabajadores (max 4):");

        // Label para mostrar mensajes o errores
        Label resultado = new Label();

        // Boton para crear el proyecto
        Button btnCrear = new Button("Crear Proyecto");
        btnCrear.setOnAction(e -> {
            // Obtener los valores ingresados
            String n = nombre.getText().trim();
            String d = descripcion.getText().trim();
            String u = url.getText().trim();
            Investigador r = comboResponsable.getValue();
            String tipo = tipoProyecto.getValue();
            String mText = monto.getText().trim();

            // Validar campos obligatorios
            if (n.isEmpty() || d.isEmpty() || u.isEmpty() || r == null || tipo == null) {
                resultado.setText("Completa todos los campos obligatorios.");
                return;
            }

            Proyecto nuevo;

            // Crear proyecto segun tipo
            if (tipo.equals("Investigacion")) {
                nuevo = new ProyectoInvestigacion(n, d, u, r);
            } else {
                try {
                    if (mText.isEmpty()) {
                        resultado.setText("Monto es obligatorio para proyectos de Emprendimiento.");
                        return;
                    }
                    double m = Double.parseDouble(mText);
                    if (m <= 0) {
                        resultado.setText("Monto debe ser mayor que cero.");
                        return;
                    }
                    nuevo = new ProyectoEmprendimiento(n, d, u, r, m);
                } catch (NumberFormatException ex) {
                    resultado.setText("Monto invalido. Introduce un numero.");
                    return;
                }
            }

            // Obtener colaboradores donantes seleccionados
            List<Colaborador> seleccionadosDonantes = listaColabsDonantes.getSelectionModel().getSelectedItems();
            if (seleccionadosDonantes.size() > 4) {
                resultado.setText("No se pueden seleccionar mas de 4 colaboradores donantes.");
                return;
            }

            // Verificar duplicados
            Set<Colaborador> unicosDonantes = new HashSet<>(seleccionadosDonantes);
            if (unicosDonantes.size() < seleccionadosDonantes.size()) {
                resultado.setText("No se permiten colaboradores donantes duplicados.");
                return;
            }

            for (Colaborador c : unicosDonantes) {
                nuevo.agregarColaborador(c); // Agregar colaborador donante
            }

            // Obtener colaboradores trabajadores seleccionados
            List<Colaborador> seleccionadosTrabajadores = listaColabsTrabajadores.getSelectionModel().getSelectedItems();
            if (seleccionadosTrabajadores.size() > 4) {
                resultado.setText("No se pueden seleccionar mas de 4 colaboradores trabajadores.");
                return;
            }

            // Verificar duplicados
            Set<Colaborador> unicosTrabajadores = new HashSet<>(seleccionadosTrabajadores);
            if (unicosTrabajadores.size() < seleccionadosTrabajadores.size()) {
                resultado.setText("No se permiten colaboradores trabajadores duplicados.");
                return;
            }

            for (Colaborador c : unicosTrabajadores) {
                nuevo.agregarColaborador(c); // Agregar colaborador trabajador
            }

            // Agregar el proyecto al repositorio
            Repositorio.proyectos.add(nuevo);
            resultado.setText("Proyecto agregado correctamente: " + nuevo.getNombre());

            // Limpiar campos
            nombre.clear();
            descripcion.clear();
            url.clear();
            monto.clear();
            comboResponsable.getSelectionModel().clearSelection();
            tipoProyecto.getSelectionModel().clearSelection();
            listaColabsDonantes.getSelectionModel().clearSelection();
            listaColabsTrabajadores.getSelectionModel().clearSelection();
        });

        // Boton para volver al menu principal
        Button btnVolver = new Button("Volver");
        btnVolver.setOnAction(e -> new MenuPrincipal(primaryStage).mostrar());

        // Agregar todos los elementos al layout
        layout.getChildren().addAll(
            nombre, descripcion, url, comboResponsable, tipoProyecto, monto,
            labelColabsDonantes, listaColabsDonantes,
            labelColabsTrabajadores, listaColabsTrabajadores,
            btnCrear, resultado, btnVolver
        );
        layout.setPadding(new Insets(20));

        // Mostrar escena
        Scene scene = new Scene(layout, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Nuevo Proyecto");
        primaryStage.show();
    }
}
