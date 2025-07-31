package Model;

public class Ficha {
	
	private final char color;
	
	// Constructor: Recibe un carácter que representa el color y lo asigna al atributo.
	public Ficha(char color) {   
		this.color = color;
	}

	// Método que devuelve el color de la ficha
	public char getColor() {
		return color;
	}
}
