/*
 * clase cliente para agregar un cliente a la lista con el tipo de cuenta
 */
package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
	private String nombre;
	private String telefono;
	private List<Cuenta> cuentas = new ArrayList<>();

	public Cliente(String nombre, String telefono) {
		this.nombre = nombre;
		this.telefono = telefono;
	}

	public void agregarCuenta(Cuenta c) {
		cuentas.add(c);
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public String getNombre() {
		return nombre;
	}

	public String toString() {
		return nombre + " (" + telefono + ")";
	}
}