package model;

import java.time.LocalDate;
import java.util.Objects;

public class Donacion {
    private double monto;
    private LocalDate fecha;
    private Colaborador colaborador; // Quién hizo la donación
    private ProyectoEmprendimiento proyectoDonado; // A qué proyecto se donó

    public Donacion(double monto, Colaborador colaborador, ProyectoEmprendimiento proyectoDonado) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto de la donación debe ser positivo.");
        }
        if (colaborador == null) {
            throw new IllegalArgumentException("El colaborador no puede ser nulo.");
        }
        if (proyectoDonado == null) {
            throw new IllegalArgumentException("El proyecto donado no puede ser nulo.");
        }

        this.monto = monto;
        this.fecha = LocalDate.now(); // La fecha se registra automáticamente al momento de la creación
        this.colaborador = colaborador;
        this.proyectoDonado = proyectoDonado;
    }

    public double getMonto() {
        return monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public ProyectoEmprendimiento getProyectoDonado() {
        return proyectoDonado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Donacion donacion = (Donacion) o;
        return Double.compare(monto, donacion.monto) == 0 &&
               Objects.equals(fecha, donacion.fecha) &&
               Objects.equals(colaborador, donacion.colaborador) &&
               Objects.equals(proyectoDonado, donacion.proyectoDonado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(monto, fecha, colaborador, proyectoDonado);
    }

    @Override
    public String toString() {
        return "Donacion{" +
               "monto=" + monto +
               ", fecha=" + fecha +
               ", colaborador=" + (colaborador != null ? colaborador.getNombre() : "N/A") +
               ", proyectoDonado=" + (proyectoDonado != null ? proyectoDonado.getNombre() : "N/A") +
               '}';
    }
}