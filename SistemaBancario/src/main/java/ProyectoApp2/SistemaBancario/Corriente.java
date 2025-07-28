package ProyectoApp2.SistemaBancario;

public class Corriente extends Cuenta {
	double limiteDescubierto;

	public Corriente(int numero, double saldo, double limiteDescubierto) {
		super(numero, saldo);
		this.limiteDescubierto = limiteDescubierto;	
	}

	/*
	 * Solo permite retiros hasta el limite 
	 */
	@Override
	public boolean checkCuenta(double monto) {
		return saldo >= -limiteDescubierto;

	}
}
