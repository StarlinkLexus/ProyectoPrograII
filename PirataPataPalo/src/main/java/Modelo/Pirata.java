package Modelo;

//Esta clase permite conocer su ubicacion actual y moverlo en una direccion al pirata .

public class Pirata {
	int fila;
	int columna;

	public Pirata(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}

	public int getFila() {
		return fila;
	}

	public int getColumna() {
		return columna;
	}

//mueve al pirata en la direccion que se indica 
	public void mover(Direccion dir) {
		switch (dir.getTipo()) {
		case NORTE:
			fila--;
			break;
		case SUR:
			fila++;
			break;
		case ESTE:
			columna++;
			break;
		case OESTE:
			columna--;
			break;
		}
	}

}
