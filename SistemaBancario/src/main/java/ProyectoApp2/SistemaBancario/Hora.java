package ProyectoApp2.SistemaBancario;
/*
 * Singleton para la hora y fecha actuales
 */
import java.time.LocalDateTime;

public class Hora {
	private static Hora instancia;

	private Hora() {
	}

	
	// devuelve la unica instancia de hora 
	public static Hora getInstancia() {
		if (instancia == null) {
			instancia = new Hora();
		}
		return instancia;
	}

	//devuelve la hora actual
	public LocalDateTime today() {
		return LocalDateTime.now();
	}
	//devuelve el año de la fecha dada 
	public int year(LocalDateTime fecha) {
		return fecha.getYear();
	}
	
}
	