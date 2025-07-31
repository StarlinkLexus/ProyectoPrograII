package View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import Modelo.*;
import App.*;

public class AppJuego extends Application {
	private int contadorMovimientos;
	private int Limite_movimientos = 5;
	private int TAM = Configuracion.TAM;// tamano del tablero
	private int SIZE = Configuracion.SIZE;// tamano pixeles de cada Casilla

	private Tablero tablero;
	private GridPane grid;
	private Scene escenaJuego;

	@Override
	public void start(Stage stage) {
		// boton "Iniciar juego"
		Button iniciarBtn = new Button("Iniciar juego");
		StackPane inicioRoot = new StackPane(iniciarBtn);
		Scene escenaInicio = new Scene(inicioRoot, TAM * SIZE, TAM * SIZE);

		stage.setTitle("Pirata Pata de Palo");
		stage.setScene(escenaInicio);
		stage.show();

		iniciarBtn.setOnAction(e -> iniciarJuego(stage));
	}

	private void iniciarJuego(Stage stage) {
		tablero = new Tablero(TAM);
		grid = new GridPane();
		BorderPane root = new BorderPane();

		// Botones Reiniciar y Salir
		Button btnReiniciar = new Button("Reiniciar juego");
		btnReiniciar.setOnAction(e -> reiniciarJuego());

		Button btnSalir = new Button("Salir");
		btnSalir.setOnAction(e -> {
			System.out.println("Saliendo del juego...");
			stage.close(); // Cierra
			contadorMovimientos = 0;
		});

		// Agrupar botones horizontalmente
		HBox hboxTop = new HBox(10, btnReiniciar, btnSalir);
		root.setTop(hboxTop);
		root.setCenter(grid);

		escenaJuego = new Scene(root, TAM * SIZE, TAM * SIZE);
		escenaJuego.setOnKeyPressed(this::manejarMovimiento);

		dibujarTablero();

		stage.setScene(escenaJuego);
		stage.show();
	}

	private void manejarMovimiento(KeyEvent e) {
		int tecla = -1;
		switch (e.getText()) {
		case "1":
			tecla = 1;
			break;
		case "2":
			tecla = 2;
			break;
		case "3":
			tecla = 3;
			break;
		case "4":
			tecla = 4;
			break;
		}
		// Mueve al pirata
		Direccion dir = Direccion.desdeTecla(tecla);
		if (dir != null) {
			boolean seMovio = tablero.moverPirata(dir);
			if (seMovio) {
				contadorMovimientos++;
			}
			dibujarTablero();

			if (tablero.haEncontradoTesoro()) {
				mostrarAlerta("Victoria", "El pirata encontro el tesoro ");
				reiniciarJuego();
			} else if (tablero.estaAhogado()) {
				mostrarAlerta("Perdiste", "El pirata cayo al agua");
				reiniciarJuego();
			} else if (contadorMovimientos >= Limite_movimientos) {
				mostrarAlerta("Perdiste", "El pirata cayo al agua");
				reiniciarJuego();
			}
		}
	}

	// Muestra la alerta si gana o pierde
	private void mostrarAlerta(String titulo, String mensaje) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		alert.showAndWait();
	}

	private void reiniciarJuego() {
		tablero = new Tablero(TAM);
		dibujarTablero();
		contadorMovimientos = 0;
	}

	private void dibujarTablero() {
		grid.getChildren().clear();
		Casilla[][] casillas = tablero.getCasillas();

		for (int i = 0; i < TAM; i++) {
			for (int j = 0; j < TAM; j++) {
				Rectangle rect = new Rectangle(SIZE, SIZE);
				Casilla c = casillas[i][j];

				if (c.tienePirata())
					rect.setFill(Color.SADDLEBROWN);
				else if (c.tieneTesoro())
					rect.setFill(Color.GOLD);
				else if (c.tienePuente())
					rect.setFill(Color.BURLYWOOD);
				else
					rect.setFill(Color.LIGHTBLUE);

				rect.setStroke(Color.BLACK);
				grid.add(rect, j, i);

			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
