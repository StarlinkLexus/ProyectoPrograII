package ProyectoApp2.SistemaBancario;

public class CajaAhorro extends Cuenta {
	int movAnuales;
	Cliente cliente;

	public CajaAhorro(int numero, double saldo, int movAnuales, Cliente cliente) {
		super(numero, saldo);
		this.movAnuales = movAnuales;
		this.cliente = cliente;
	}

	/*
	 * Verifica si se puede realizar un retiro, que haya saldo suficiente y que no
	 * se haya excedido el numero de movimientos anuales.
	 */

	@Override
	public boolean checkCuenta(double monto) {
		int yearActual = Hora.getInstancia().year(Hora.getInstancia().today());
		long cantidadMovimientos = transacciones.stream()
				.filter(t -> Hora.getInstancia().year(t.getFecha()) == yearActual).count();

		if (cantidadMovimientos >= movAnuales)
			return false;
		return saldo >= monto;
	}

}
