package model;

public class ProyectoInvestigacion extends Proyecto {
    public ProyectoInvestigacion(String nombre, String descripcion, String urlVideoExplicativo, Investigador investigadorResponsable) {
        super(nombre, descripcion, urlVideoExplicativo, investigadorResponsable);
        // No hay atributos adicionales para este tipo de proyecto según la descripción
    }

    @Override
    public String toString() {
        return "ProyectoInvestigacion{" +
               "nombre='" + getNombre() + '\'' +
               ", descripcion='" + getDescripcion() + '\'' +
               '}';
    }
}