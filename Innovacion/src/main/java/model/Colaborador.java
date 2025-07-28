package model;

import java.util.ArrayList;
import java.util.List;

public class Colaborador extends Usuario {
    private List<Donacion> donaciones = new ArrayList<>();

    public Colaborador(String nombre) {
        super(nombre);
    }

    public void agregarDonacion(Donacion d) {
        donaciones.add(d);
    }

    public double totalDonado() {
        return donaciones.stream().mapToDouble(Donacion::getMonto).sum();
    }
}