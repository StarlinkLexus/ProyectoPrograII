package model;

import java.util.ArrayList;
import java.util.List;

public class ProyectoEmprendimiento extends Proyecto {
    private double montoTotalAFinanciar;
    private double montoRecibido;
    private List<Donacion> donaciones; // Lista de donaciones recibidas

    public ProyectoEmprendimiento(String nombre, String descripcion, String urlVideoExplicativo, Investigador investigadorResponsable, double montoTotalAFinanciar) {
        super(nombre, descripcion, urlVideoExplicativo, investigadorResponsable);
        if (montoTotalAFinanciar <= 0) {
            throw new IllegalArgumentException("El monto total a financiar debe ser positivo.");
        }
        this.montoTotalAFinanciar = montoTotalAFinanciar;
        this.montoRecibido = 0.0; // Inicialmente no se ha recibido dinero
        this.donaciones = new ArrayList<>();
    }

    public double getMontoTotalAFinanciar() {
        return montoTotalAFinanciar;
    }

    // No hay un setter directo para montoTotalAFinanciar si se asume que se define al crear.
    // Si se necesita modificar, se añadiría un setter con validación.

    public double getMontoRecibido() {
        return montoRecibido;
    }

    public List<Donacion> getDonaciones() {
        return new ArrayList<>(donaciones); // Devuelve una copia
    }

    /**
     * Agrega una donación al proyecto de emprendimiento y actualiza el monto recibido.
     * También actualiza el total donado del colaborador.
     * @param donacion La donación a agregar.
     * @throws IllegalArgumentException si el monto de la donación es inválido o excede el monto a financiar.
     */
    public void agregarDonacion(Donacion donacion) {
        if (donacion == null) {
            throw new IllegalArgumentException("La donación no puede ser nula.");
        }
        if (donacion.getMonto() <= 0) {
            throw new IllegalArgumentException("El monto de la donación debe ser positivo.");
        }
        if (this.montoRecibido + donacion.getMonto() > this.montoTotalAFinanciar) {
            throw new IllegalArgumentException("La donación excede el monto total a financiar restante. Máximo a donar: " + (this.montoTotalAFinanciar - this.montoRecibido));
        }

        this.donaciones.add(donacion);
        this.montoRecibido += donacion.getMonto();

        // Actualizar el total donado del colaborador
        if (donacion.getColaborador() != null) {
            donacion.getColaborador().agregarDonacion(donacion.getMonto());
        }
    }

    public double getMontoRestante() {
        return montoTotalAFinanciar - montoRecibido;
    }

    @Override
    public String toString() {
        return "ProyectoEmprendimiento{" +
               "nombre='" + getNombre() + '\'' +
               ", descripcion='" + getDescripcion() + '\'' +
               ", montoTotalAFinanciar=" + montoTotalAFinanciar +
               ", montoRecibido=" + montoRecibido +
               '}';
    }
}