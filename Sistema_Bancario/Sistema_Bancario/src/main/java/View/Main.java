package View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;
import Modelo.CajaAhorro;
import Modelo.Cliente;
import Modelo.Cuenta;
import Modelo.CuentaCorriente;

//Clase principal que extiende Application para crear una aplicación JavaFX
public class Main extends Application {

 private Cliente cliente; // Almacena el cliente actual registrado
 private ComboBox<Cuenta> cuentaCombo = new ComboBox<>(); // Desplegable para seleccionar una cuenta
 private TextArea historial = new TextArea(); // Área de texto para mostrar el historial de operaciones

 // Método principal que lanza la aplicación JavaFX
 public static void main(String[] args) {
     launch(args);
 }

 // Método que se ejecuta al iniciar la aplicación
 @Override
 public void start(Stage stage) {

     // Sección para registrar un nuevo cliente


     TextField nombre = new TextField(); // Campo para ingresar el nombre del cliente
     TextField telefono = new TextField(); // Campo para ingresar el teléfono del cliente
     Button registrar = new Button("Registrar"); // Botón para registrar al cliente

     VBox clienteBox = new VBox(5, new Label("Nombre:"), nombre, new Label("Teléfono:"), telefono, registrar);
     clienteBox.setPadding(new Insets(10)); // Espaciado interior


     // Sección para crear una nueva cuenta


     ComboBox<String> tipoCuenta = new ComboBox<>(); // Desplegable para seleccionar el tipo de cuenta
     tipoCuenta.getItems().addAll("Caja Ahorro", "Cuenta Corriente"); // Opciones de tipo de cuenta
     Button agregarCuenta = new Button("Agregar Cuenta"); // Botón para agregar la cuenta

     VBox cuentaBox = new VBox(5, new Label("Tipo de Cuenta:"), tipoCuenta, agregarCuenta,
             new Label("Seleccionar Cuenta:"), cuentaCombo);
     cuentaBox.setPadding(new Insets(10));
     cuentaBox.setDisable(true); // Se desactiva hasta que se registre un cliente


     // Sección para operaciones bancarias


     TextField monto = new TextField(); // Campo para ingresar el monto
     Button depositar = new Button("Depositar"); // Botón para realizar depósito
     Button retirar = new Button("Retirar"); // Botón para realizar retiro

     VBox operacionBox = new VBox(5, new Label("Monto:"), monto, depositar, retirar);
     operacionBox.setPadding(new Insets(10));
     operacionBox.setDisable(true); // Se desactiva hasta que se cree una cuenta

     // Configuración del área de historial
     historial.setEditable(false); // Solo lectura
     historial.setPrefHeight(150); // Altura del área de historial


     // Lógica de los botones
 

     // Botón registrar: crea un nuevo cliente y habilita la sección de cuentas
     registrar.setOnAction(e -> {
         cliente = new Cliente(nombre.getText(), telefono.getText());
         cuentaBox.setDisable(false); // Habilita la sección de creación de cuentas
     });

     // Botón agregarCuenta: crea una nueva cuenta del tipo seleccionado y la asocia al cliente
     agregarCuenta.setOnAction(e -> {
         String tipo = tipoCuenta.getValue(); // Obtener tipo de cuenta
         if (tipo != null && cliente != null) {
             // Crear la cuenta dependiendo del tipo
             Cuenta cuenta = tipo.equals("Caja Ahorro") ? new CajaAhorro(cliente) : new CuentaCorriente(cliente);
             cliente.agregarCuenta(cuenta); // Asociar cuenta al cliente
             cuentaCombo.getItems().add(cuenta); // Añadir la cuenta al ComboBox
             cuentaCombo.getSelectionModel().select(cuenta); // Seleccionarla automáticamente
             operacionBox.setDisable(false); // Habilita la sección de operaciones
             historial.appendText("Cuenta creada: " + cuenta + "\n");
         }
     });

     // Botón depositar: realiza un depósito en la cuenta seleccionada
     depositar.setOnAction(e -> {
         try {
             double valor = Double.parseDouble(monto.getText()); // Convertir el monto a número
             Cuenta c = cuentaCombo.getValue(); // Obtener la cuenta seleccionada
             c.deposito(valor); // Ejecutar depósito
             historial.appendText("Depósito: $" + valor + " -> Saldo: $" + c.getSaldo() + "\n" );
         } catch (Exception ex) {
             historial.appendText("Error: " + ex.getMessage() + "\n");
         }
     });

     // Botón retirar: realiza un retiro en la cuenta seleccionada
     retirar.setOnAction(e -> {
         try {
             double valor = Double.parseDouble(monto.getText()); // Convertir el monto a número
             Cuenta c = cuentaCombo.getValue(); // Obtener la cuenta seleccionada
             c.retiro(valor); // Ejecutar retiro
             historial.appendText("Retiro: $" + valor + " -> Saldo: $" + c.getSaldo() + "\n");
         } catch (Exception ex) {
             historial.appendText("Error: " + ex.getMessage() + "\n");
         }
     });

     // Configuración de la escena y del escenario


     VBox root = new VBox(10, clienteBox, cuentaBox, operacionBox, new Label("Historial:"), historial);
     root.setPadding(new Insets(15)); // Espaciado general
     
     root.setStyle("-fx-background-color: #FFDD00");

     stage.setScene(new Scene(root, 450, 700)); // Crear escena con tamaño fijo
     stage.setTitle("BANCO PICHINCHA-BancaMovil"); // Título de la ventana
     stage.show(); // Mostrar la ventana
 }
}
