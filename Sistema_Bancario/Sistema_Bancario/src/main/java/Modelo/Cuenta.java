/*
 * clase cuenta abstracta para el uso de datos con list y arraylist para el tipo de cuenta y el tipo de transaccion con varias excepciones 
 */
package Modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Cuenta {
	protected double saldo;
	protected Cliente cliente;
	protected List<Transaccion> transacciones = new ArrayList<>();
	private static int contador = 1000;
	protected int numeroCuenta;

	public Cuenta(Cliente cliente) {
		this.cliente = cliente;
		this.numeroCuenta = contador++;
		this.saldo = 0.0;
	}

	public void deposito(double monto) {
		saldo += monto;
		transacciones.add(new Transaccion("DEPÓSITO", monto));
	}
// restricion de verificacion de monto en la cuenta para retiro

	public void retiro(double monto) {
		if (!checkCuenta(monto)) {
			throw new RuntimeException("ERROR AL MOMENTO DE LA TRANSACCION.");
		}
		saldo -= monto;
		transacciones.add(new Transaccion("RETIRO", monto));
	}

	public abstract boolean checkCuenta(double monto);

	public double getSaldo() {
		return saldo;
	}

	public String toString() {
		return getClass().getSimpleName() + " #" + numeroCuenta;
	}
}