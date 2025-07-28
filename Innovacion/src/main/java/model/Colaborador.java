package model;

public class Colaborador extends Usuario {
    private double totalDonado;

    public Colaborador(String nombre) {
        super(nombre);
        this.totalDonado = 0.0; // Inicializamos en 0 al crear un nuevo colaborador
    }

    public double getTotalDonado() {
        return totalDonado;
    }

    // Método para agregar una donación al total donado por el colaborador
    public void agregarDonacion(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto de la donación debe ser positivo.");
        }
        this.totalDonado += monto;
    }

    // No se permite setTotalDonado directamente para asegurar la consistencia.
    // El totalDonado se actualiza a través de agregarDonacion.

    @Override
    public String toString() {
        return "Colaborador{" +
               "nombre='" + getNombre() + '\'' +
               ", totalDonado=" + totalDonado +
               '}';
    }
}