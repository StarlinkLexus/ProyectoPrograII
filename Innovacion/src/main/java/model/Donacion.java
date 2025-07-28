package model;

public class Donacion {
    private double monto;
    private Colaborador colaborador;

    public Donacion(double monto, Colaborador colaborador) {
        this.monto = monto;
        this.colaborador = colaborador;
    }

    public double getMonto() {
        return monto;
    }
}