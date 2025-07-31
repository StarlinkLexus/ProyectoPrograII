package model;

import java.util.ArrayList;
import java.util.List;

public class Colaborador {

    // Atributos del colaborador: nombre y lista de proyectos seguidos
    private String nombre;
    private List<ProyectoEmprendimiento> proyectosSeguidos;

    /*
     * Constructor del colaborador
     */
    public Colaborador(String nombre) {
        this.nombre = nombre;
        this.proyectosSeguidos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    /**
     * Permite al colaborador seguir un proyecto de emprendimiento
     */
    public void seguirProyecto(ProyectoEmprendimiento proyecto) {
        if (!proyectosSeguidos.contains(proyecto)) {
            proyectosSeguidos.add(proyecto);
        }
    }

    /**
     * Verifica si el colaborador ya ha seguido un proyecto
     */
    public boolean haSeguido(ProyectoEmprendimiento proyecto) {
        return proyectosSeguidos.contains(proyecto);
    }

    /**
     * Permite al colaborador donar a un proyecto si lo ha seguido
     */
    public boolean donar(ProyectoEmprendimiento proyecto, double cantidad) {
        if (haSeguido(proyecto)) {
            proyecto.donar(cantidad); // Llama al metodo donar del proyecto
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
