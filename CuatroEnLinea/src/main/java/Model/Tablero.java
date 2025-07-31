package Model;

public class Tablero {
    
    private final char[][] tablero;
    // Constantes que definen el tamaño del tablero
    public static final int COLUMNAS = 7;
    public static final int FILAS = 6;

    // Constructor: crea un nuevo tablero vacío
    public Tablero() {
        tablero = new char[FILAS][COLUMNAS];
        inicializar(); // Inicializa con todas las casillas vacías
    }

    // Llena el tablero con guiones '-', indicando casillas vacías
    public void inicializar() {
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                tablero[i][j] = '-';
            }
        }
    }

    // Devuelve el estado actual del tablero
    public char[][] getTablero() {
        return tablero;
    }

    // Coloca una ficha en la columna indicada y devuelve la fila donde cayó
    public int colocarFicha(int columna, char color) {
        // Verifica si la columna es válida
        if (columna < 0 || columna >= COLUMNAS) return -1;

        // Recorre la columna desde abajo hacia arriba
        for (int i = FILAS - 1; i >= 0; i--) {
            if (tablero[i][columna] == '-') {
                tablero[i][columna] = color; // Coloca la ficha del color del jugador
                return i; // Devuelve la fila donde se colocó la ficha
            }
        }
        return -1; // Si no hay espacio en la columna, devuelve -1
    }

    // Comprueba si el jugador actual ganó al colocar su ficha
    public boolean comprobarGanador(int fila, int columna, char color) {
        // Verifica si hay 4 en línea en alguna de las 4 direcciones posibles
        return verificarDireccion(fila, columna, 0, 1, color) ||   // Horizontal
               verificarDireccion(fila, columna, 1, 0, color) ||   // Vertical
               verificarDireccion(fila, columna, 1, 1, color) ||   // Diagonal principal \
               verificarDireccion(fila, columna, -1, 1, color);    // Diagonal secundaria /
    }

    // Verifica si hay al menos 4 fichas consecutivas en una dirección específica
    private boolean verificarDireccion(int fila, int col, int df, int dc, char jugador) {
        int cuenta = 1; // Cuenta la ficha recién colocada

        // Suma las fichas iguales en la dirección positiva y negativa
        cuenta += contarFichas(fila, col, df, dc, jugador);      // Dirección hacia adelante
        cuenta += contarFichas(fila, col, -df, -dc, jugador);    // Dirección hacia atrás

        return cuenta >= 4; // Devuelve true si hay al menos 4 en línea
    }

    // Cuenta cuántas fichas consecutivas hay del mismo jugador en una dirección
    private int contarFichas(int fila, int col, int df, int dc, char jugador) {
        int cuenta = 0;
        int i = fila + df, j = col + dc;

        // Mientras esté dentro del tablero y la ficha sea del mismo jugador
        while (i >= 0 && i < FILAS && j >= 0 && j < COLUMNAS && tablero[i][j] == jugador) {
            cuenta++;
            i += df;
            j += dc;
        }

        return cuenta;
    }
}
