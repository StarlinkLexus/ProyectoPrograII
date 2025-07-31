/*
 * clase CuentaCorriente con limite de monto y un super cliente para jalar datos
 */
package Modelo;

public class CuentaCorriente extends Cuenta {
	private final double LIMITE_DESCUBIERTO = -500.0;

	public CuentaCorriente(Cliente cliente) {
		super(cliente);
	}

	@Override
	public boolean checkCuenta(double monto) {
		return (saldo - monto) >= LIMITE_DESCUBIERTO;
	}
}