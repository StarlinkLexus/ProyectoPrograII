package Modelo;


//representa una direccion de movimiento en el tablero.

public class Direccion {
	
	  DireccionTipo tipo;
	  
        // Constructor
	    public Direccion(DireccionTipo tipo) {
	        this.tipo = tipo;
	    }
       //Getters ands setters
	    
	    public DireccionTipo getTipo() {
	        return tipo;
	    }
	    
      //Devuelve el cambio en la fila asociado a esta direccion.
	    
	    public int MovimientoEnFila() {
	        switch (tipo) {
	            case NORTE:
	                return -1;
	            case SUR:
	                return 1;
	            default:
	                return 0;
	        }
	    }
     //Devuelve el cambio en la columna asociado a esta dirección.
	    public int MovimientoEnColumna() {
	        switch (tipo) {
	            case ESTE:
	                return 1;
	            case OESTE:
	                return -1;
	            default:
	                return 0;
	        }
	    }
       //Crea una instancia de Direccion a partir de una tecla numérica.
	    public static Direccion desdeTecla(int tecla) {
	        switch (tecla) {
	            case 1:
	                return new Direccion(DireccionTipo.NORTE);
	            case 2:
	                return new Direccion(DireccionTipo.SUR);
	            case 3:
	                return new Direccion(DireccionTipo.ESTE);
	            case 4:
	                return new Direccion(DireccionTipo.OESTE);
	            default:
	                return null;
	        }
	    }
}
