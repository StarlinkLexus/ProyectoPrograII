package ProyectoApp2.SistemaBancario;

import java.util.ArrayList;
import java.util.List;

/*
 * Clase que representa cuenta bancaria
 */

public abstract class Cuenta {
	int numero;
	double saldo;
	List<Transaccion> transacciones = new ArrayList<>();

	public Cuenta(int numero, double saldo) {
		super();
		this.numero = numero;
		this.saldo = saldo;
	}
	
	/*
	 * Realizar un deposito en la cuenta
	 */
	public double deposito(double monto, Cliente cliente) {
		saldo += monto;
		transacciones.add(new Transaccion(monto, TipoTransaccion.Deposito, Hora.getInstancia().today(), cliente));
		return saldo;	
	}

	/*
	 * Retiro de la cuenta, primero verificamos las restricciones
	 */
	public double retiro(double monto, Cliente cliente) {
		if(checkCuenta(monto)) {
			saldo -= monto;
			transacciones.add(new Transaccion(monto, TipoTransaccion.Retiro, Hora.getInstancia().today(), cliente));
			return saldo;
		}else {
			throw new RuntimeException("Restriccion Violada!.");
		}
	}
	
	/*
	 * Metodo para verificar restricciones antes del retiro
	 */
	public abstract boolean checkCuenta(double monto);
}
