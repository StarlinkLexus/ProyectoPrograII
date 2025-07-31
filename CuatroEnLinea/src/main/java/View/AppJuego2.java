package View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


public class AppJuego2 extends Application {

	// Constantes para las dimensiones del tablero
    private static final int FILAS = 6;
    private static final int COLUMNAS = 7;

    // Arreglo bidimensional que representa el estado del tablero
    private final char[][] tablero = new char[FILAS][COLUMNAS];

    // Variable para llevar el turno actual ('R' para rojo, 'B' para blanco)
    private char turno = 'R';

    // Componente gráfico que representa el tablero visual
    private final GridPane grid = new GridPane();

    // Contadores de rondas ganadas por cada jugador
    private int rondasRojo = 0;
    private int rondasBlanco = 0;

    // Etiqueta para mostrar el marcador
    private final Label marcador = new Label();

    // Método main que lanza la aplicación JavaFX
    public static void main(String[] args) {
        launch(args);
    }

    // Método que configura y muestra la interfaz gráfica
    @Override
    public void start(Stage stage) {
        inicializarTablero(); // Llena el tablero con casillas vacías
        dibujarTablero();     // Dibuja el tablero en pantalla

        // Botón que reinicia el juego sin cerrar la aplicación
        Button botonReiniciar = new Button("Reiniciar Juego");
        botonReiniciar.setOnAction(e -> reiniciarJuego());

        // Botón para salir del juego
        Button botonSalir = new Button("Salir");
        botonSalir.setOnAction(e -> Platform.exit());

        // Contenedor vertical que organiza el marcador, tablero y botones
        VBox contenido = new VBox(10);
        contenido.setAlignment(Pos.CENTER);
        contenido.getChildren().addAll(marcador, grid, botonReiniciar, botonSalir);

        // Configuración de proporciones de columnas y filas en el GridPane
        for (int i = 0; i < COLUMNAS; i++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(100.0 / COLUMNAS);
            cc.setFillWidth(true);
            cc.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(cc);
        }
        for (int i = 0; i < FILAS; i++) {
            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(100.0 / FILAS);
            rc.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(rc);
        }

        // Coloca el contenido en el centro de la escena
        BorderPane root = new BorderPane();
        root.setCenter(contenido);
        actualizarMarcador(); // Muestra el marcador inicial

        // Crea y muestra la ventana
        Scene scene = new Scene(root, 600, 650);
        stage.setTitle("4 en Línea - JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    // Llena el arreglo del tablero con guiones para representar casillas vacías
    private void inicializarTablero() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                tablero[i][j] = '-';
            }
        }
    }

    // Dibuja las casillas y fichas del tablero en la interfaz
    private void dibujarTablero() {
        grid.getChildren().clear(); // Limpia el tablero gráfico

        for (int fila = 0; fila < FILAS; fila++) {
            for (int col = 0; col < COLUMNAS; col++) {
                // Casilla azul con borde negro
                Rectangle casilla = new Rectangle(80, 80);
                casilla.setFill(Color.BLUE);
                casilla.setStroke(Color.BLACK);

                // Círculo que representa la ficha (puede ser rojo, blanco o gris)
                Circle ficha = new Circle(30);
                ficha.setFill(obtenerColorFicha(tablero[fila][col]));
                ficha.setMouseTransparent(true); // Permite hacer clic a través del círculo

                int columnaFinal = col;
                // Maneja clic en una casilla
                casilla.setOnMouseClicked(e -> manejarClickColumna(columnaFinal));

                // Apila la casilla y la ficha en un StackPane
                StackPane celda = new StackPane(casilla, ficha);
                grid.add(celda, col, fila); // Agrega la celda al GridPane
            }
        }
    }

    // Devuelve el color visual de la ficha según el carácter del jugador
    private Color obtenerColorFicha(char jugador) {
        switch (jugador) {
            case 'R':
                return Color.RED;
            case 'B':
                return Color.WHITE;
            default:
                return Color.LIGHTGRAY; // Si está vacía
        }
    }

    // Maneja el evento cuando el usuario hace clic en una columna
    private void manejarClickColumna(int columna) {
        int fila = colocarFicha(columna); // Intenta colocar la ficha en la columna
        if (fila == -1) return; // Si la columna está llena, no hace nada

        // Verifica si el jugador actual ganó después de colocar la ficha
        if (comprobarGanador(fila, columna, turno)) {
            dibujarTablero(); // Redibuja para mostrar la ficha final
            
            char ganador = turno;
            
            // Aumenta el marcador del jugador ganador
            if (ganador == 'R') {
                rondasRojo++;
            } else {
                rondasBlanco++;
            }

            actualizarMarcador(); // Muestra el nuevo marcador
            
            mostrarMensaje("¡Ganó el jugador " + (turno == 'R' ? "Rojo!" : "Blanco!"));
            return;
       
        }

        // Cambia el turno al otro jugador
        turno = (turno == 'R') ? 'B' : 'R';
        dibujarTablero(); // Redibuja el tablero con la nueva ficha
    }

    // Coloca la ficha en la columna seleccionada (de abajo hacia arriba)
    private int colocarFicha(int columna) {
        for (int fila = FILAS - 1; fila >= 0; fila--) {
            if (tablero[fila][columna] == '-') {
                tablero[fila][columna] = turno;
                return fila; // Devuelve la fila donde se colocó la ficha
            }
        }
        return -1; // Si no hay espacio en esa columna
    }

    // Verifica si el jugador ha hecho 4 en línea
    private boolean comprobarGanador(int fila, int col, char jugador) {
        return verificarDireccion(fila, col, 0, 1, jugador) ||  // Horizontal
               verificarDireccion(fila, col, 1, 0, jugador) ||  // Vertical
               verificarDireccion(fila, col, 1, 1, jugador) ||  // Diagonal \
               verificarDireccion(fila, col, -1, 1, jugador);   // Diagonal /
    }

    // Verifica en ambas direcciones de una línea si hay 4 fichas consecutivas
    private boolean verificarDireccion(int fila, int col, int df, int dc, char jugador) {
        int cuenta = 1;
        cuenta += contarFichas(fila, col, df, dc, jugador);   // En una dirección
        cuenta += contarFichas(fila, col, -df, -dc, jugador); // En la opuesta
        return cuenta >= 4; // Si hay 4 o más, gana
    }

    // Cuenta cuántas fichas consecutivas hay en una dirección específica
    private int contarFichas(int fila, int col, int df, int dc, char jugador) {
        int cuenta = 0;
        int i = fila + df, j = col + dc;
        while (i >= 0 && i < FILAS && j >= 0 && j < COLUMNAS && tablero[i][j] == jugador) {
            cuenta++;
            i += df;
            j += dc;
        }
        return cuenta;
    }

    // Muestra un mensaje al ganar, y luego reinicia el juego
    private void mostrarMensaje(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Fin del Juego");
        alert.setContentText(mensaje);
        alert.showAndWait();

        reiniciarJuego(); // Reinicia el tablero
    }

    // Reinicia el juego pero conserva los puntajes
    private void reiniciarJuego() {
        inicializarTablero();
        turno = 'R'; // Empieza el jugador rojo
        dibujarTablero(); // Redibuja el tablero vacío
    }

    // Actualiza el texto del marcador que muestra las rondas ganadas
    private void actualizarMarcador() {
        marcador.setText("MARCADOR - Jugador Rojo: " + rondasRojo + " | Jugador Blanco: " + rondasBlanco);
    }


}