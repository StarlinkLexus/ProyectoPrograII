package model;

public class ProyectoInvestigacion extends Proyecto {

    /*
     * Constructor del proyecto de investigacion.
     * Inicializa el proyecto con nombre, descripcion, url del video y responsable.
     */
    public ProyectoInvestigacion(String nombre, String descripcion, String urlVideo, Investigador responsable) {
        super(nombre, descripcion, urlVideo, responsable);
    }
}
