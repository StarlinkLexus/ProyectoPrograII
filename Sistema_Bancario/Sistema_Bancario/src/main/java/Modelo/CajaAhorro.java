package Modelo;

public class CajaAhorro extends Cuenta {
	private int transacciones = 0;
	private final int LIMITE = 5;

	public CajaAhorro(Cliente cliente) {
		super(cliente);
	}

	/*
	 * metodo apra analizar el limite de la transaccion
	 */
	@Override
	public boolean checkCuenta(double monto) {
		return saldo >= monto && transacciones < LIMITE;
	}

	/*
	 * metodo para el retiro de dinero con excepciones de limite y numero negativo
	 */
	@Override
	public void retiro(double monto) {
		if (transacciones >= LIMITE && monto < 0) {
			throw new RuntimeException("SALDO INSUFICIENTE");
		} else if (monto <= 0) {
			throw new IllegalArgumentException("EL MONTO DEL RETIRO DEBE SER POSITIVO.");
		}
		super.retiro(monto);
		transacciones++;
	}

	/*
	 * metodo para el deposito de dinero con excepciones de limite y numero negativo
	 */
	@Override
	public void deposito(double monto) {
		if (transacciones >= LIMITE && monto < 0) {
			throw new RuntimeException("SALDO INSUFICIENTE");
		} else if (monto <= 0) {
			throw new IllegalArgumentException("EL MONTO DEBE SER POSITIVO");
		}
		super.deposito(monto);
		transacciones++;
	}
}