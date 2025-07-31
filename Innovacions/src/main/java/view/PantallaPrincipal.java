package view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.*;

import java.util.List;
import java.util.stream.Collectors;

public class PantallaPrincipal extends TabPane {

    /*
     * Constructor de la pantalla principal.
     * Carga los datos iniciales y crea las pestañas.
     */
    public PantallaPrincipal() {
        Repositorio.inicializarDatos(); // Cargar investigadores y colaboradores de ejemplo

        Tab tabProyectos = new Tab("Crear Proyecto", crearVistaProyectos());
        Tab tabColaboradores = new Tab("Donaciones", crearVistaColaboradores());
        Tab tabInvestigadores = new Tab("Registrar Investigador", crearVistaInvestigadores());
        Tab tabMetas = new Tab("Meta de Proyectos", crearVistaMetaProyectos());

        this.getTabs().addAll(tabProyectos, tabColaboradores, tabInvestigadores, tabMetas);
        this.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
    }

    /*
     * Vista para crear nuevos proyectos (investigacion o emprendimiento).
     */
    private ScrollPane crearVistaProyectos() {
        VBox layout = new VBox(10);

        // Campos de entrada
        TextField nombre = new TextField();
        nombre.setPromptText("Nombre del proyecto");

        TextField descripcion = new TextField();
        descripcion.setPromptText("Descripcion");

        TextField url = new TextField();
        url.setPromptText("URL del video");

        ComboBox<Investigador> comboResponsable = new ComboBox<>();
        comboResponsable.getItems().addAll(Repositorio.investigadores);
        comboResponsable.setPromptText("Responsable");

        ComboBox<String> tipoProyecto = new ComboBox<>();
        tipoProyecto.getItems().addAll("Investigacion", "Emprendimiento");
        tipoProyecto.setPromptText("Tipo de proyecto");

        TextField monto = new TextField();
        monto.setPromptText("Monto meta (si es emprendimiento)");
        monto.disableProperty().bind(tipoProyecto.valueProperty().isNotEqualTo("Emprendimiento"));

        // Lista de colaboradores
        ListView<Colaborador> listaColabs = new ListView<>();
        listaColabs.getItems().addAll(Repositorio.colaboradores);
        listaColabs.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listaColabs.setPrefHeight(100);

        // Boton de accion
        Button btnCrear = new Button("Crear Proyecto");
        Label resultado = new Label();

        // Accion al crear proyecto
        btnCrear.setOnAction(e -> {
            String n = nombre.getText().trim();
            String d = descripcion.getText().trim();
            String u = url.getText().trim();
            Investigador r = comboResponsable.getValue();
            String tipo = tipoProyecto.getValue();

            if (n.isEmpty() || d.isEmpty() || u.isEmpty() || r == null || tipo == null) {
                resultado.setText("Todos los campos son obligatorios.");
                return;
            }

            Proyecto nuevo;
            if (tipo.equals("Investigacion")) {
                nuevo = new ProyectoInvestigacion(n, d, u, r);
            } else {
                try {
                    double m = Double.parseDouble(monto.getText().trim());
                    if (m <= 0) {
                        resultado.setText("Monto debe ser mayor que cero.");
                        return;
                    }
                    nuevo = new ProyectoEmprendimiento(n, d, u, r, m);
                } catch (NumberFormatException ex) {
                    resultado.setText("Monto invalido.");
                    return;
                }
            }

            // Agregar colaboradores, maximo 4
            List<Colaborador> seleccionados = listaColabs.getSelectionModel().getSelectedItems();
            if (seleccionados.size() > 4) {
                resultado.setText("No se pueden seleccionar mas de 4 colaboradores.");
                return;
            }
            for (Colaborador c : seleccionados) {
                if (!nuevo.getColaboradores().contains(c)) {
                    nuevo.agregarColaborador(c);
                }
            }

            Repositorio.proyectos.add(nuevo);
            resultado.setText("Proyecto creado correctamente.");
        });

        layout.getChildren().addAll(nombre, descripcion, url, comboResponsable, tipoProyecto, monto, listaColabs, btnCrear, resultado);
        layout.setPadding(new Insets(20));
        return new ScrollPane(layout);
    }

    /*
     * Vista para buscar colaboradores y realizar donaciones.
     */
    private ScrollPane crearVistaColaboradores() {
        VBox layout = new VBox(10);

        TextField nombre = new TextField();
        nombre.setPromptText("Nombre del colaborador");

        Button btnBuscar = new Button("Buscar");
        Label info = new Label();

        ListView<ProyectoEmprendimiento> listaProyectos = new ListView<>();
        listaProyectos.setPrefHeight(150);

        Button btnSeguir = new Button("Seguir proyecto");
        TextField monto = new TextField();
        monto.setPromptText("Monto a donar");

        Button btnDonar = new Button("Donar");

        // Accion para buscar colaborador
        btnBuscar.setOnAction(e -> {
            String nom = nombre.getText().trim();
            Colaborador c = Repositorio.colaboradores.stream()
                    .filter(x -> x.getNombre().equalsIgnoreCase(nom))
                    .findFirst().orElse(null);

            if (c == null) {
                info.setText("Colaborador no encontrado.");
                return;
            }

            info.setText("Colaborador encontrado.");

            List<ProyectoEmprendimiento> proyectosActivos = Repositorio.proyectos.stream()
                    .filter(p -> p instanceof ProyectoEmprendimiento)
                    .map(p -> (ProyectoEmprendimiento) p)
                    .filter(p -> !p.haAlcanzadoMeta())
                    .collect(Collectors.toList());

            listaProyectos.getItems().setAll(proyectosActivos);

            // Accion para seguir proyecto
            btnSeguir.setOnAction(ev -> {
                ProyectoEmprendimiento seleccionado = listaProyectos.getSelectionModel().getSelectedItem();
                if (seleccionado != null) {
                    c.seguirProyecto(seleccionado);
                    info.setText("Siguiendo proyecto: " + seleccionado.getNombre());
                }
            });

            // Accion para donar
            btnDonar.setOnAction(ev -> {
                ProyectoEmprendimiento seleccionado = listaProyectos.getSelectionModel().getSelectedItem();
                if (seleccionado != null) {
                    try {
                        double cantidad = Double.parseDouble(monto.getText().trim());
                        if (cantidad < 0) {
                            info.setText("No puede donar cifras negativas.");
                            return;
                        }
                        if (c.haSeguido(seleccionado)) {
                            if (c.donar(seleccionado, cantidad)) {
                                info.setText("Donacion exitosa.");
                                monto.clear();
                            } else {
                                info.setText("Error al procesar la donacion.");
                            }
                        } else {
                            info.setText("Asegurese de seguir el proyecto antes de donar.");
                        }
                    } catch (NumberFormatException ex) {
                        info.setText("Monto invalido. Introduzca un numero.");
                    }
                } else {
                    info.setText("Seleccione un proyecto para donar.");
                }
            });

        });

        layout.getChildren().addAll(nombre, btnBuscar, info, listaProyectos, btnSeguir, monto, btnDonar);
        layout.setPadding(new Insets(20));
        return new ScrollPane(layout);
    }

    /*
     * Vista para registrar nuevos investigadores.
     */
    private ScrollPane crearVistaInvestigadores() {
        VBox layout = new VBox(10);

        TextField nombre = new TextField();
        nombre.setPromptText("Nombre del investigador");

        TextField pagina = new TextField();
        pagina.setPromptText("Pagina web");

        TextField area = new TextField();
        area.setPromptText("Area de experiencia");

        Button btnRegistrar = new Button("Registrar Investigador");
        Label resultado = new Label();

        // Accion para registrar investigador
        btnRegistrar.setOnAction(e -> {
            String n = nombre.getText().trim();
            String p = pagina.getText().trim();
            String a = area.getText().trim();

            if (n.isEmpty() || p.isEmpty() || a.isEmpty()) {
                resultado.setText("Todos los campos son obligatorios.");
                return;
            }

            Investigador nuevo = new Investigador(n, p, a);
            Repositorio.investigadores.add(nuevo);
            resultado.setText("Investigador registrado: " + nuevo.getNombre());

            nombre.clear();
            pagina.clear();
            area.clear();
        });

        layout.getChildren().addAll(nombre, pagina, area, btnRegistrar, resultado);
        layout.setPadding(new Insets(20));
        return new ScrollPane(layout);
    }

    /*
     * Actualiza la lista de proyectos que aun no alcanzan su meta.
     */
    private void actualizarListaMeta(ListView<ProyectoEmprendimiento> lista) {
        lista.getItems().clear();
        for (Proyecto p : Repositorio.proyectos) {
            if (p instanceof ProyectoEmprendimiento) {
                ProyectoEmprendimiento proyecto = (ProyectoEmprendimiento) p;
                if (!proyecto.haAlcanzadoMeta()) {
                    lista.getItems().add(proyecto);
                }
            }
        }
    }

    /*
     * Vista que muestra proyectos que no han alcanzado su meta.
     */
    private ScrollPane crearVistaMetaProyectos() {
        VBox layout = new VBox(20);
        Label titulo = new Label("Proyectos que aun no alcanzan su meta:");

        ListView<ProyectoEmprendimiento> lista = new ListView<>();
        lista.setPrefHeight(300);
        actualizarListaMeta(lista);

        // Muestra informacion detallada del proyecto
        lista.setCellFactory(lv -> new ListCell<ProyectoEmprendimiento>() {
            @Override
            protected void updateItem(ProyectoEmprendimiento proyecto, boolean empty) {
                super.updateItem(proyecto, empty);
                if (empty || proyecto == null) {
                    setText(null);
                } else {
                    setText(String.format("%s - Meta: $%.2f, Recaudado: $%.2f, Faltante: $%.2f",
                            proyecto.getNombre(), proyecto.getMontoMeta(), proyecto.getMontoRecaudado(), proyecto.cantidadFaltante()));
                }
            }
        });

        // Doble clic para ver detalles
        lista.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                ProyectoEmprendimiento proyectoSeleccionado = lista.getSelectionModel().getSelectedItem();
                if (proyectoSeleccionado != null) {
                    mostrarDetallesProyecto(proyectoSeleccionado);
                }
            }
        });

        Button btnActualizar = new Button("Actualizar lista");
        btnActualizar.setOnAction(e -> actualizarListaMeta(lista));

        layout.getChildren().addAll(titulo, lista, btnActualizar);
        layout.setPadding(new Insets(20));
        return new ScrollPane(layout);
    }

    /*
     * Muestra una ventana con los detalles del proyecto seleccionado.
     */
    private void mostrarDetallesProyecto(ProyectoEmprendimiento proyecto) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Detalles del Proyecto");
        alert.setHeaderText(proyecto.getNombre());
        alert.setContentText("Descripcion: " + proyecto.getDescripcion() + "\n" +
                             "Meta: $" + proyecto.getMontoMeta() + "\n" +
                             "Recaudado: $" + proyecto.getMontoRecaudado() + "\n" +
                             "Faltante: $" + proyecto.cantidadFaltante() + "\n" +
                             "Responsable: " + proyecto.getResponsable().getNombre());
        alert.showAndWait();
    }
}
