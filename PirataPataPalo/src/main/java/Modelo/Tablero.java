package Modelo;

import java.util.Random;

//Clase que representa el tablero del juego y controla la inicialización del juego, el movimiento del pirata
public class Tablero {
	private int N;// Tamano del tablero
	private Casilla[][] casillas;
	private Pirata pirata;
	private int filaTesoro, colTesoro;

	public Tablero(int N) {
		this.N = N;
		inicializarTablero();
	}

	// inicializa el tabllero
	public void inicializarTablero() {
		casillas = new Casilla[N][N];
		// todas las casiilas son agua
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				casillas[i][j] = new Casilla();
			}
		}

		// Puentes fijos en esquinas
		casillas[0][0].cambiarEstado(EstadoCasilla.PUENTE);
		casillas[N - 1][N - 1].cambiarEstado(EstadoCasilla.PUENTE);

		// Tesoro en casilla aleatoria pero no en un puente
		Random rand = new Random();
		do {
			filaTesoro = rand.nextInt(N);
			colTesoro = rand.nextInt(N);
		} while (casillas[filaTesoro][colTesoro].tienePuente());

		casillas[filaTesoro][colTesoro].cambiarEstado(EstadoCasilla.TESORO);

		// Pirata en casilla aleatoria distinta al tesoro
		int filaPirata, colPirata;
		do {
			filaPirata = rand.nextInt(N);
			colPirata = rand.nextInt(N);
		} while ((filaPirata == filaTesoro && colPirata == colTesoro) || casillas[filaPirata][colPirata].tienePuente());

		pirata = new Pirata(filaPirata, colPirata);
		casillas[filaPirata][colPirata].cambiarEstado(EstadoCasilla.PIRATA);
	}

	public Casilla[][] getCasillas() {
		return casillas;
	}

	public Pirata getPirata() {
		return pirata;
	}

	// Mueve al pirata en la dirección indicada
	public boolean moverPirata(Direccion direccion) {
		int nuevaFila = pirata.getFila() + direccion.MovimientoEnFila();
		int nuevaCol = pirata.getColumna() + direccion.MovimientoEnColumna();

		// movimientos que esten dentro del tablero
		if (nuevaFila < 0 || nuevaFila >= N || nuevaCol < 0 || nuevaCol >= N) {
			return false;
		}

		// Limpia al pirata anterior
		casillas[pirata.getFila()][pirata.getColumna()].cambiarEstado(EstadoCasilla.LIMPIAR);

		// Mueve al pirata
		pirata.mover(direccion);

		// Colocar nuevo pirata
		casillas[pirata.getFila()][pirata.getColumna()].cambiarEstado(EstadoCasilla.PIRATA);

		return true;
	}

	// Verifica si el pirata se encuentra sobre la casilla del tesoro.
	public boolean haEncontradoTesoro() {
		return pirata.getFila() == filaTesoro && pirata.getColumna() == colTesoro;
	}

	// Verifica si el pirata esta sobre una casilla de agua sin puente
	public boolean estaAhogado() {
		Casilla c = casillas[pirata.getFila()][pirata.getColumna()];
		return c.esAgua() && !c.tienePuente();
	}

}
