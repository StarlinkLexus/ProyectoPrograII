package model;

import java.util.ArrayList;
import java.util.List;

public class ProyectoEmprendimiento extends Proyecto {
    private double montoObjetivo;
    private List<Donacion> donaciones = new ArrayList<>();

    public ProyectoEmprendimiento(String nombre, String descripcion, String videoURL, Investigador responsable, double montoObjetivo) {
        super(nombre, descripcion, videoURL, responsable);
        this.montoObjetivo = montoObjetivo;
    }

    public boolean agregarDonacion(Donacion d) {
        if (totalRecaudado() + d.getMonto() <= montoObjetivo) {
            donaciones.add(d);
            return true;
        }
        return false;
    }

    public double totalRecaudado() {
        return donaciones.stream().mapToDouble(Donacion::getMonto).sum();
    }
}